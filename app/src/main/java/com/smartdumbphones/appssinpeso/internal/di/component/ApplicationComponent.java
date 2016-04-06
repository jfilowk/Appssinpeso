package com.smartdumbphones.appssinpeso.internal.di.component;

import android.content.Context;
import com.smartdumbphones.appssinpeso.Appssinpeso;
import com.smartdumbphones.appssinpeso.internal.di.module.ApplicationModule;
import com.smartdumbphones.appssinpeso.internal.domain.MainThread;
import com.smartdumbphones.appssinpeso.internal.manager.ApplicationsManager;
import com.smartdumbphones.appssinpeso.ui.device_applications.AppDetails;
import com.smartdumbphones.appssinpeso.ui.login.LoginInteractor;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = ApplicationModule.class) public interface ApplicationComponent {

  void inject(Appssinpeso application);

  // Expose to graph

  Context applicationContext();

  MainThread mainThread();

  ApplicationsManager applicationManager();

  AppDetails appDetails();

  LoginInteractor loginInteractor();
}
