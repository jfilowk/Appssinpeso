package com.smartdumbphones.appssinpeso.internal.manager;

import com.smartdumbphones.appssinpeso.models.AllApplications;

public interface ApplicationsManager {
  void attachOnApplicationListener(OnApplicationsListener listener);

  void start(boolean getSystemPackages);

  void stop();

  interface OnApplicationsListener {

    void onSuccess(AllApplications allApplications);

    void onError();
  }
}
