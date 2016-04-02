package com.smartdumbphones.appssinpeso.datasize;

import com.smartdumbphones.appssinpeso.Appssinpeso;
import com.smartdumbphones.appssinpeso.datasize.models.AllApplications;

public class ListCachePresenterImpl
    implements ListCachePresenter, ApplicationsManager.OnApplicationsListener {

  private ListCacheView view;

  public ListCachePresenterImpl(ListCacheView view) {
    this.view = view;
  }

  @Override public void getPackages() {
    Appssinpeso.getApplicationsManager().attachOnApplicationListener(this);
    Appssinpeso.getApplicationsManager().start();
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
