package com.smartdumbphones.appssinpeso.datasize;

import com.smartdumbphones.appssinpeso.datasize.models.AllApplications;

public interface ApplicationsManager {
  void attachOnApplicationListener(OnApplicationsListener listener);

  void start();

  void stop();

  interface OnApplicationsListener {

    void onSuccess(AllApplications allApplications);

    void onError();
  }
}
