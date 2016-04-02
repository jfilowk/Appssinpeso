package com.smartdumbphones.appssinpeso.datasize;

import com.smartdumbphones.appssinpeso.datasize.models.ApplicationInfoStruct;
import java.util.List;

public interface ListCacheView {
  void showLoading();

  void hideLoading();

  void displayListCache(List<ApplicationInfoStruct> applicationInfoStructList, float totalCacheSize);

  void showError(String error);
}
