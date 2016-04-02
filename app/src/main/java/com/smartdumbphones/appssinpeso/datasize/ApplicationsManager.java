package com.smartdumbphones.appssinpeso.datasize;

import com.smartdumbphones.appssinpeso.datasize.models.ApplicationInfoStruct;
import java.util.List;

public interface ApplicationsManager {
  void attachOnApplicationListener(OnApplicationsListener listener);

  void start();

  void stop();

  interface OnApplicationsListener {
    // TODO: 01/04/16 add object to return

    void onSuccess(List<ApplicationInfoStruct> applicationInfoStructList, float totalCacheSize);

    void onError();
  }
}
