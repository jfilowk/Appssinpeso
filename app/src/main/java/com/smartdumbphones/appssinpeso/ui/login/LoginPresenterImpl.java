package com.smartdumbphones.appssinpeso.ui.login;

import javax.inject.Inject;

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnLoginFinishedListener {

  private LoginInteractor loginInteractor;
  private LoginView loginView;

  @Inject public LoginPresenterImpl(LoginInteractor loginInteractor) {
    this.loginInteractor = loginInteractor;
  }

  @Override public void attachView(LoginView view) {
    this.loginView = view;
  }

  @Override public void validateCredentials(String email) {
    loginInteractor.login(email, this);
    loginView.showProgress();
  }

  @Override public void onErrorCredentials() {
    loginView.showErrorCredentials();
    loginView.hideProgress();
  }

  @Override public void onSuccess() {
    loginView.goListApplications();
    loginView.hideProgress();
  }
}
