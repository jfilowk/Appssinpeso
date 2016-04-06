package com.smartdumbphones.appssinpeso.internal.di.module;

import com.smartdumbphones.appssinpeso.internal.di.PerActivity;
import com.smartdumbphones.appssinpeso.ui.login.LoginInteractor;
import com.smartdumbphones.appssinpeso.ui.login.LoginInteractorImpl;
import com.smartdumbphones.appssinpeso.ui.login.LoginPresenter;
import com.smartdumbphones.appssinpeso.ui.login.LoginPresenterImpl;
import dagger.Module;
import dagger.Provides;

@Module public class LoginModule {

  @Provides @PerActivity LoginPresenter provideLoginPresenter(LoginPresenterImpl presenter) {
    return presenter;
  }

  @Provides LoginInteractor provideLoginInteractor(LoginInteractorImpl interactor) {
    return interactor;
  }
}
