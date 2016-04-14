package com.smartdumbphones.appssinpeso.models;

public class ApplicationInfoCached {
  private long apkSize = 0;
  private long cacheSize = 0;
  private long dataSize = 0;

  public ApplicationInfoCached() {
  }

  public ApplicationInfoCached(long apkSize, long cacheSize, long dataSize) {
    this.apkSize = apkSize;
    this.cacheSize = cacheSize;
    this.dataSize = dataSize;
  }

  public long getApkSize() {
    return apkSize;
  }

  public void setApkSize(long apkSize) {
    this.apkSize = apkSize;
  }

  public long getCacheSize() {
    return cacheSize;
  }

  public void setCacheSize(long cacheSize) {
    this.cacheSize = cacheSize;
  }

  public long getDataSize() {
    return dataSize;
  }

  public void setDataSize(long dataSize) {
    this.dataSize = dataSize;
  }
}
