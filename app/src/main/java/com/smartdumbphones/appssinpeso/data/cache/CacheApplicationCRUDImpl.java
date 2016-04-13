package com.smartdumbphones.appssinpeso.data.cache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.smartdumbphones.appssinpeso.data.entity.DeviceApplicationEntity;
import com.smartdumbphones.appssinpeso.data.entity.mapper.DeviceApplicationDBMapper;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CacheApplicationCRUDImpl extends CacheApplicationDB implements CacheApplicationCRUD {

  private DeviceApplicationDBMapper deviceApplicationDBMapper;

  public CacheApplicationCRUDImpl(Context context,
      DeviceApplicationDBMapper deviceApplicationDBMapper) {
    super(context);
    this.deviceApplicationDBMapper = deviceApplicationDBMapper;
  }

  @Override public boolean insertListDeviceApplicationEntity(
      List<DeviceApplicationEntity> deviceApplicationEntityList) {
    boolean success = true;
    SQLiteDatabase db = this.getWritableDatabase();
    if (deviceApplicationEntityList != null) {
      for (DeviceApplicationEntity deviceApplicationEntity : deviceApplicationEntityList) {
        long insert =
            db.insert(APPLICATIONS_TABLE, null, bindApplicationInfoStruct(deviceApplicationEntity));
        if (insert > 0) {
          success = false;
          break;
        }
      }
    }
    return success;
  }

  @Override public void obtainListDeviceApplicationEntity(DeviceApplicationListCallback callback) {
    SQLiteDatabase db = this.getReadableDatabase();
    String selectQuery = "SELECT * FROM " + APPLICATIONS_TABLE;
    try {
      Cursor cursor = db.rawQuery(selectQuery, null);
      List<DeviceApplicationEntity> deviceApplicationEntityList =
          deviceApplicationDBMapper.transformToListEntity(cursor);
      callback.onDeviceApplicationListLoaded(deviceApplicationEntityList);
    } catch (Exception e) {
      callback.onError();
    } finally {
      db.close();
    }
  }

  private ContentValues bindApplicationInfoStruct(DeviceApplicationEntity deviceApplicationEntity) {
    ContentValues values = new ContentValues();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();

    values.put("package_name", deviceApplicationEntity.getPackageName());
    values.put("apk_size", deviceApplicationEntity.getApkSize());
    values.put("cache_size", deviceApplicationEntity.getCacheSize());
    values.put("data_size", deviceApplicationEntity.getDataSize());
    values.put("created_at", dateFormat.format(date));

    return values;
  }
}