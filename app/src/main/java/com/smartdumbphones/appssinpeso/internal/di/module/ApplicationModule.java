package com.smartdumbphones.appssinpeso.internal.di.module;

import android.content.Context;
import com.smartdumbphones.appssinpeso.Appssinpeso;
import com.smartdumbphones.appssinpeso.ui.device_applications.AppDetails;
import com.smartdumbphones.appssinpeso.internal.manager.ApplicationsManager;
import com.smartdumbphones.appssinpeso.internal.manager.ApplicationsManagerImpl;
import com.smartdumbphones.appssinpeso.internal.domain.MainThread;
import com.smartdumbphones.appssinpeso.internal.domain.MainThreadImpl;
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
