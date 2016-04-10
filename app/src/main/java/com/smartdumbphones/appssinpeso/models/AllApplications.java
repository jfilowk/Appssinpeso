package com.smartdumbphones.appssinpeso.models;

import java.util.List;

public class AllApplications {

  private int totalNumApplications = 0;
  private long totalSizeApplications = 0;
  private long totalSizeCache = 0;
  private List<ApplicationInfoStruct> listApplications;

  public AllApplications(int totalNumApplications, long totalSizeApplications,
      long totalSizeCache, List<ApplicationInfoStruct> listApplications) {
    this.totalNumApplications = totalNumApplications;
    this.totalSizeApplications = totalSizeApplications;
    this.totalSizeCache = totalSizeCache;
    this.listApplications = listApplications;
  }

  public AllApplications() {

  }

  public static class Builder {
    private int totalNumApplications;
    private long totalSizeApplications;
    private long totalSizeCache;
    private List<ApplicationInfoStruct> listApplications;

    public Builder setTotalNumApplications(int totalNumApplications) {
      this.totalNumApplications = totalNumApplications;
      return this;
    }

    public Builder setTotalSizeApplications(long totalSizeApplications) {
      this.totalSizeApplications = totalSizeApplications;
      return this;
    }

    public Builder setTotalSizeCache(long totalSizeCache) {
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

  public long getTotalSizeApplications() {
    return totalSizeApplications;
  }

  public long getTotalSizeCache() {
    return totalSizeCache;
  }

  public List<ApplicationInfoStruct> getListApplications() {
    return listApplications;
  }
}
