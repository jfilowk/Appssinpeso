package com.smartdumbphones.appssinpeso.internal.di.component;

import com.smartdumbphones.appssinpeso.internal.di.PerActivity;
import com.smartdumbphones.appssinpeso.internal.di.module.ActivityModule;
import com.smartdumbphones.appssinpeso.internal.di.module.LoginModule;
import com.smartdumbphones.appssinpeso.ui.login.LoginActivity;
import dagger.Component;

@PerActivity @Component(dependencies = ApplicationComponent.class, modules = {
    ActivityModule.class, LoginModule.class
}) public interface LoginComponent {

  void inject(LoginActivity activity);
}
