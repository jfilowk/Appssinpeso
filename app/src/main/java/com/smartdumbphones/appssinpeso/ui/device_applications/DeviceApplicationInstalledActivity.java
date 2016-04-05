package com.smartdumbphones.appssinpeso.ui.device_applications;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.smartdumbphones.appssinpeso.BaseActivity;
import com.smartdumbphones.appssinpeso.R;
import com.smartdumbphones.appssinpeso.internal.di.component.DaggerInstalledComponent;
import com.smartdumbphones.appssinpeso.internal.di.component.InstalledComponent;
import com.smartdumbphones.appssinpeso.models.AllApplications;
import com.smartdumbphones.appssinpeso.ui.device_applications.adapters.ApplicationInstalledAdapter;
import javax.inject.Inject;

public class DeviceApplicationInstalledActivity extends BaseActivity
    implements ApplicationInstalledView {

  @Bind(R.id.recycler_list) RecyclerView recyclerList;

  private ProgressDialog progressDialog;
  private ApplicationInstalledAdapter adapter;

  @Inject DeviceApplicationInstalledPresenter presenter;

  private InstalledComponent component;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.list_cache_activity);
    ButterKnife.bind(this);

    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    initProgressDialog();
    initializeInjectors();

    presenter.attachView(this);
    presenter.getPackages();
  }

  private void initializeInjectors() {
    if (component == null) {
      component = DaggerInstalledComponent.builder()
          .applicationComponent(getApplicationComponent())
          .activityModule(getActivityModule())
          .build();
    }

    component.inject(this);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int itemId = item.getItemId();
    switch (itemId) {
      case android.R.id.home:
        onBackPressed();
        break;
    }
    return true;
  }

  @Override public void showLoading() {
    progressDialog.show();
  }

  @Override public void hideLoading() {
    progressDialog.dismiss();
  }

  @Override public void displayListCache(final AllApplications allApplications) {
    adapter = new ApplicationInstalledAdapter(allApplications);
    recyclerList.setAdapter(adapter);
    recyclerList.setLayoutManager(new LinearLayoutManager(this));
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    presenter.onDestroy();
  }

  @Override public void showError(String error) {
    Toast.makeText(this, "No packages found", Toast.LENGTH_SHORT).show();
  }

  private void initProgressDialog() {
    progressDialog = new ProgressDialog(this);
    progressDialog.setIcon(android.R.drawable.alert_dark_frame);
    progressDialog.setMessage("Loading...");
    progressDialog.setCancelable(false);
  }
}
