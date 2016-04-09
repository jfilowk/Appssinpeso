package com.smartdumbphones.appssinpeso.appdetails;

import com.smartdumbphones.appssinpeso.ui.login.LoginInteractor;
import com.smartdumbphones.appssinpeso.ui.login.LoginPresenter;
import com.smartdumbphones.appssinpeso.ui.login.LoginPresenterImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

public class LoginPresenterTest {

  @Mock private LoginInteractor loginInteractor;

  @Mock private LoginPresenter.LoginView loginView;

  @Captor private ArgumentCaptor<LoginInteractor.OnLoginFinishedListener> listenerArgumentCaptor;

  private LoginPresenter presenter;
  public static final String EMAIL_LOGIN = "jjj@am.com";

  @Before public void setupLoginPresenter() {
    MockitoAnnotations.initMocks(this);

    presenter = new LoginPresenterImpl(loginInteractor);
    presenter.attachView(loginView);
  }

  @Test public void clickOnAnalize_verifyCredentialsIncorrectEmail() {
    presenter.validateCredentials(EMAIL_LOGIN);

    verify(loginView).showProgress();
    verify(loginInteractor).login(eq(EMAIL_LOGIN), listenerArgumentCaptor.capture());
    listenerArgumentCaptor.getValue().onErrorCredentials();
    verify(loginView).hideProgress();
    verify(loginView).showErrorCredentials();
  }

  @Test public void clickOnAnalize_verifyCredentialsCorrectEmail() {
    presenter.validateCredentials(EMAIL_LOGIN);

    verify(loginView).showProgress();
    verify(loginInteractor).login(eq(EMAIL_LOGIN), listenerArgumentCaptor.capture());
    listenerArgumentCaptor.getValue().onSuccess();
    verify(loginView).hideProgress();
    verify(loginView).goListApplications();
  }
}
