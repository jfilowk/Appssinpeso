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
  private List<ApplicationInfoStruct> listApplicationsCache;

  public AllApplications(int totalNumApplications, long totalSizeApplications, long totalSizeCache,
      int totalNumApplicationsVariance, long totalSizeApplicationsVariance,
      long totalSizeCacheVariance, List<ApplicationInfoStruct> listApplications,
      List<ApplicationInfoStruct> listApplicationsCache) {
    this.totalNumApplications = totalNumApplications;
    this.totalSizeApplications = totalSizeApplications;
    this.totalSizeCache = totalSizeCache;
    this.totalNumApplicationsVariance = totalNumApplicationsVariance;
    this.totalSizeApplicationsVariance = totalSizeApplicationsVariance;
    this.totalSizeCacheVariance = totalSizeCacheVariance;
    this.listApplications = listApplications;
    this.listApplicationsCache = listApplicationsCache;
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

  public List<ApplicationInfoStruct> getListApplicationsCache() {
    return listApplicationsCache;
  }

  public static class Builder {
    private int totalNumApplications;
    private long totalSizeApplications;
    private long totalSizeCache;
    private int totalNumApplicationsVariance;
    private long totalSizeApplicationsVariance;
    private long totalSizeCacheVariance;
    private List<ApplicationInfoStruct> listApplications;
    private List<ApplicationInfoStruct> listApplicationsCache;

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

    public Builder setListApplicationsCache(List<ApplicationInfoStruct> listApplicationsCache) {
      this.listApplicationsCache = listApplicationsCache;
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
          listApplications, listApplicationsCache);
    }
  }
}
