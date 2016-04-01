package com.smartdumbphones.appssinpeso.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.smartdumbphones.appssinpeso.R;
import com.smartdumbphones.appssinpeso.datasize.ListCacheActivity;

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

  @Bind(R.id.analyze_button) Button btnAnalyze;
  @Bind(R.id.email_login_text) EditText txtEmailLogin;
  @Bind(R.id.progressBar) ProgressBar progressBar;
  private LoginPresenter presenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login_activity);
    ButterKnife.bind(this);
    txtEmailLogin.setText("hola@hola.com");
    btnAnalyze.setOnClickListener(this);
    progressBar.setVisibility(View.GONE);

    presenter = new LoginPresenterImpl(this);
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
    startActivity(new Intent(this, ListCacheActivity.class));
    finish();
  }

  @Override public void onClick(View v) {
    presenter.validateCredentials(txtEmailLogin.getText().toString());
  }
}
