package com.smartdumbphones.appssinpeso.datasize;

public interface ListCacheView {
  void showLoading();

  void hideLoading();

  void displayListCache();

  void showError(String error);
}
