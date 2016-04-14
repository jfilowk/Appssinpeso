package com.smartdumbphones.appssinpeso.data.repository.datasource;

import com.smartdumbphones.appssinpeso.data.cache.CacheApplicationCRUD;
import com.smartdumbphones.appssinpeso.data.entity.DeviceApplicationEntity;
import java.util.List;
import javax.inject.Inject;

public class DiskDeviceApplicationStore implements DeviceApplicationStore {

  private final CacheApplicationCRUD cacheApplicationCRUD;

  @Inject
  public DiskDeviceApplicationStore(CacheApplicationCRUD cacheApplicationCRUD) {
    this.cacheApplicationCRUD = cacheApplicationCRUD;
  }

  @Override
  public void createDeviceApplicationList(List<DeviceApplicationEntity> deviceApplicationEntityList,
      CreateDeviceApplicationListCallback callback) {
    boolean insert =
        cacheApplicationCRUD.insertListDeviceApplicationEntity(deviceApplicationEntityList);

    if (insert) {
      callback.onDeviceApplicationListCreated(true);
    } else {
      callback.onError();
    }
  }

  @Override public void getDeviceApplicationList(final DeviceApplicationListCallback callback) {
    cacheApplicationCRUD.obtainListDeviceApplicationEntity(
        new CacheApplicationCRUD.DeviceApplicationListCallback() {
          @Override public void onDeviceApplicationListLoaded(
              List<DeviceApplicationEntity> deviceApplicationEntityList) {
            callback.onDeviceApplicationListLoaded(deviceApplicationEntityList);
          }

          @Override public void onError() {
            callback.onError();
          }
        });
  }
}
