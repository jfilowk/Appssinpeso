package com.smartdumbphones.appssinpeso.datasize.models;

public class AllApplications {
  private int totalNumApplications = 0;
  private float totalSizeApplications = 0;
  private float totalSizeCache = 0;

  public AllApplications(int totalNumApplications, float totalSizeApplications,
      float totalSizeCache) {
    this.totalNumApplications = totalNumApplications;
    this.totalSizeApplications = totalSizeApplications;
    this.totalSizeCache = totalSizeCache;
  }

  public static class Builder {
    private int totalNumApplications;
    private float totalSizeApplications;
    private float totalSizeCache;

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

    public AllApplications build() {
      return new AllApplications(totalNumApplications, totalSizeApplications, totalSizeCache);
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
}
