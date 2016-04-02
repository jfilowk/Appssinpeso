package com.smartdumbphones.appssinpeso.datasize;

import android.content.Context;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import com.smartdumbphones.appssinpeso.datasize.models.AllApplications;
import com.smartdumbphones.appssinpeso.datasize.models.ApplicationInfoStruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApplicationsManagerImpl implements ApplicationsManager {

  private ExecutorService executorService;
  private OnApplicationsListener listener;
  private Context context;
  private long totalCacheSize = 0;
  private long totalApplicationSize = 0;
  private List<ApplicationInfoStruct> listApplications;
  private List<PackageStats> listPackageStats;

  public ApplicationsManagerImpl(Context context) {
    this.context = context;
    this.executorService = Executors.newSingleThreadExecutor();
    this.listPackageStats = new ArrayList<>();
  }

  @Override public void attachOnApplicationListener(OnApplicationsListener listener) {
    this.listener = listener;
  }

  @Override public void start() {
    if (this.listener != null) {
      executorService.submit(new Runnable() {
        @Override public void run() {
          AppDetails appDetails = new AppDetails();
          listApplications = appDetails.getPackages();
          if (listApplications.size() == 0) {
            notifyOnError();
          } else {
            sortPackagesAlpha(listApplications);
            for (ApplicationInfoStruct aPackage : listApplications) {
              try {
                PackageManager packageManager = context.getPackageManager();
                Method getPackageSizeInfo = packageManager.getClass()
                    .getMethod("getPackageSizeInfo", String.class, IPackageStatsObserver.class);
                getPackageSizeInfo.invoke(packageManager, aPackage.getPname(),
                    new CachePackState(new CachePackState.Callback() {
                      @Override public void onSuccess(PackageStats stats) {
                        listPackageStats.add(stats);
                        notifyOnSuccess();
                      }
                    }));
              } catch (SecurityException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
                notifyOnError();
              }
            }
          }
        }
      });
    }
  }

  private void notifyOnSuccess() {
    if (listApplications.size() == listPackageStats.size()) {
      for (ApplicationInfoStruct listApplication : listApplications) {
        for (PackageStats listPackageStat : listPackageStats) {
          if (listApplication.getPname().equals(listPackageStat.packageName)) {
            addSizesApplication(listPackageStat, listApplication);
            totalCacheSize =
                totalCacheSize + listPackageStat.cacheSize + listPackageStat.externalCacheSize;
            totalApplicationSize = totalApplicationSize + listPackageStat.codeSize;
            break;
          }
        }
      }
      AllApplications allApplications =
          new AllApplications.Builder().setTotalNumApplications(listApplications.size())
              .setTotalSizeApplications(convertToMb(totalApplicationSize))
              .setTotalSizeCache(convertToMb(totalCacheSize))
              .build();
      if (listener != null) {
        listener.onSuccess(listApplications, allApplications);
      }
    }
  }

  private void notifyOnError() {
    if (listener != null) {
      listener.onError();
    }
  }

  private void sortPackagesAlpha(List<ApplicationInfoStruct> packages) {
    Collections.sort(packages, new Comparator<ApplicationInfoStruct>() {
      @Override public int compare(ApplicationInfoStruct lhs, ApplicationInfoStruct rhs) {
        return lhs.getAppname().compareTo(rhs.getAppname());
      }
    });
  }

  @Override public void stop() {
    listener = null;
  }

  private void addSizesApplication(PackageStats pStats,
      ApplicationInfoStruct applicationInfoStruct) {
    applicationInfoStruct.setApkSize(convertToMb(pStats.codeSize));
    applicationInfoStruct.setCacheSize(convertToMb(pStats.cacheSize + pStats.externalCacheSize));
    applicationInfoStruct.setDataSize(convertToMb(pStats.dataSize + pStats.externalDataSize));
    applicationInfoStruct.setTotalSize(convertToMb((pStats.codeSize
        + pStats.cacheSize
        + pStats.externalCacheSize
        + pStats.dataSize
        + pStats.externalDataSize)));
  }

  private float convertToMb(long size) {
    float v = (float) size / 1024000;
    DecimalFormat format = new DecimalFormat();
    format.setMaximumFractionDigits(1);
    return Float.parseFloat(format.format(v));
  }
}
