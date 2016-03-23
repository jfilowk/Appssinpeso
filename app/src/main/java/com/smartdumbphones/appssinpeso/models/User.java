package com.smartdumbphones.appssinpeso.models;

import android.os.Build;

public class User {

  private static User user;

  private String email;
  private String manufacturerModel;
  private String androidVersion;

  private User() {
    this.manufacturerModel = Build.MANUFACTURER;
    this.androidVersion = Build.VERSION.RELEASE;
  }

  public static User getInstance() {
    if (user == null) {
      user = new User();
    }
    return user;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getManufacturerModel() {
    return manufacturerModel;
  }

  public String getAndroidVersion() {
    return androidVersion;
  }

  @Override public String toString() {
    return "User{" +
        "email='" + email + '\'' +
        ", manufacturerModel='" + manufacturerModel + '\'' +
        ", androidVersion='" + androidVersion + '\'' +
        '}';
  }
}
