package com.smartdumbphones.appssinpeso.datasize;

public interface ApplicationsManager {
  void start(OnApplicationsListener listener);

  void stop();

  interface OnApplicationsListener {
    // TODO: 01/04/16 add object to return

    void onSuccess();

    void onError();
  }
}
