package com.smartdumbphones.appssinpeso.ui.login;

public interface LoginView {
  void showProgress();

  void hideProgress();

  void showErrorCredentials();

  void goListApplications();
}
