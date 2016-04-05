package com.smartdumbphones.appssinpeso.ui.login;

public interface LoginInteractor {

  interface OnLoginFinishedListener {
    void onErrorCredentials();

    void onSuccess();
  }

  void login(String email, OnLoginFinishedListener listener);
}
