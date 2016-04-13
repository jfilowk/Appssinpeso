package com.smartdumbphones.appssinpeso.datasource.cache;

import android.database.Cursor;
import com.smartdumbphones.appssinpeso.models.ApplicationInfoStruct;
import java.util.List;

public interface CacheApplicationCRUD {

  boolean insertListDeviceApplication(List<ApplicationInfoStruct> applicationInfoStructList);

  Cursor obtainListDeviceApplication();

  long createCacheApplication(ApplicationInfoStruct applicationInfoStruct);

  long deleteCacheApplication();

  //TODO: Improve via id like an integer
  Cursor getApplication(String packageName);

  Cursor getCacheApplications();
}
