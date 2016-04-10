package com.smartdumbphones.appssinpeso.ui.device_applications;

import com.smartdumbphones.appssinpeso.internal.manager.ApplicationsManager;
import com.smartdumbphones.appssinpeso.models.AllApplications;
import javax.inject.Inject;

public class DeviceApplicationInstalledPresenterImpl
    implements DeviceApplicationInstalledPresenter, ApplicationsManager.OnApplicationsListener {

  private DeviceApplicationInstalledView view;
  private ApplicationsManager applicationsManager;

  @Inject public DeviceApplicationInstalledPresenterImpl(ApplicationsManager applicationsManager) {
    this.applicationsManager = applicationsManager;
  }

  @Override public void getPackages() {
    applicationsManager.attachOnApplicationListener(this);
    applicationsManager.start(true);
    view.showLoading();
  }

  @Override public void getDetailPackage() {

  }

  @Override public void filterSystemPackage() {
    applicationsManager.start(false);
    view.showLoading();
  }

  @Override public void onDestroy() {
    applicationsManager.stop();
  }

  @Override public void attachView(DeviceApplicationInstalledView view) {
    this.view = view;
  }

  @Override public void onSuccess(AllApplications allApplications) {
    view.hideLoading();
    view.displayListCache(allApplications);
  }

  @Override public void onError() {

  }
}
