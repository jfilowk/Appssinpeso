package com.smartdumbphones.appssinpeso.models;

import java.util.List;

public class AllApplications {

  private int totalNumApplications = 0;
  private long totalSizeApplications = 0;
  private long totalSizeCache = 0;
  private int totalNumApplicationsVariance;
  private long totalSizeApplicationsVariance;
  private long totalSizeCacheVariance;
  private List<ApplicationInfoStruct> listApplications;

  public AllApplications(int totalNumApplications, long totalSizeApplications, long totalSizeCache,
      int totalNumApplicationsVariance, long totalSizeApplicationsVariance,
      long totalSizeCacheVariance, List<ApplicationInfoStruct> listApplications) {
    this.totalNumApplications = totalNumApplications;
    this.totalSizeApplications = totalSizeApplications;
    this.totalSizeCache = totalSizeCache;
    this.totalNumApplicationsVariance = totalNumApplicationsVariance;
    this.totalSizeApplicationsVariance = totalSizeApplicationsVariance;
    this.totalSizeCacheVariance = totalSizeCacheVariance;
    this.listApplications = listApplications;
  }

  public AllApplications() {

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

  public int getTotalNumApplicationsVariance() {
    return totalNumApplicationsVariance;
  }

  public long getTotalSizeApplicationsVariance() {
    return totalSizeApplicationsVariance;
  }

  public long getTotalSizeCacheVariance() {
    return totalSizeCacheVariance;
  }

  public void recalculateTotals() {

    long totalCacheSize = 0;
    long totalApplicationsSize = 0;

    for (ApplicationInfoStruct listApplication : listApplications) {
      totalApplicationsSize += listApplication.getApkSize();
      totalCacheSize += listApplication.getCacheSize();
    }

    this.totalNumApplications = this.listApplications.size();
    this.totalSizeApplications = totalApplicationsSize;
    this.totalSizeCache = totalCacheSize;

  }

  public void setListApplications(List<ApplicationInfoStruct> listApplications) {
    this.listApplications = listApplications;
  }

  public static class Builder {
    private int totalNumApplications;
    private long totalSizeApplications;
    private long totalSizeCache;
    private int totalNumApplicationsVariance;
    private long totalSizeApplicationsVariance;
    private long totalSizeCacheVariance;
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

    public Builder setTotalNumApplicationsVariance(int totalNumApplicationsVariance) {
      this.totalNumApplicationsVariance = totalNumApplicationsVariance;
      return this;
    }

    public Builder setTotalSizeApplicationsVariance(long totalSizeApplicationsVariance) {
      this.totalSizeApplicationsVariance = totalSizeApplicationsVariance;
      return this;
    }

    public Builder setTotalSizeCacheVariance(long totalSizeCacheVariance) {
      this.totalSizeCacheVariance = totalSizeCacheVariance;
      return this;
    }

    public AllApplications build() {
      return new AllApplications(totalNumApplications, totalSizeApplications, totalSizeCache,
          totalNumApplicationsVariance, totalSizeApplicationsVariance, totalSizeCacheVariance,
          listApplications);
    }
  }
}
