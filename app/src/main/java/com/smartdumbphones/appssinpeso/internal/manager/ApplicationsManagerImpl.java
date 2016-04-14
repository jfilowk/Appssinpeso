package com.smartdumbphones.appssinpeso.internal.manager;

import android.content.Context;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.support.annotation.NonNull;
import com.smartdumbphones.appssinpeso.internal.domain.MainThread;
import com.smartdumbphones.appssinpeso.models.AllApplications;
import com.smartdumbphones.appssinpeso.models.ApplicationInfoCached;
import com.smartdumbphones.appssinpeso.models.ApplicationInfoStruct;
import com.smartdumbphones.appssinpeso.ui.device_applications.AppDetails;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
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

  private List<ApplicationInfoStruct> applicationInfoStructListAidl;
  private List<ApplicationInfoStruct> applicationInfoStructListCache;
  private boolean isDataReady = false;

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
    this.applicationInfoStructListAidl = new ArrayList<>();
    this.applicationInfoStructListCache = new ArrayList<>();
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
                        // TODO: 13/04/2016 Extract this eventbus
                        if (isFinishedProcess(listApplications.size(), listPackageStats.size())) {
                          //stop, return
                          applicationInfoStructListAidl =
                              generateAllApplicationsAidl(listApplications, listPackageStats);
                          //notify when finish
                          if (isDataReady) {
                            notifyDataProcessingDone();
                          } else {
                            isDataReady = true;
                          }
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

      executorService.submit(new Runnable() {
        @Override public void run() {
          applicationInfoStructRepository.getDeviceApplicationList(
              new ApplicationInfoStructRepository.DeviceApplicationListCallback() {
                @Override public void onDeviceApplicationList(
                    List<ApplicationInfoStruct> applicationInfoStructList) {
                  if (applicationInfoStructList != null) {
                    applicationInfoStructListCache = applicationInfoStructList;
                    if (isDataReady) {
                      notifyDataProcessingDone();
                    } else {
                      isDataReady = true;
                    }
                  }
                }

                @Override public void onError() {
                  Timber.e("error");
                }
              });
        }
      });
    }
  }

  private void notifyDataProcessingDone() {
    AllApplications allApplications =
        mergeData(applicationInfoStructListCache, applicationInfoStructListAidl);
    if (allApplications != null) {
      notifyOnSuccess(allApplications);
    } else {
      notifyOnError();
    }
  }

  private AllApplications mergeData(List<ApplicationInfoStruct> applicationInfoStructListCache,
      List<ApplicationInfoStruct> applicationInfoStructListAidl) {
    long totalCacheSize = 0;
    long totalApplicationsSize = 0;

    long totalCacheSizeCached = 0;
    long totalApplicationsSizeCached = 0;

    List<ApplicationInfoStruct> tmpApplicationInfoStructsAidl = new ArrayList<>();

    ApplicationInfoCached applicationInfoCached = null;

    for (ApplicationInfoStruct applicationInfoStructAidl : applicationInfoStructListAidl) {
      totalApplicationsSize += applicationInfoStructAidl.getApkSize();
      totalCacheSize += applicationInfoStructAidl.getCacheSize();
      for (ApplicationInfoStruct applicationInfoStructCache : applicationInfoStructListCache) {
        if (applicationInfoStructAidl.getPname().equals(applicationInfoStructCache.getPname())) {

          totalApplicationsSizeCached += applicationInfoStructCache.getApkSize();
          totalCacheSizeCached += applicationInfoStructCache.getCacheSize();

          // TODO: 14/04/2016 quitar los +5. mejorar el sistema

          Random random = new Random();
          boolean i = random.nextBoolean();

          long randomLong = -1;
          if (i) randomLong = 1;

          long sizeApk =
              applicationInfoStructAidl.getApkSize() - applicationInfoStructCache.getApkSize()
                  + randomLong;

          long sizeCache =
              applicationInfoStructAidl.getCacheSize() - applicationInfoStructCache.getCacheSize()
                  + randomLong;

          long sizeData =
              applicationInfoStructAidl.getDataSize() - applicationInfoStructCache.getDataSize()
                  + randomLong;

          if (hasChanges(sizeApk, sizeCache, sizeData)) {
            applicationInfoCached = createApplicationInfoCached(sizeApk, sizeCache, sizeData);
            applicationInfoStructAidl.setApplicationInfoCached(applicationInfoCached);
          }
          break;
        }
      }
      tmpApplicationInfoStructsAidl.add(applicationInfoStructAidl);
    }

    // TODO: 14/04/2016 clear global and put false is ready

    applicationInfoStructRepository.createDeviceApplicationList(tmpApplicationInfoStructsAidl,
        new ApplicationInfoStructRepository.CreateDeviceApplicationListCallback() {
          @Override public void onCreateDeviceApplicationListCallback(boolean success) {
            Timber.e(String.valueOf(success));
          }

          @Override public void onError() {
            Timber.e("Error");
          }
        });

    int totalNumApplicationsAidl = applicationInfoStructListAidl.size();
    int totalNumApplicationsCache = applicationInfoStructListCache.size();

    long varianceTotalSize = totalApplicationsSize - totalApplicationsSizeCached;
    long varianceCacheSize = totalCacheSize - totalCacheSizeCached;
    int varianceNumApplications = totalNumApplicationsAidl - totalNumApplicationsCache;

    applicationInfoStructListAidl.clear();
    applicationInfoStructListCache.clear();
    isDataReady = false;

    return new AllApplications.Builder().setTotalNumApplications(totalNumApplicationsAidl)
        .setTotalSizeApplications(totalApplicationsSize)
        .setTotalSizeCache(totalCacheSize)
        .setListApplications(tmpApplicationInfoStructsAidl)
        .setTotalNumApplicationsVariance(varianceNumApplications)
        .setTotalSizeApplicationsVariance(varianceTotalSize)
        .setTotalSizeCacheVariance(varianceCacheSize)
        .build();
  }

  @NonNull private ApplicationInfoCached createApplicationInfoCached(long sizeApk, long sizeCache,
      long sizeData) {
    ApplicationInfoCached applicationInfoCached;
    applicationInfoCached = new ApplicationInfoCached();
    applicationInfoCached.setApkSize(sizeApk);
    applicationInfoCached.setCacheSize(sizeCache);
    applicationInfoCached.setDataSize(sizeData);
    return applicationInfoCached;
  }

  @Override public void stop() {
    listener = null;
  }

  private boolean isFinishedProcess(int listApplicationsSize, int listPackageStatsSize) {
    return listApplicationsSize == listPackageStatsSize;
  }

  private List<ApplicationInfoStruct> generateAllApplicationsAidl(
      List<ApplicationInfoStruct> listApplications, List<PackageStats> listPackageStats) {

    List<ApplicationInfoStruct> applicationInfoStructList =
        new ArrayList<>(listApplications.size());

    for (ApplicationInfoStruct listApplication : listApplications) {
      for (PackageStats listPackageStat : listPackageStats) {
        if (listApplication.getPname().equals(listPackageStat.packageName)) {

          addSizesApplication(listPackageStat, listApplication);

          applicationInfoStructList.add(listApplication);

          break;
        }
      }
    }

    return applicationInfoStructList;
  }

  private void notifyOnSuccess(final AllApplications allApplications) {
    if (listener != null) {
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
    applicationInfoStruct.setTotalSize(sumTotalSize(applicationInfoStruct));
  }

  private long sumTotalSize(ApplicationInfoStruct applicationInfoStruct) {
    return applicationInfoStruct.getApkSize()
        + applicationInfoStruct.getCacheSize()
        + applicationInfoStruct.getDataSize();
  }

  public boolean hasChanges(long apkSize, long cacheSize, long dataSize) {
    return !(apkSize == 0 && cacheSize == 0 && dataSize == 0);
  }
}
