package com.smartdumbphones.appssinpeso.data.repository;

import com.smartdumbphones.appssinpeso.data.entity.DeviceApplicationEntity;
import com.smartdumbphones.appssinpeso.data.entity.mapper.DeviceApplicationDataMapper;
import com.smartdumbphones.appssinpeso.data.repository.datasource.DeviceApplicationStore;
import com.smartdumbphones.appssinpeso.data.repository.datasource.DiskDeviceApplicationStore;
import com.smartdumbphones.appssinpeso.internal.manager.ApplicationInfoStructRepository;
import com.smartdumbphones.appssinpeso.models.ApplicationInfoStruct;
import java.util.List;
import javax.inject.Inject;

public class ApplicationInfoStructRepositoryImpl implements ApplicationInfoStructRepository {

  private final DiskDeviceApplicationStore diskDeviceApplicationStore;
  private final DeviceApplicationDataMapper deviceApplicationDataMapper;

  @Inject
  public ApplicationInfoStructRepositoryImpl(DiskDeviceApplicationStore diskDeviceApplicationStore,
      DeviceApplicationDataMapper deviceApplicationDataMapper) {
    this.diskDeviceApplicationStore = diskDeviceApplicationStore;
    this.deviceApplicationDataMapper = deviceApplicationDataMapper;
  }

  @Override
  public void createDeviceApplicationList(List<ApplicationInfoStruct> applicationInfoStructList,
      final CreateDeviceApplicationListCallback callback) {

    List<DeviceApplicationEntity> deviceApplicationEntityList =
        deviceApplicationDataMapper.convert(applicationInfoStructList);
    diskDeviceApplicationStore.createDeviceApplicationList(deviceApplicationEntityList,
        new DeviceApplicationStore.CreateDeviceApplicationListCallback() {
          @Override public void onDeviceApplicationListCreated(boolean success) {
            if (success) {
              callback.onCreateDeviceApplicationListCallback(true);
            } else {
              callback.onError();
            }
          }

          @Override public void onError() {
            callback.onError();
          }
        });
  }

  @Override public void getDeviceApplicationList(final DeviceApplicationListCallback callback) {
    diskDeviceApplicationStore.getDeviceApplicationList(
        new DeviceApplicationStore.DeviceApplicationListCallback() {
          @Override public void onDeviceApplicationListLoaded(
              List<DeviceApplicationEntity> deviceApplicationEntityList) {
            if (deviceApplicationEntityList != null) {
              List<ApplicationInfoStruct> applicationInfoStructList =
                  deviceApplicationDataMapper.transform(deviceApplicationEntityList);
              callback.onDeviceApplicationList(applicationInfoStructList);
            } else {
              callback.onError();
            }
          }

          @Override public void onError() {
            callback.onError();
          }
        });
  }
}
