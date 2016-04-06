package com.smartdumbphones.appssinpeso.internal.di.module;

import com.smartdumbphones.appssinpeso.internal.di.PerActivity;
import com.smartdumbphones.appssinpeso.ui.device_applications.DeviceApplicationInstalledPresenter;
import com.smartdumbphones.appssinpeso.ui.device_applications.DeviceApplicationInstalledPresenterImpl;
import dagger.Module;
import dagger.Provides;

@Module public class InstalledModule {

  @Provides @PerActivity DeviceApplicationInstalledPresenter provideApplicationInstalledPresenter(DeviceApplicationInstalledPresenterImpl presenter) {
    return presenter;
  }
}
