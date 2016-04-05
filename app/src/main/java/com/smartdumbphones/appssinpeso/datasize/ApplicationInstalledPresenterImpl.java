package com.smartdumbphones.appssinpeso.datasize;

import com.smartdumbphones.appssinpeso.Appssinpeso;
import com.smartdumbphones.appssinpeso.datasize.models.AllApplications;

public class ApplicationInstalledPresenterImpl
    implements ApplicationInstalledPresenter, ApplicationsManager.OnApplicationsListener {

  private ApplicationInstalledView view;
  private final ApplicationsManager applicationsManager;

  public ApplicationInstalledPresenterImpl(ApplicationInstalledView view,
      ApplicationsManager applicationsManager) {
    this.view = view;
    this.applicationsManager = applicationsManager;
  }

  @Override public void getPackages() {
    this.applicationsManager.attachOnApplicationListener(this);
    this.applicationsManager.start();
    view.showLoading();
  }

  @Override public void getDetailPackage() {

  }

  @Override public void onDestroy() {
    Appssinpeso.getApplicationsManager().stop();
  }

  @Override public void onSuccess(AllApplications allApplications) {
    view.hideLoading();
    view.displayListCache(allApplications);
  }

  @Override public void onError() {

  }
}
