package com.smartdumbphones.appssinpeso.ui.device_applications;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.smartdumbphones.appssinpeso.models.ApplicationInfoStruct;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class AppDetails {
  private Context context;

  @Inject public AppDetails(Context context) {
    this.context = context;
  }

  public ArrayList<ApplicationInfoStruct> getPackages() {
    return getInstalledApps(false);
  }

  private ArrayList<ApplicationInfoStruct> getInstalledApps(boolean getSysPackages) {
    ArrayList<ApplicationInfoStruct> res = new ArrayList<>();
    PackageManager packageManager = context.getPackageManager();
    List<PackageInfo> packs = packageManager.getInstalledPackages(0);

    if (packs.size() == 0) {
      return res;
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
    }
    return res;
  }
}
