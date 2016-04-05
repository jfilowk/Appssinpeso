package com.smartdumbphones.appssinpeso.login;

public interface LoginPresenter {
  void validateCredentials(String emai);

  void attachView(LoginView view);
}
