package com.smartdumbphones.appssinpeso;

import android.app.Application;
import timber.log.Timber;

public class Appssinpeso extends Application {

  private static Appssinpeso instance;

  public static Appssinpeso getInstance() {
    if (instance == null) {
      instance = new Appssinpeso();
    }
    return instance;
  }

  @Override public void onCreate() {
    super.onCreate();

    instance = this;

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    } else {
      // Instance of a Tree that allows remote report crash
    }
  }
}
