package com.smartdumbphones.appssinpeso.data.repository.datasource;

import com.smartdumbphones.appssinpeso.data.entity.DeviceApplicationEntity;
import java.util.List;

public interface DeviceApplicationStore {

  void createDeviceApplicationList(List<DeviceApplicationEntity> deviceApplicationEntityList,
      CreateDeviceApplicationListCallback callback);

  void getDeviceApplicationList(DeviceApplicationListCallback callback);

  interface CreateDeviceApplicationListCallback {
    void onDeviceApplicationListCreated(boolean success);

    void onError();
  }

  interface DeviceApplicationListCallback {
    void onDeviceApplicationListLoaded(List<DeviceApplicationEntity> deviceApplicationEntityList);

    void onError();
  }
}
