package com.smartdumbphones.appssinpeso.datasize;

import com.smartdumbphones.appssinpeso.datasize.models.AllApplications;
import com.smartdumbphones.appssinpeso.datasize.models.ApplicationInfoStruct;
import java.util.List;

public interface ListCacheView {
  void showLoading();

  void hideLoading();

  void displayListCache(List<ApplicationInfoStruct> applicationInfoStructList, AllApplications allApplications);

  void showError(String error);
}
