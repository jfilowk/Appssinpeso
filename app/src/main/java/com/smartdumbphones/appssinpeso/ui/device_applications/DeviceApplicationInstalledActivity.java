package com.smartdumbphones.appssinpeso.ui.device_applications;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.smartdumbphones.appssinpeso.R;
import com.smartdumbphones.appssinpeso.internal.di.component.DaggerInstalledComponent;
import com.smartdumbphones.appssinpeso.internal.di.component.InstalledComponent;
import com.smartdumbphones.appssinpeso.models.AllApplications;
import com.smartdumbphones.appssinpeso.ui.BaseActivity;
import com.smartdumbphones.appssinpeso.ui.device_applications.adapters.ApplicationInstalledAdapter;
import javax.inject.Inject;

public class DeviceApplicationInstalledActivity extends BaseActivity
    implements DeviceApplicationInstalledPresenter.DeviceApplicationInstalledView {

  @Bind(R.id.recycler_list) RecyclerView recyclerList;

  private ProgressDialog progressDialog;
  private ApplicationInstalledAdapter adapter;

  @Inject DeviceApplicationInstalledPresenter presenter;

  private InstalledComponent component;
  private AllApplications allApplications;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.list_cache_activity);
    ButterKnife.bind(this);

    this.allApplications = new AllApplications();

    recyclerList.setLayoutManager(new LinearLayoutManager(this));

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

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.device_applications_menu, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int itemId = item.getItemId();
    switch (itemId) {
      case android.R.id.home:
        onBackPressed();
        break;
      case R.id.remove_system_apps:
        presenter.filterSystemPackage();
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
    this.allApplications = allApplications;
    if (adapter == null) {
      adapter = new ApplicationInstalledAdapter(this.allApplications);
      recyclerList.setAdapter(adapter);
    } else {
      adapter.refreshData(this.allApplications);
    }
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
