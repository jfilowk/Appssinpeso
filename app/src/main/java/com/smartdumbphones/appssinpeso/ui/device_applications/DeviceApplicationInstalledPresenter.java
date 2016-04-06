package com.smartdumbphones.appssinpeso.ui.device_applications;

public interface DeviceApplicationInstalledPresenter {
  void getPackages();

  void getDetailPackage();

  void onDestroy();

  void attachView(ApplicationInstalledView view);

}
