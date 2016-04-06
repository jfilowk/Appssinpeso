package com.smartdumbphones.appssinpeso.internal.di.module;

import android.content.Context;
import android.content.pm.PackageManager;
import com.smartdumbphones.appssinpeso.Appssinpeso;
import com.smartdumbphones.appssinpeso.internal.domain.MainThread;
import com.smartdumbphones.appssinpeso.internal.domain.MainThreadImpl;
import com.smartdumbphones.appssinpeso.internal.manager.ApplicationsManager;
import com.smartdumbphones.appssinpeso.internal.manager.ApplicationsManagerImpl;
import com.smartdumbphones.appssinpeso.ui.device_applications.AppDetails;
import com.smartdumbphones.appssinpeso.ui.login.LoginInteractor;
import com.smartdumbphones.appssinpeso.ui.login.LoginInteractorImpl;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

  @Provides @Singleton ExecutorService provideExecutorSingleThread() {
    return Executors.newSingleThreadExecutor();
  }

  @Provides @Singleton PackageManager providePackageManager(Context context) {
    return context.getPackageManager();
  }

  @Provides @Singleton ApplicationsManager provideApplicationsManager(
      ApplicationsManagerImpl manager) {
    return manager;
  }

  @Provides @Singleton AppDetails provideAppDetails(Context context) {
    return new AppDetails(context);
  }

  @Provides LoginInteractor provideLoginInteractor(LoginInteractorImpl interactor) {
    return interactor;
  }
}
