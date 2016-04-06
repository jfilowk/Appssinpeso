package com.smartdumbphones.appssinpeso.ui.login;

public interface LoginPresenter {
  void validateCredentials(String emai);

  void attachView(LoginView view);

  interface LoginView {
    void showProgress();

    void hideProgress();

    void showErrorCredentials();

    void goListApplications();
  }
}
