package com.smartdumbphones.appssinpeso.internal.di.component;

import com.smartdumbphones.appssinpeso.internal.di.PerActivity;
import com.smartdumbphones.appssinpeso.internal.di.module.ActivityModule;
import com.smartdumbphones.appssinpeso.internal.di.module.InstalledModule;
import com.smartdumbphones.appssinpeso.ui.device_applications.DeviceApplicationInstalledActivity;
import dagger.Component;

@PerActivity @Component(dependencies = ApplicationComponent.class, modules = {
    ActivityModule.class, InstalledModule.class
}) public interface InstalledComponent {

  void inject(DeviceApplicationInstalledActivity activity);
}
