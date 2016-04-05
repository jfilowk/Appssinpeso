package com.smartdumbphones.appssinpeso.internal.di.component;

import android.content.Context;
import com.smartdumbphones.appssinpeso.Appssinpeso;
import com.smartdumbphones.appssinpeso.datasize.AppDetails;
import com.smartdumbphones.appssinpeso.datasize.ApplicationsManager;
import com.smartdumbphones.appssinpeso.datasize.MainThread;
import com.smartdumbphones.appssinpeso.internal.di.module.ApplicationModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = ApplicationModule.class) public interface ApplicationComponent {

  void inject(Appssinpeso application);

  // Expose to graph

  Context applicationContext();

  MainThread mainThread();

  ApplicationsManager applicationManager();

  AppDetails appDetails();
}
