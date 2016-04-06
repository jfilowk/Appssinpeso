package com.smartdumbphones.appssinpeso.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.smartdumbphones.appssinpeso.ui.BaseActivity;
import com.smartdumbphones.appssinpeso.R;
import com.smartdumbphones.appssinpeso.internal.di.component.DaggerLoginComponent;
import com.smartdumbphones.appssinpeso.internal.di.component.LoginComponent;
import com.smartdumbphones.appssinpeso.ui.device_applications.DeviceApplicationInstalledActivity;
import javax.inject.Inject;

public class LoginActivity extends BaseActivity implements LoginView, View.OnClickListener {

  @Bind(R.id.analyze_button) Button btnAnalyze;
  @Bind(R.id.email_login_text) EditText txtEmailLogin;
  @Bind(R.id.progressBar) ProgressBar progressBar;

  @Inject LoginPresenter presenter;

  private LoginComponent component;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login_activity);

    initializeInjectors();

    presenter.attachView(this);

    ButterKnife.bind(this);
    txtEmailLogin.setText("hola@hola.com");
    btnAnalyze.setOnClickListener(this);
    progressBar.setVisibility(View.GONE);
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
    progressBar.setVisibility(View.VISIBLE);
  }

  @Override public void hideProgress() {
    progressBar.setVisibility(View.GONE);
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
