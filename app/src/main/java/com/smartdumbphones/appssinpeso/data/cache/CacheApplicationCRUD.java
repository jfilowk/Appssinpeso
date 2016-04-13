package com.smartdumbphones.appssinpeso.data.cache;

import android.database.Cursor;
import com.smartdumbphones.appssinpeso.data.entity.DeviceApplicationEntity;
import java.util.List;

public interface CacheApplicationCRUD {

  boolean insertListDeviceApplicationEntity(List<DeviceApplicationEntity> deviceApplicationEntityList);

  Cursor obtainListDeviceApplicationEntity();

}
