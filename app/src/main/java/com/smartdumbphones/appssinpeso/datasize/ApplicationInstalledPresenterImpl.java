package com.smartdumbphones.appssinpeso.datasize;

import com.smartdumbphones.appssinpeso.datasize.models.AllApplications;
import javax.inject.Inject;

public class ApplicationInstalledPresenterImpl
    implements ApplicationInstalledPresenter, ApplicationsManager.OnApplicationsListener {

  private ApplicationInstalledView view;
  private ApplicationsManager applicationsManager;

  @Inject public ApplicationInstalledPresenterImpl(ApplicationsManager applicationsManager) {
    this.applicationsManager = applicationsManager;
  }

  @Override public void getPackages() {
    applicationsManager.attachOnApplicationListener(this);
    applicationsManager.start();
    view.showLoading();
  }

  @Override public void getDetailPackage() {

  }

  @Override public void onDestroy() {
    applicationsManager.stop();
  }

  @Override public void attachView(ApplicationInstalledView view) {
    this.view = view;
  }

  @Override public void onSuccess(AllApplications allApplications) {
    view.hideLoading();
    view.displayListCache(allApplications);
  }

  @Override public void onError() {

  }
}
