package com.smartdumbphones.appssinpeso.datasize.models;

import android.graphics.drawable.Drawable;

public class ApplicationInfoStruct {
  private String appname = "";
  private String pname = "";
  private Drawable icon;
  private String datadir = "";

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
}
