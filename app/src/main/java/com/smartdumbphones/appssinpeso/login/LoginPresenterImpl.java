package com.smartdumbphones.appssinpeso.login;

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnLoginFinishedListener {
  private static String TAG = "LoginPresenter";
  // Instance of interfaces
  private LoginView loginView;
  private LoginInteractor loginInteractor;

  public LoginPresenterImpl(LoginView loginView) {
    this.loginView = loginView;
    this.loginInteractor = new LoginInteractorImpl();
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
