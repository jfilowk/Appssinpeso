package com.smartdumbphones.appssinpeso.datasize;

public interface ApplicationInstalledPresenter {
  void getPackages();

  void getDetailPackage();

  void onDestroy();

  void attachView(ApplicationInstalledView view);

}
