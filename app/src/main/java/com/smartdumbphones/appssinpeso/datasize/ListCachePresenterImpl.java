package com.smartdumbphones.appssinpeso.datasize;

import com.smartdumbphones.appssinpeso.Appssinpeso;

public class ListCachePresenterImpl implements ListCachePresenter,
    ApplicationsManager.OnApplicationsListener {

  private ListCacheView view;

  public ListCachePresenterImpl(ListCacheView view) {
    this.view = view;
  }

  @Override public void getPackages() {
    view.showLoading();
    Appssinpeso.getApplicationsManager().attachOnApplicationListener(this);
    Appssinpeso.getApplicationsManager().start();
  }

  @Override public void getDetailPackage() {

  }

  @Override public void onDestroy() {
    
  }

  @Override public void onSuccess() {

  }

  @Override public void onError() {

  }
}
