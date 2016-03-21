package com.smartdumbphones.appssinpeso.datasize;

import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class AppDetails {
  MainActivity activity;
  public ArrayList<PackageInfoStruct> res = new ArrayList<>();
  public ListView listView;
  public String app_labels[];

  public AppDetails(MainActivity activity) {
    this.activity = activity;
  }

  public ArrayList<PackageInfoStruct> getPackages() {
    ArrayList<PackageInfoStruct> apps = getInstalledApps(false);
    final int max = apps.size();
    for (int i = 0; i < max; i++) {
      apps.get(i);
    }
    return apps;
  }

  private ArrayList<PackageInfoStruct> getInstalledApps(boolean getSysPackages) {

    List<PackageInfo> packs = activity.getPackageManager().getInstalledPackages(0);
    try {
      app_labels = new String[packs.size()];
    } catch (Exception e) {
      Toast.makeText(activity.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }
    for (int i = 0; i < packs.size(); i++) {
      PackageInfo p = packs.get(i);
      if ((!getSysPackages) && (p.versionName == null)) {
        continue;
      }
      PackageInfoStruct newInfo = new PackageInfoStruct();
      newInfo.appname = p.applicationInfo.loadLabel(activity.getPackageManager()).toString();
      newInfo.pname = p.packageName;
      newInfo.datadir = p.applicationInfo.dataDir;
      newInfo.versionName = p.versionName;
      newInfo.versionCode = p.versionCode;
      newInfo.icon = p.applicationInfo.loadIcon(activity.getPackageManager());
      res.add(newInfo);

      app_labels[i] = newInfo.appname;
    }
    return res;
  }

  class PackageInfoStruct {
    String appname = "";
    String pname = "";
    String versionName = "";
    int versionCode = 0;
    Drawable icon;
    String datadir = "";
  }
}
