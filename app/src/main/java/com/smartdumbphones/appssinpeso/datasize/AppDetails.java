package com.smartdumbphones.appssinpeso.datasize;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.smartdumbphones.appssinpeso.Appssinpeso;
import com.smartdumbphones.appssinpeso.datasize.models.ApplicationInfoStruct;
import java.util.ArrayList;
import java.util.List;

public class AppDetails {
  private ListenerErrorPackage listener;
  public ArrayList<ApplicationInfoStruct> res = new ArrayList<>();
  public String app_labels[];

  public AppDetails(ListenerErrorPackage listener) {
    this.listener = listener;
  }

  public ArrayList<ApplicationInfoStruct> getPackages() {
    ArrayList<ApplicationInfoStruct> apps = getInstalledApps(false);
    final int max = apps.size();
    for (int i = 0; i < max; i++) {
      apps.get(i);
    }
    return apps;
  }

  private ArrayList<ApplicationInfoStruct> getInstalledApps(boolean getSysPackages) {

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
      ApplicationInfoStruct newInfo = new ApplicationInfoStruct();
      newInfo.setAppname(p.applicationInfo.loadLabel(packageManager).toString());
      newInfo.setPname(p.packageName);
      newInfo.setDatadir(p.applicationInfo.dataDir);
      newInfo.setIcon(p.applicationInfo.loadIcon(packageManager));
      res.add(newInfo);

      app_labels[i] = newInfo.getAppname();
    }
    return res;
  }
}
