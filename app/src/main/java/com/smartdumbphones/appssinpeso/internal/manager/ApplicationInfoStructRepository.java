package com.smartdumbphones.appssinpeso.internal.manager;

import com.smartdumbphones.appssinpeso.models.ApplicationInfoStruct;
import java.util.List;

public interface ApplicationInfoStructRepository {

  void createDeviceApplicationList(List<ApplicationInfoStruct> applicationInfoStructList,
      CreateDeviceApplicationListCallback callback);

  void getDeviceApplicationList(DeviceApplicationListCallback callback);

  interface CreateDeviceApplicationListCallback {
    void onCreateDeviceApplicationListCallback(boolean success);

    void onError();
  }

  interface DeviceApplicationListCallback {
    void onDeviceApplicationList(List<ApplicationInfoStruct> applicationInfoStructList);

    void onError();
  }
}
