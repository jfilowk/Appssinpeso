package com.smartdumbphones.appssinpeso.internal.di.module;

import android.content.Context;
import com.smartdumbphones.appssinpeso.Appssinpeso;
import com.smartdumbphones.appssinpeso.datasize.AppDetails;
import com.smartdumbphones.appssinpeso.datasize.ApplicationsManager;
import com.smartdumbphones.appssinpeso.datasize.ApplicationsManagerImpl;
import com.smartdumbphones.appssinpeso.datasize.MainThread;
import com.smartdumbphones.appssinpeso.datasize.MainThreadImpl;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module public class ApplicationModule {

  private Appssinpeso appssinpeso;

  public ApplicationModule(Appssinpeso appssinpeso) {
    this.appssinpeso = appssinpeso;
  }

  @Provides Context provideApplicationContext() {
    return this.appssinpeso.getApplicationContext();
  }

  @Provides @Singleton MainThread provideMainThread() {
    return new MainThreadImpl();
  }

  @Provides @Singleton ApplicationsManager provideApplicationsManager(
      ApplicationsManagerImpl manager) {
    return manager;
  }

  @Provides @Singleton AppDetails provideAppDetails(Context context) {
    return new AppDetails(context);
  }
}
