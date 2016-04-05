package com.smartdumbphones.appssinpeso.modules;

import android.content.Context;
import com.smartdumbphones.appssinpeso.Appssinpeso;
import com.smartdumbphones.appssinpeso.datasize.ApplicationsManager;
import com.smartdumbphones.appssinpeso.datasize.MainThread;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.inject.Singleton;

@Module public class ApplicationModule {
  private final Appssinpeso application;

  public ApplicationModule(Appssinpeso application) {
    this.application = application;
  }

  @Provides @Singleton Context provideApplicationContext() {
    return this.application;
  }

  @Provides @Singleton ExecutorService provideExecutor(Executors executors) {
    return Executors.newSingleThreadExecutor();
  }

  @Provides @Singleton MainThread provideMainThread(MainThread mainThread) {
    return mainThread;
  }

  @Provides @Singleton ApplicationsManager provideApplicationManager(
      ApplicationsManager applicationsManager) {
    return applicationsManager;
  }
}
