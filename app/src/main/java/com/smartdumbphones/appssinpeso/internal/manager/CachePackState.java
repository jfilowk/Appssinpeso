package com.smartdumbphones.appssinpeso.internal.manager;

import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageStats;
import android.os.RemoteException;

public class CachePackState extends IPackageStatsObserver.Stub {

  interface Callback {
    void onSuccess(PackageStats stats);
  }

  private Callback callback;

  public CachePackState(Callback callback) {
    this.callback = callback;
  }

  @Override public void onGetStatsCompleted(PackageStats pStats, boolean succeeded)
      throws RemoteException {
    if (callback != null) {
      callback.onSuccess(pStats);
    }
  }
}
