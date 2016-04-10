package com.smartdumbphones.appssinpeso.ui.device_applications;

import com.smartdumbphones.appssinpeso.models.AllApplications;

public interface DeviceApplicationInstalledPresenter {
  void getPackages();

  void getDetailPackage();

  void filterSystemPackage(boolean getSystemPackage);

  void onDestroy();

  void attachView(DeviceApplicationInstalledView view);

  interface DeviceApplicationInstalledView {
    void showLoading();

    void hideLoading();

    void displayListCache(AllApplications allApplications);

    void showError(String error);
  }
}
