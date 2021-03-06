package com.smartdumbphones.appssinpeso.ui.device_applications;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
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
import com.smartdumbphones.appssinpeso.models.ApplicationInfoStruct;
import com.smartdumbphones.appssinpeso.ui.BaseActivity;
import com.smartdumbphones.appssinpeso.ui.device_applications.adapters.ApplicationInstalledAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class DeviceApplicationInstalledActivity extends BaseActivity
    implements DeviceApplicationInstalledPresenter.DeviceApplicationInstalledView {

  @Bind(R.id.recycler_list) RecyclerView recyclerList;
  @Inject DeviceApplicationInstalledPresenter presenter;
  @Inject Context context;
  private ProgressDialog progressDialog;
  private ApplicationInstalledAdapter adapter;
  private InstalledComponent component;

  private AllApplications allApplications;
  private List<ApplicationInfoStruct> applicationInfoStructList;

  private boolean showSystemPackage = true;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.list_cache_activity);
    ButterKnife.bind(this);

    this.allApplications = new AllApplications();
    applicationInfoStructList = new ArrayList<>();

    recyclerList.setLayoutManager(new LinearLayoutManager(this));

    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    ActivityCompat.invalidateOptionsMenu(this);

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

  @Override public boolean onPrepareOptionsMenu(Menu menu) {
    if (showSystemPackage) {
      showSystemPackage = false;
      menu.findItem(R.id.display_system_apps).setTitle(R.string.hide_system_apps);
    } else {
      showSystemPackage = true;
      menu.findItem(R.id.display_system_apps).setTitle(R.string.show_system_apps);
    }

    return super.onPrepareOptionsMenu(menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int itemId = item.getItemId();
    switch (itemId) {
      case android.R.id.home:
        onBackPressed();
        break;
      case R.id.display_system_apps:
        if (showSystemPackage) {
          presenter.filterSystemPackage();
        } else {
          presenter.filterSystemPackage();
        }
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
    this.applicationInfoStructList.addAll(allApplications.getListApplications());
    if (adapter == null) {
      adapter = new ApplicationInstalledAdapter(context, this.allApplications);
      recyclerList.setAdapter(adapter);
    } else {
      adapter.refreshData(this.allApplications);
    }
  }

  @Override public void filterSystemApplications() {

    if (showSystemPackage) {
      this.allApplications.setListApplications(this.applicationInfoStructList);
      adapter.refreshData(this.allApplications);
    } else {
      List<ApplicationInfoStruct> listSystemApplications = new ArrayList<>();

      for (ApplicationInfoStruct listApplication : this.allApplications.getListApplications()) {
        if (listApplication.isSystem()) {
          listSystemApplications.add(listApplication);
        }
      }

      int totalNumApplications = listSystemApplications.size();
      AllApplications allApplicationsSystems =
          new AllApplications.Builder().setTotalNumApplications(totalNumApplications)
              .setListApplications(listSystemApplications)
              .build();

      allApplicationsSystems.recalculateTotals();

      adapter.refreshData(allApplicationsSystems);
    }
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    presenter.onDestroy();
  }

  @Override public void showError(String error) {
    Toast.makeText(this, R.string.no_packages_found_toast, Toast.LENGTH_SHORT).show();
  }

  private void initProgressDialog() {
    progressDialog = new ProgressDialog(this);
    progressDialog.setIcon(android.R.drawable.alert_dark_frame);
    progressDialog.setMessage(getString(R.string.loading_progress));
    progressDialog.setCancelable(false);
  }
}
