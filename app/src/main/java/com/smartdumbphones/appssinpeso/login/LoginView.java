package com.smartdumbphones.appssinpeso.login;

public interface LoginView {
  void showProgress();

  void hideProgress();

  void showErrorCredentials();

  void goListApplications();
}
