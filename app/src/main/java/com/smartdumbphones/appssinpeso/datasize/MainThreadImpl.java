package com.smartdumbphones.appssinpeso.datasize;

import android.os.Handler;
import android.os.Looper;
import javax.inject.Inject;

public class MainThreadImpl implements MainThread {
  private Handler handler;

  @Inject
  public MainThreadImpl() {
    this.handler = new Handler(Looper.getMainLooper());
  }

  @Override public void post(Runnable runnable) {
    handler.post(runnable);
  }
}
