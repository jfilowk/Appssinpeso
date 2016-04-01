package com.smartdumbphones.appssinpeso.datasize;

import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageStats;
import android.os.RemoteException;
import com.smartdumbphones.appssinpeso.datasize.models.ApplicationInfoStruct;
import java.text.DecimalFormat;

public class CachePackState extends IPackageStatsObserver.Stub {

  private ApplicationInfoStruct applicationInfoStruct;
  private long packageSize;

  public CachePackState(ApplicationInfoStruct applicationInfoStruct, long packageSize) {
    this.applicationInfoStruct = applicationInfoStruct;
    this.packageSize = packageSize;
  }

  public ApplicationInfoStruct getApplicationInfoStruct() {
    return applicationInfoStruct;
  }

  public long getPackageSize() {
    return packageSize;
  }

  @Override public void onGetStatsCompleted(PackageStats pStats, boolean succeeded)
      throws RemoteException {
    addSizesApplication(pStats);
    packageSize = packageSize + pStats.cacheSize + pStats.externalCacheSize;
  }

  private void addSizesApplication(PackageStats pStats) {
    applicationInfoStruct.setApkSize(convertToMb(pStats.codeSize));
    applicationInfoStruct.setCacheSize(convertToMb(pStats.cacheSize + pStats.externalCacheSize));
    applicationInfoStruct.setDataSize(convertToMb(pStats.dataSize + pStats.externalDataSize));
    applicationInfoStruct.setTotalSize(applicationInfoStruct.getApkSize()
        + applicationInfoStruct.getCacheSize()
        + applicationInfoStruct.getDataSize());
  }

  private float convertToMb(long size) {
    float v = (float) size / 1024000;
    DecimalFormat format = new DecimalFormat();
    format.setMaximumFractionDigits(1);
    return Float.parseFloat(format.format(v));
  }
}
