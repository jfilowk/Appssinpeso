package com.smartdumbphones.appssinpeso;

import android.app.Application;
import com.smartdumbphones.appssinpeso.datasize.ApplicationsManager;
import com.smartdumbphones.appssinpeso.datasize.ApplicationsManagerImpl;
import com.smartdumbphones.appssinpeso.datasize.MainThreadImpl;
import timber.log.Timber;

public class Appssinpeso extends Application {

  private static Appssinpeso instance;
  private static ApplicationsManager applicationsManager;

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

  public static ApplicationsManager getApplicationsManager() {
    if (applicationsManager == null) {
      applicationsManager = new ApplicationsManagerImpl(instance, new MainThreadImpl());
    }
    return applicationsManager;
  }
}
