package com.smartdumbphones.appssinpeso.datasize;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApplicationsManagerImpl implements ApplicationsManager, ListenerErrorPackage {

  private ExecutorService executorService;

  public ApplicationsManagerImpl() {
    executorService = Executors.newSingleThreadExecutor();
  }

  // TODO: Listener
  @Override public void start(OnApplicationsListener listener) {
    executorService.submit(new Runnable() {
      @Override public void run() {

      }
    });
  }

  @Override public void stop() {

  }

  @Override public void onError() {

  }
}
