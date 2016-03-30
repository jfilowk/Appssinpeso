package com.smartdumbphones.appssinpeso.datasize;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.smartdumbphones.appssinpeso.R;
import com.smartdumbphones.appssinpeso.datasize.adapters.ListApplicationAdapter;
import com.smartdumbphones.appssinpeso.datasize.models.ApplicationInfoStruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class ListCacheActivity extends Activity implements ListenerErrorPackage {
  private static final int FETCH_PACKAGE_SIZE_COMPLETED = 100;
  private static final int ALL_PACKAGE_SIZE_COMPLETED = 200;
  private static final int NO_PACKAGE_FOUND = 300;

  IDataStatus onIDataStatus;

  @Bind(R.id.lbl_cache_size) TextView lbl_cache_size;
  @Bind(R.id.recycler_list) RecyclerView recyclerList;
  ProgressDialog pd;

  private long packageSize = 0;
  private long size = 0;
  AppDetails cAppDetails;
  private List<ApplicationInfoStruct> res;
  ListApplicationAdapter adapter;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.list_cache_activity);
    ButterKnife.bind(this);
    initGetPackageSize();
    recyclerList.setLayoutManager(new LinearLayoutManager(this));

    adapter = new ListApplicationAdapter(res);
    recyclerList.setAdapter(adapter);

    // clearCache();
  }

  private void initGetPackageSize() {
    size = 0;
    packageSize = 0;
    showProgress("Calculating Cache Size..!!!");
    /***
     * You can also use async task
     * **/
    new Thread(new Runnable() {

      @Override public void run() {
        getpackageSize();
      }
    }).start();
  }

  private void showProgress(String message) {
    pd = new ProgressDialog(this);
    pd.setIcon(android.R.drawable.alert_dark_frame);
    pd.setTitle("Please Wait...");
    pd.setMessage(message);
    pd.setCancelable(false);
    pd.show();
  }

  private void getpackageSize() {
    cAppDetails = new AppDetails(this);
    res = cAppDetails.getPackages();
    if (res == null) return;
    for (int m = 0; m < res.size(); m++) {
      PackageManager pm = getPackageManager();
      Method getPackageSizeInfo;
      try {
        getPackageSizeInfo = pm.getClass()
            .getMethod("getPackageSizeInfo", String.class, IPackageStatsObserver.class);
        getPackageSizeInfo.invoke(pm, res.get(m).getPname(), new cachePackState());
      } catch (SecurityException e) {
        e.printStackTrace();
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      }
    }
    handle.sendEmptyMessage(ALL_PACKAGE_SIZE_COMPLETED);
    Log.v("Total Cache Size", " " + packageSize);
  }

  private Handler handle = new Handler() {
    @Override public void handleMessage(Message msg) {
      switch (msg.what) {
        case FETCH_PACKAGE_SIZE_COMPLETED:
          if (packageSize > 0) size = (packageSize / 1024000);
          lbl_cache_size.setText("Cache Size : " + size + " MB");
          break;
        case ALL_PACKAGE_SIZE_COMPLETED:
          adapter.notifyDataSetChanged();
          if (null != pd) if (pd.isShowing()) pd.dismiss();

          break;
        case NO_PACKAGE_FOUND:
          showError();
          break;
        default:
          break;
      }
    }
  };

  @Override public void onError() {
    handle.sendEmptyMessage(NO_PACKAGE_FOUND);
  }

  private void showError() {
    Toast.makeText(this, "Error no package", Toast.LENGTH_SHORT).show();
  }

  private class cachePackState extends IPackageStatsObserver.Stub {

    @Override public void onGetStatsCompleted(PackageStats pStats, boolean succeeded)
        throws RemoteException {
      // TODO: Delete when release. Debug.
      Log.d("Package Size", pStats.packageName + "");
      Log.e("APK Size", pStats.codeSize + "");
      Log.i("Cache Size", (pStats.cacheSize + pStats.externalCacheSize) + "");
      Log.w("Data Size", (pStats.dataSize + pStats.externalDataSize) + "");
      packageSize = packageSize + pStats.cacheSize;
      Log.v("Total Cache Sizes", " " + packageSize);
      addSizesApplication(pStats);
      handle.sendEmptyMessage(FETCH_PACKAGE_SIZE_COMPLETED);
    }
  }

  private void addSizesApplication(PackageStats pStats) {
    for (ApplicationInfoStruct applicationInfoStruct : res) {
      if (applicationInfoStruct.getPname().equals(pStats.packageName)) {
        applicationInfoStruct.setApkSize(pStats.codeSize);
        applicationInfoStruct.setCacheSize((pStats.cacheSize + pStats.externalCacheSize));
        applicationInfoStruct.setDataSize((pStats.dataSize + pStats.externalDataSize));
        applicationInfoStruct.setTotalSize(
            pStats.codeSize + (pStats.cacheSize + pStats.externalCacheSize) + (pStats.dataSize
                + pStats.externalDataSize));
      }
    }
  }

/*  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btn_get_cacheSize:
        size = 0;
        packageSize = 0;
        showProgress("Calculating Cache Size..!!!");
        *//**
   * You can also use async task
   * *//*
        new Thread(new Runnable() {

          @Override public void run() {
            getpackageSize();
          }
        }).start();

        break;
    }
  }*/
}