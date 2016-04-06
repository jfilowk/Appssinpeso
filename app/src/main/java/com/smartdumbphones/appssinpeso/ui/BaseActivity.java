package com.smartdumbphones.appssinpeso.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.smartdumbphones.appssinpeso.Appssinpeso;
import com.smartdumbphones.appssinpeso.internal.di.component.ApplicationComponent;
import com.smartdumbphones.appssinpeso.internal.di.module.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  public ApplicationComponent getApplicationComponent() {
    return ((Appssinpeso) getApplication()).getApplicationComponent();
  }

  public ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }
}
