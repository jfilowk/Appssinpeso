package com.smartdumbphones.appssinpeso.models;

import java.util.List;

public class AllApplications {

  private int totalNumApplications = 0;
  private float totalSizeApplications = 0;
  private float totalSizeCache = 0;
  private List<ApplicationInfoStruct> listApplications;

  public AllApplications(int totalNumApplications, float totalSizeApplications,
      float totalSizeCache, List<ApplicationInfoStruct> listApplications) {
    this.totalNumApplications = totalNumApplications;
    this.totalSizeApplications = totalSizeApplications;
    this.totalSizeCache = totalSizeCache;
    this.listApplications = listApplications;
  }

  public static class Builder {
    private int totalNumApplications;
    private float totalSizeApplications;
    private float totalSizeCache;
    private List<ApplicationInfoStruct> listApplications;

    public Builder setTotalNumApplications(int totalNumApplications) {
      this.totalNumApplications = totalNumApplications;
      return this;
    }

    public Builder setTotalSizeApplications(float totalSizeApplications) {
      this.totalSizeApplications = totalSizeApplications;
      return this;
    }

    public Builder setTotalSizeCache(float totalSizeCache) {
      this.totalSizeCache = totalSizeCache;
      return this;
    }

    public Builder setListApplications(List<ApplicationInfoStruct> listApplications) {
      this.listApplications = listApplications;
      return this;
    }

    public AllApplications build() {
      return new AllApplications(totalNumApplications, totalSizeApplications, totalSizeCache,
          listApplications);
    }
  }

  public int getTotalNumApplications() {
    return totalNumApplications;
  }

  public float getTotalSizeApplications() {
    return totalSizeApplications;
  }

  public float getTotalSizeCache() {
    return totalSizeCache;
  }

  public List<ApplicationInfoStruct> getListApplications() {
    return listApplications;
  }
}
