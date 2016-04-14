package com.smartdumbphones.appssinpeso.data.entity.mapper;

import android.database.Cursor;
import android.support.annotation.NonNull;
import com.smartdumbphones.appssinpeso.data.entity.DeviceApplicationEntity;
import java.util.ArrayList;
import java.util.List;

public class DeviceApplicationDBMapper {

  public static final int PACKAGE_INDEX = 1;
  public static final int APK_INDEX = 2;
  public static final int CACHE_INDEX = 3;
  public static final int DATA_INDEX = 4;

  public DeviceApplicationEntity transformToEntity(Cursor cursor) {
    DeviceApplicationEntity deviceApplicationEntity = null;

    while (cursor.moveToNext()) {
      deviceApplicationEntity = getDeviceApplicationEntityCursor(cursor);
    }

    return deviceApplicationEntity;
  }

  public List<DeviceApplicationEntity> transformToListEntity(Cursor cursor) {
    List<DeviceApplicationEntity> deviceApplicationEntityList = new ArrayList<>();
    DeviceApplicationEntity deviceApplicationEntity;

    while (cursor.moveToNext()) {
      deviceApplicationEntity = getDeviceApplicationEntityCursor(cursor);
      deviceApplicationEntityList.add(deviceApplicationEntity);
    }

    return deviceApplicationEntityList;
  }

  @NonNull private DeviceApplicationEntity getDeviceApplicationEntityCursor(Cursor cursor) {
    DeviceApplicationEntity deviceApplicationEntity;
    deviceApplicationEntity = new DeviceApplicationEntity();
    deviceApplicationEntity.setPackageName(cursor.getString(PACKAGE_INDEX));
    deviceApplicationEntity.setApkSize(cursor.getLong(APK_INDEX));
    deviceApplicationEntity.setCacheSize(cursor.getLong(CACHE_INDEX));
    deviceApplicationEntity.setDataSize(cursor.getLong(DATA_INDEX));
    return deviceApplicationEntity;
  }
}
