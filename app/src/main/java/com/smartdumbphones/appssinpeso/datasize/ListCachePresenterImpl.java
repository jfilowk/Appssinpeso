package com.smartdumbphones.appssinpeso.datasize;

public class ListCachePresenterImpl implements ListCachePresenter {

  private ListCacheView view;

  public ListCachePresenterImpl(ListCacheView view) {
    this.view = view;
  }

  @Override public void getPackages() {
    view.showLoading();
  }

  @Override public void getDetailPackage() {

  }
}
