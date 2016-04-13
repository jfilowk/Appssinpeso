package com.smartdumbphones.appssinpeso.data.repository.datasource;

import com.smartdumbphones.appssinpeso.models.ApplicationInfoStruct;
import java.util.List;

public interface DeviceApplicationStore {

  interface DeviceApplicationListCallback {
    void onDeviceApplicationListLoaded(List<ApplicationInfoStruct> applicationInfoStructList);

    void onError();
  }

}
