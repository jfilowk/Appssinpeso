package com.smartdumbphones.appssinpeso.datasize;

import com.smartdumbphones.appssinpeso.datasize.models.AllApplications;

public interface ListCacheView {
  void showLoading();

  void hideLoading();

  void displayListCache(AllApplications allApplications);

  void showError(String error);
}
