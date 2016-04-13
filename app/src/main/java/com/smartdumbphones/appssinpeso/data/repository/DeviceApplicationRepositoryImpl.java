package com.smartdumbphones.appssinpeso.data.repository;

import com.smartdumbphones.appssinpeso.data.entity.mapper.DeviceApplicationDataMapper;
import com.smartdumbphones.appssinpeso.internal.manager.ApplicationInfoStructRepository;

public class DeviceApplicationRepositoryImpl implements ApplicationInfoStructRepository {

  // Factory DeviceDataStore
  private final DeviceApplicationDataMapper deviceApplicationDataMapper;

  public DeviceApplicationRepositoryImpl(DeviceApplicationDataMapper deviceApplicationDataMapper) {
    this.deviceApplicationDataMapper = deviceApplicationDataMapper;
  }

  @Override public void createDeviceApplicationList(CreateDeviceApplicationListCallback callback) {

  }

  @Override public void getDeviceApplicationList(DeviceApplicationListCallback callback) {

  }
}
