package com.smartdumbphones.appssinpeso;

import android.app.Application;
import timber.log.Timber;

public class Appssinpeso extends Application {

  @Override public void onCreate() {
    super.onCreate();

    if(BuildConfig.DEBUG){
      Timber.plant(new Timber.DebugTree());
    } else {
      // Instance of a Tree that allows remote report crash
    }
  }
}
