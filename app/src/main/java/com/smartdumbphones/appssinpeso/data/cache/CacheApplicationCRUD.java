package com.smartdumbphones.appssinpeso.data.cache;

import com.smartdumbphones.appssinpeso.data.entity.DeviceApplicationEntity;
import java.util.List;

public interface CacheApplicationCRUD {

  boolean insertListDeviceApplicationEntity(
      List<DeviceApplicationEntity> deviceApplicationEntityList);

  void obtainListDeviceApplicationEntity(DeviceApplicationListCallback callback);

  interface DeviceApplicationListCallback {
    void onDeviceApplicationListLoaded(List<DeviceApplicationEntity> deviceApplicationEntityList);

    void onError();
  }
}
