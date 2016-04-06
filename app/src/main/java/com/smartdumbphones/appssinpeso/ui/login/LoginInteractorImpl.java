package com.smartdumbphones.appssinpeso.ui.login;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;
import com.smartdumbphones.appssinpeso.models.User;
import javax.inject.Inject;

public class LoginInteractorImpl implements LoginInteractor {

  @Inject public LoginInteractorImpl() {
  }

  @Override public void login(final String email, final OnLoginFinishedListener listener) {
    new Handler().post(new Runnable() {
      @Override public void run() {
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
          listener.onErrorCredentials();
        } else {
          User.getInstance().setEmail(email);
          listener.onSuccess();
        }
      }
    });
  }
}
