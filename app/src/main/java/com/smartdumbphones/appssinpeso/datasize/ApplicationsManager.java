package com.smartdumbphones.appssinpeso.datasize;

public interface ApplicationsManager {
  void attachOnApplicationListener(OnApplicationsListener listener);

  void start();

  void stop();

  interface OnApplicationsListener {
    // TODO: 01/04/16 add object to return

    void onSuccess();

    void onError();
  }
}
