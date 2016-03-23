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
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.smartdumbphones.appssinpeso.R;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {
  public static final int FETCH_PACKAGE_SIZE_COMPLETED = 100;
  public static final int ALL_PACKAGE_SIZE_COMPLETED = 200;
  IDataStatus onIDataStatus;
  TextView lbl_cache_size;
  ProgressDialog pd;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    findViewById(R.id.btn_get_cacheSize).setOnClickListener(this);
    lbl_cache_size = (TextView) findViewById(R.id.lbl_cache_size);
    // clearCache();
  }

  private void showProgress(String message) {
    pd = new ProgressDialog(this);
    pd.setIcon(android.R.drawable.alert_dark_frame);
    pd.setTitle("Please Wait...");
    pd.setMessage(message);
    pd.setCancelable(false);
    pd.show();
  }

  long packageSize = 0, size = 0;
  AppDetails cAppDetails;
  public ArrayList<AppDetails.PackageInfoStruct> res;

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
        getPackageSizeInfo.invoke(pm, res.get(m).pname, new cachePackState());
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
          if (null != pd) if (pd.isShowing()) pd.dismiss();

          break;
        default:
          break;
      }
    }
  };

  private class cachePackState extends IPackageStatsObserver.Stub {

    @Override public void onGetStatsCompleted(PackageStats pStats, boolean succeeded)
        throws RemoteException {
      Log.d("Package Size", pStats.packageName + "");
      Log.i("Cache Size", pStats.cacheSize + "");
      Log.w("Data Size", pStats.dataSize + "");
      packageSize = packageSize + pStats.cacheSize;
      Log.v("Total Cache Sizes", " " + packageSize);
      handle.sendEmptyMessage(FETCH_PACKAGE_SIZE_COMPLETED);
    }
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btn_get_cacheSize:
        size = 0;
        packageSize = 0;
        showProgress("Calculating Cache Size..!!!");
        /**
         * You can also use async task
         * */
        new Thread(new Runnable() {

          @Override public void run() {
            getpackageSize();
          }
        }).start();

        break;
    }
  }
}