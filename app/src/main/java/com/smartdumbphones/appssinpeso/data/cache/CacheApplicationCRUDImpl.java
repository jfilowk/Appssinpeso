package com.smartdumbphones.appssinpeso.data.cache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.smartdumbphones.appssinpeso.models.ApplicationInfoStruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CacheApplicationCRUDImpl extends CacheApplicationDB implements CacheApplicationCRUD {

  public CacheApplicationCRUDImpl(Context context) {
    super(context);
  }

  @Override public boolean insertListDeviceApplication(
      List<ApplicationInfoStruct> applicationInfoStructList) {
    boolean success = true;
    if (applicationInfoStructList != null) {
      for (ApplicationInfoStruct applicationInfoStruct : applicationInfoStructList) {
        SQLiteDatabase db = this.getWritableDatabase();
        long insert =
            db.insert(APPLICATIONS_TABLE, null, bindApplicationInfoStruct(applicationInfoStruct));
        if (insert > 0) {
          success = false;
          break;
        }
      }
    }
    return success;
  }

  @Override public Cursor obtainListDeviceApplication() {
    SQLiteDatabase db = this.getReadableDatabase();
    String selectQuery = "SELECT * FROM " + APPLICATIONS_TABLE;

    return db.rawQuery(selectQuery, null);
  }

  @Override public long createCacheApplication(ApplicationInfoStruct applicationInfoStruct) {
    return 0;
  }

  @Override public long deleteCacheApplication() {
    return 0;
  }

  @Override public Cursor getApplication(String packageName) {
    return null;
  }

  @Override public Cursor getCacheApplications() {
    return null;
  }

  private ContentValues bindApplicationInfoStruct(ApplicationInfoStruct applicationInfoStruct) {
    ContentValues values = new ContentValues();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();

    values.put("package_name", applicationInfoStruct.getPname());
    values.put("apk_size", applicationInfoStruct.getApkSize());
    values.put("cache_size", applicationInfoStruct.getCacheSize());
    values.put("data_size", applicationInfoStruct.getDataSize());
    values.put("created_at", dateFormat.format(date));
    values.put("analyze", applicationInfoStruct.isSystem());

    return values;
  }
}