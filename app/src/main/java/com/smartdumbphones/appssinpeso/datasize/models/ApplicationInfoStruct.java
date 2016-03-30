package com.smartdumbphones.appssinpeso.datasize.models;

import android.graphics.drawable.Drawable;

public class ApplicationInfoStruct {
  private String appname = "";
  private String pname = "";
  private Drawable icon;
  private String datadir = "";
  private float apkSize = 0;
  private float cacheSize = 0;
  private float dataSize = 0;
  private float totalSize = 0;

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

  public float getApkSize() {
    return apkSize;
  }

  public void setApkSize(float apkSize) {
    this.apkSize = apkSize;
  }

  public float getCacheSize() {
    return cacheSize;
  }

  public void setCacheSize(float cacheSize) {
    this.cacheSize = cacheSize;
  }

  public float getDataSize() {
    return dataSize;
  }

  public void setDataSize(float dataSize) {
    this.dataSize = dataSize;
  }

  public float getTotalSize() {
    return totalSize;
  }

  public void setTotalSize(float totalSize) {
    this.totalSize = apkSize+cacheSize;
  }
}
