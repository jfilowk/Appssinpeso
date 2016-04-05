package com.smartdumbphones.appssinpeso.internal.di.component;

import android.app.Activity;
import com.smartdumbphones.appssinpeso.BaseActivity;
import com.smartdumbphones.appssinpeso.internal.di.PerActivity;
import com.smartdumbphones.appssinpeso.internal.di.module.ActivityModule;
import dagger.Component;

@PerActivity @Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

  void inject(BaseActivity activity);

  Activity getActivity();
}
