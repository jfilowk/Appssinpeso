package com.smartdumbphones.appssinpeso.datasize;

import com.smartdumbphones.appssinpeso.Appssinpeso;
import com.smartdumbphones.appssinpeso.datasize.models.ApplicationInfoStruct;
import java.util.List;

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

  @Override public void onSuccess(List<ApplicationInfoStruct> applicationInfoStructList) {
    view.hideLoading();
    view.displayListCache(applicationInfoStructList);
  }

  @Override public void onError() {

  }
}
