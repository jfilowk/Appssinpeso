package com.smartdumbphones.appssinpeso.data.entity;

public class DeviceApplicationEntity {

  private String packageName;
  private long apkSize = 0;
  private long cacheSize = 0;
  private long dataSize = 0;

  public DeviceApplicationEntity() {
  }

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
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
