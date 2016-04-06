package com.smartdumbphones.appssinpeso.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.smartdumbphones.appssinpeso.R;
import com.smartdumbphones.appssinpeso.internal.di.component.DaggerLoginComponent;
import com.smartdumbphones.appssinpeso.internal.di.component.LoginComponent;
import com.smartdumbphones.appssinpeso.ui.BaseActivity;
import com.smartdumbphones.appssinpeso.ui.device_applications.DeviceApplicationInstalledActivity;
import javax.inject.Inject;

public class LoginActivity extends BaseActivity
    implements LoginPresenter.LoginView, View.OnClickListener {

  @Bind(R.id.analyze_button) Button btnAnalyze;
  @Bind(R.id.email_login_text) EditText txtEmailLogin;

  @Inject LoginPresenter presenter;

  private LoginComponent component;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login_activity);

    // TODO: Improve it
    getSupportActionBar().hide();

    initializeInjectors();

    presenter.attachView(this);

    ButterKnife.bind(this);
    // TODO: Remove when release
    txtEmailLogin.setText("hola@hola.com");
    btnAnalyze.setOnClickListener(this);
  }

  private void initializeInjectors() {
    if (component == null) {
      component = DaggerLoginComponent.builder()
          .applicationComponent(getApplicationComponent())
          .activityModule(getActivityModule())
          .build();
    }

    component.inject(this);
  }

  @Override public void showProgress() {
    btnAnalyze.setEnabled(false);
  }

  @Override public void hideProgress() {
    btnAnalyze.setEnabled(true);
  }

  @Override public void showErrorCredentials() {
    Toast.makeText(this, "Check your email!", Toast.LENGTH_SHORT).show();
  }

  @Override public void goListApplications() {
    startActivity(new Intent(this, DeviceApplicationInstalledActivity.class));
  }

  @Override public void onClick(View v) {
    presenter.validateCredentials(txtEmailLogin.getText().toString());
  }
}
