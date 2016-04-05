package com.smartdumbphones.appssinpeso.internal.di.module;

import com.smartdumbphones.appssinpeso.internal.di.PerActivity;
import com.smartdumbphones.appssinpeso.login.LoginInteractor;
import com.smartdumbphones.appssinpeso.login.LoginInteractorImpl;
import com.smartdumbphones.appssinpeso.login.LoginPresenter;
import com.smartdumbphones.appssinpeso.login.LoginPresenterImpl;
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
