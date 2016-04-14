package com.smartdumbphones.appssinpeso.data.entity.mapper;

import com.smartdumbphones.appssinpeso.data.entity.DeviceApplicationEntity;
import com.smartdumbphones.appssinpeso.models.ApplicationInfoStruct;
import java.util.ArrayList;
import java.util.List;

public class DeviceApplicationDataMapper {

  public ApplicationInfoStruct transform(DeviceApplicationEntity entity) {
    ApplicationInfoStruct applicationInfoStruct = null;

    if (entity != null) {
      applicationInfoStruct = new ApplicationInfoStruct();
      applicationInfoStruct.setPname(entity.getPackageName());
      applicationInfoStruct.setApkSize(entity.getApkSize());
      applicationInfoStruct.setCacheSize(entity.getCacheSize());
      applicationInfoStruct.setDataSize(entity.getDataSize());
    }

    return applicationInfoStruct;
  }

  public List<ApplicationInfoStruct> transform(
      List<DeviceApplicationEntity> deviceApplicationEntityList) {
    List<ApplicationInfoStruct> applicationInfoStructList = new ArrayList<>();
    ApplicationInfoStruct applicationInfoStruct;
    if (deviceApplicationEntityList != null) {
      for (DeviceApplicationEntity deviceApplicationEntity : deviceApplicationEntityList) {
        applicationInfoStruct = transform(deviceApplicationEntity);
        if (applicationInfoStruct != null) {
          applicationInfoStructList.add(applicationInfoStruct);
        }
      }
    }
    return applicationInfoStructList;
  }

  public DeviceApplicationEntity convert(ApplicationInfoStruct applicationInfoStruct) {
    DeviceApplicationEntity deviceApplicationEntity = null;
    if (applicationInfoStruct != null) {
      deviceApplicationEntity = new DeviceApplicationEntity();
      deviceApplicationEntity.setPackageName(applicationInfoStruct.getPname());
      deviceApplicationEntity.setApkSize(applicationInfoStruct.getApkSize());
      deviceApplicationEntity.setCacheSize(applicationInfoStruct.getCacheSize());
      deviceApplicationEntity.setDataSize(applicationInfoStruct.getDataSize());
    }
    return deviceApplicationEntity;
  }

  public List<DeviceApplicationEntity> convert(
      List<ApplicationInfoStruct> applicationInfoStructList) {
    List<DeviceApplicationEntity> deviceApplicationEntityList = new ArrayList<>();
    DeviceApplicationEntity deviceApplicationEntity;

    if (applicationInfoStructList != null) {
      for (ApplicationInfoStruct applicationInfoStruct : applicationInfoStructList) {
        deviceApplicationEntity = convert(applicationInfoStruct);
        if (deviceApplicationEntity != null) {
          deviceApplicationEntityList.add(deviceApplicationEntity);
        }
      }
    }
    return deviceApplicationEntityList;
  }
}


