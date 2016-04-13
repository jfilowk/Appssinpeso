package com.smartdumbphones.appssinpeso.data.repository;

import com.smartdumbphones.appssinpeso.models.ApplicationInfoStruct;
import java.util.List;

public interface DeviceApplicationRepository {

  void createDeviceApplicationList(CreateDeviceApplicationListCallback callback);

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
