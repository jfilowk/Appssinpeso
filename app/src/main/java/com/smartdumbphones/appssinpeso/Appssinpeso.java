package com.smartdumbphones.appssinpeso;

import android.app.Application;
import com.smartdumbphones.appssinpeso.internal.di.component.ApplicationComponent;
import com.smartdumbphones.appssinpeso.internal.di.component.DaggerApplicationComponent;
import com.smartdumbphones.appssinpeso.internal.di.module.ApplicationModule;
import timber.log.Timber;

public class Appssinpeso extends Application {

  private ApplicationComponent component;

  @Override public void onCreate() {
    super.onCreate();

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    } else {
      // Instance of a Tree that allows remote report crash
    }

    initializeDagger();
  }

  private void initializeDagger() {
    if (component == null) {
      component = DaggerApplicationComponent.builder()
          .applicationModule(new ApplicationModule(this))
          .build();
    }

    component.inject(this);
  }

  public ApplicationComponent getApplicationComponent() {
    return component;
  }
}
