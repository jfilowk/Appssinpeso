package com.smartdumbphones.appssinpeso.datasize;

import android.content.Context;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import com.smartdumbphones.appssinpeso.datasize.models.ApplicationInfoStruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
  private long packageSize = 0;

  private int packagesSize;

  private List<PackageStats> statsList = new ArrayList<>();

  public ApplicationsManagerImpl(Context context) {
    this.context = context;
    this.executorService = Executors.newSingleThreadExecutor();
  }

  @Override public void attachOnApplicationListener(OnApplicationsListener listener) {
    this.listener = listener;
  }

  // TODO: Listener
  @Override public void start() {
    executorService.submit(new Runnable() {
      @Override public void run() {
        AppDetails appDetails = new AppDetails();
        ArrayList<ApplicationInfoStruct> packages = appDetails.getPackages();
        packagesSize = packages.size();
        if (packagesSize == 0) {
          notifyOnError();
        } else {
          sortPackagesAlpha(packages);

          for (ApplicationInfoStruct aPackage : packages) {
            try {
              PackageManager packageManager = context.getPackageManager();
              Method getPackageSizeInfo = packageManager.getClass()
                  .getMethod("getPackageSizeInfo", String.class, IPackageStatsObserver.class);

              CachePackState cachePackState = new CachePackState();
              cachePackState.calculate(aPackage, new CachePackState.Callback() {
                @Override public void onSuccess(PackageStats stats) {
                  statsList.add(stats);
                  notifyResult();
                }
              });

              getPackageSizeInfo.invoke(packageManager, aPackage.getPname(), cachePackState);
            } catch (SecurityException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
              e.printStackTrace();
              notifyOnError();
            }
          }
        }
      }
    });
  }

  private void notifyResult() {
    if (statsList.size() == packagesSize) {
      notifyOnSuccess();
    }
  }

  private void notifyOnSuccess() {
    if (listener != null) {
      listener.onSuccess();
    }
  }

  private void notifyOnError() {
    if (listener != null) {
      listener.onError();
    }
  }

  private void sortPackagesAlpha(ArrayList<ApplicationInfoStruct> packages) {
    Collections.sort(packages, new Comparator<ApplicationInfoStruct>() {
      @Override public int compare(ApplicationInfoStruct lhs, ApplicationInfoStruct rhs) {
        return lhs.getAppname().compareTo(rhs.getAppname());
      }
    });
  }

  @Override public void stop() {
    listener = null;
  }
}
