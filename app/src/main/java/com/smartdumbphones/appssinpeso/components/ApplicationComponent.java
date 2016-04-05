package com.smartdumbphones.appssinpeso.components;

import android.content.Context;
import com.smartdumbphones.appssinpeso.datasize.ApplicationsManager;
import com.smartdumbphones.appssinpeso.datasize.MainThread;
import com.smartdumbphones.appssinpeso.modules.ApplicationModule;
import dagger.Component;
import java.util.concurrent.ExecutorService;
import javax.inject.Singleton;

@Singleton
@Component(modules = { ApplicationModule.class })
public interface ApplicationComponent {

  Context context();

  ExecutorService threadExecutor();

  MainThread mainThread();

  ApplicationsManager applicationManager();
}
