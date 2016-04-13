package com.smartdumbphones.appssinpeso.internal.manager;

import android.content.Context;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import com.smartdumbphones.appssinpeso.internal.domain.MainThread;
import com.smartdumbphones.appssinpeso.models.AllApplications;
import com.smartdumbphones.appssinpeso.models.ApplicationInfoStruct;
import com.smartdumbphones.appssinpeso.ui.device_applications.AppDetails;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import javax.inject.Inject;
import timber.log.Timber;

public class ApplicationsManagerImpl implements ApplicationsManager {

  private ExecutorService executorService;
  private OnApplicationsListener listener;
  private AppDetails appDetails;
  private PackageManager packageManager;
  private Context context;
  private ApplicationInfoStructRepository applicationInfoStructRepository;

  private MainThread mainThread;

  @Inject public ApplicationsManagerImpl(MainThread mainThread, AppDetails appDetails,
      ExecutorService executorService, PackageManager packageManager, Context context,
      ApplicationInfoStructRepository applicationInfoStructRepository) {
    this.appDetails = appDetails;
    // TODO: singlenton? never destroyed?
    this.executorService = executorService;
    this.mainThread = mainThread;
    this.packageManager = packageManager;
    this.context = context;
    this.applicationInfoStructRepository = applicationInfoStructRepository;
  }

  @Override public void attachOnApplicationListener(OnApplicationsListener listener) {
    this.listener = listener;
  }

  // TODO: Add size photos
  @Override public void start(final boolean getSystemPackages) {
    if (this.listener != null) {
      executorService.submit(new Runnable() {
        @Override public void run() {
          final List<ApplicationInfoStruct> listApplications =
              appDetails.getPackages(getSystemPackages);

          if (listApplications.size() == 0) {
            notifyOnError();
          } else {
            sortPackagesAlpha(listApplications);
            final List<PackageStats> listPackageStats = new ArrayList<>(listApplications.size());
            for (ApplicationInfoStruct aPackage : listApplications) {
              try {
                Method getPackageSizeInfo = packageManager.getClass()
                    .getMethod("getPackageSizeInfo", String.class, IPackageStatsObserver.class);
                getPackageSizeInfo.invoke(packageManager, aPackage.getPname(),
                    new CachePackState(new CachePackState.Callback() {
                      @Override public void onSuccess(PackageStats stats) {
                        listPackageStats.add(stats);
                        // TODO: 13/04/2016 Extract this
                        if (isFinishedProcess(listApplications.size(), listPackageStats.size())) {
                          AllApplications allApplications =
                              mergeData(listApplications, listPackageStats);
                          notifyOnSuccess(allApplications);
                        }
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

  @Override public void stop() {
    listener = null;
  }

  private boolean isFinishedProcess(int listApplicationsSize, int listPackageStatsSize) {
    return listApplicationsSize == listPackageStatsSize;
  }

  private AllApplications mergeData(List<ApplicationInfoStruct> listApplications,
      List<PackageStats> listPackageStats) {

    long totalCacheSize = 0;
    long totalApplicationSize = 0;
    List<ApplicationInfoStruct> applicationInfoStructList =
        new ArrayList<>(listApplications.size());

    for (ApplicationInfoStruct listApplication : listApplications) {
      for (PackageStats listPackageStat : listPackageStats) {
        if (listApplication.getPname().equals(listPackageStat.packageName)) {
          addSizesApplication(listPackageStat, listApplication);

          applicationInfoStructList.add(listApplication);
          totalCacheSize += listPackageStat.cacheSize + listPackageStat.externalCacheSize;
          totalApplicationSize += listPackageStat.codeSize;

          break;
        }
      }
    }

    return new AllApplications.Builder().setTotalNumApplications(applicationInfoStructList.size())
        .setTotalSizeApplications(totalApplicationSize)
        .setTotalSizeCache(totalCacheSize)
        .setListApplications(applicationInfoStructList)
        .build();
  }

  private void notifyOnSuccess(final AllApplications allApplications) {
    if (listener != null) {
      executorService.submit(new Runnable() {
        @Override public void run() {
          applicationInfoStructRepository.createDeviceApplicationList(
              allApplications.getListApplications(),
              new ApplicationInfoStructRepository.CreateDeviceApplicationListCallback() {
                @Override public void onCreateDeviceApplicationListCallback(boolean success) {
                  Timber.e("INSERTADO CORRECTAMENTE");
                }

                @Override public void onError() {

                }
              });
        }
      });
      Timber.e("ACABO DE EMPEZAR");
      mainThread.post(new Runnable() {
        @Override public void run() {
          listener.onSuccess(allApplications);
        }
      });
    }
  }

  private void notifyOnError() {
    if (listener != null) {
      mainThread.post(new Runnable() {
        @Override public void run() {
          listener.onError();
        }
      });
    }
  }

  private void sortPackagesAlpha(List<ApplicationInfoStruct> packages) {
    Collections.sort(packages, new Comparator<ApplicationInfoStruct>() {
      @Override public int compare(ApplicationInfoStruct lhs, ApplicationInfoStruct rhs) {
        return lhs.getAppname().compareTo(rhs.getAppname());
      }
    });
  }

  private void addSizesApplication(PackageStats pStats,
      ApplicationInfoStruct applicationInfoStruct) {
    applicationInfoStruct.setApkSize(pStats.codeSize);
    applicationInfoStruct.setCacheSize(pStats.cacheSize + pStats.externalCacheSize);
    applicationInfoStruct.setDataSize(pStats.dataSize + pStats.externalDataSize);
    applicationInfoStruct.setTotalSize(sumSizes(applicationInfoStruct));
  }

  private long sumSizes(ApplicationInfoStruct applicationInfoStruct) {
    return applicationInfoStruct.getApkSize()
        + applicationInfoStruct.getCacheSize()
        + applicationInfoStruct.getDataSize();
  }
}
