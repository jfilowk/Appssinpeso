package com.smartdumbphones.appssinpeso.models;

import android.graphics.drawable.Drawable;

public class ApplicationInfoStruct {
  private String appname = "";
  private String pname = "";
  private Drawable icon;
  private String datadir = "";
  private long apkSize = 0;
  private long cacheSize = 0;
  private long dataSize = 0;
  private long totalSize = 0;
  private boolean isSystem;

  public ApplicationInfoStruct() {
  }

  public ApplicationInfoStruct(String appname, String pname, Drawable icon, String datadir,
      long apkSize, long cacheSize, long dataSize, long totalSize, boolean isSystem) {
    this.appname = appname;
    this.pname = pname;
    this.icon = icon;
    this.datadir = datadir;
    this.apkSize = apkSize;
    this.cacheSize = cacheSize;
    this.dataSize = dataSize;
    this.totalSize = totalSize;
    this.isSystem = isSystem;
  }

  public String getAppname() {
    return appname;
  }

  public void setAppname(String appname) {
    this.appname = appname;
  }

  public String getPname() {
    return pname;
  }

  public void setPname(String pname) {
    this.pname = pname;
  }

  public Drawable getIcon() {
    return icon;
  }

  public void setIcon(Drawable icon) {
    this.icon = icon;
  }

  public String getDatadir() {
    return datadir;
  }

  public void setDatadir(String datadir) {
    this.datadir = datadir;
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

  public long getTotalSize() {
    return totalSize;
  }

  public void setTotalSize(long totalSize) {
    this.totalSize = apkSize + cacheSize;
  }

  public boolean isSystem() {
    return isSystem;
  }

  public void setIsSystem(boolean isSystem) {
    this.isSystem = isSystem;
  }
}
