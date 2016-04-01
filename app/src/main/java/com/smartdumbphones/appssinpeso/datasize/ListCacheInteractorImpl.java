package com.smartdumbphones.appssinpeso.datasize;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListCacheInteractorImpl implements ListCacheInteractor, ListenerErrorPackage {

  private ExecutorService executorService;

  public ListCacheInteractorImpl() {
    executorService = Executors.newSingleThreadExecutor();
  }

  // TODO: Listener
  @Override public void getPackages() {
    executorService.submit(new Runnable() {
      @Override public void run() {
        //
      }
    });
  }

  @Override public void onError() {

  }
}
