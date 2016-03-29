package com.smartdumbphones.appssinpeso.datasize.models;

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

  public ApplicationInfoStruct() {
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
    this.totalSize = apkSize+cacheSize;
  }
}
