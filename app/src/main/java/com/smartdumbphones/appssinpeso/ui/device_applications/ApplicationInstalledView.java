package com.smartdumbphones.appssinpeso.ui.device_applications;

import com.smartdumbphones.appssinpeso.models.AllApplications;

public interface ApplicationInstalledView {
  void showLoading();

  void hideLoading();

  void displayListCache(AllApplications allApplications);

  void showError(String error);
}
