package com.smartdumbphones.appssinpeso.internal.di.module;

import com.smartdumbphones.appssinpeso.datasize.ApplicationInstalledPresenter;
import com.smartdumbphones.appssinpeso.datasize.ApplicationInstalledPresenterImpl;
import com.smartdumbphones.appssinpeso.internal.di.PerActivity;
import dagger.Module;
import dagger.Provides;

@Module public class InstalledModule {

  @Provides @PerActivity ApplicationInstalledPresenter provideApplicationInstalledPresenter(ApplicationInstalledPresenterImpl presenter) {
    return presenter;
  }
}
