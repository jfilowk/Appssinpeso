package com.smartdumbphones.appssinpeso.datasize;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import com.smartdumbphones.appssinpeso.Appssinpeso;
import java.util.ArrayList;
import java.util.List;

public class AppDetails {
  private ListenerErrorPackage listener;
  public ArrayList<PackageInfoStruct> res = new ArrayList<>();
  public String app_labels[];

  public AppDetails(ListenerErrorPackage listener) {
    this.listener = listener;
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

    PackageManager packageManager = Appssinpeso.getInstance().getPackageManager();
    List<PackageInfo> packs = packageManager.getInstalledPackages(0);
    try {
      app_labels = new String[packs.size()];
    } catch (Exception e) {
      listener.onError();
    }
    for (int i = 0; i < packs.size(); i++) {
      PackageInfo p = packs.get(i);
      if ((!getSysPackages) && (p.versionName == null)) {
        continue;
      }
      PackageInfoStruct newInfo = new PackageInfoStruct();
      newInfo.appname = p.applicationInfo.loadLabel(packageManager).toString();
      newInfo.pname = p.packageName;
      newInfo.datadir = p.applicationInfo.dataDir;
      newInfo.versionName = p.versionName;
      newInfo.versionCode = p.versionCode;
      newInfo.icon = p.applicationInfo.loadIcon(packageManager);
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
