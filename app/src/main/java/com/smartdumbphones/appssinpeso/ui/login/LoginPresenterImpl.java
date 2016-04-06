package com.smartdumbphones.appssinpeso.ui.login;

import javax.inject.Inject;

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnLoginFinishedListener {
  private static String TAG = "LoginPresenter";
  // Instance of interfaces
  private LoginInteractor loginInteractor;
  private LoginView loginView;

  @Inject public LoginPresenterImpl(LoginInteractor loginInteractor) {
    this.loginInteractor = loginInteractor;
  }

  @Override public void attachView(LoginView view) {
    this.loginView = view;
  }

  @Override public void validateCredentials(String email) {
    loginView.showProgress();
    loginInteractor.login(email, this);
  }

  @Override public void onErrorCredentials() {
    loginView.showErrorCredentials();
    loginView.hideProgress();
  }

  @Override public void onSuccess() {
    loginView.hideProgress();
    loginView.goListApplications();
  }
}
