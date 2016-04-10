package com.smartdumbphones.appssinpeso.ui.device_applications;

import android.content.Context;
import android.content.pm.ApplicationInfo;
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
    return getInstalledApps();
  }

  private ArrayList<ApplicationInfoStruct> getInstalledApps() {
    ArrayList<ApplicationInfoStruct> res = new ArrayList<>();
    PackageManager packageManager = context.getPackageManager();
    List<PackageInfo> packs = packageManager.getInstalledPackages(0);

    if (packs.size() == 0) {
      return res;
    }

    for (int i = 0; i < packs.size(); i++) {
      PackageInfo p = packs.get(i);
      ApplicationInfoStruct applicationInfoStruct = new ApplicationInfoStruct();

      if (isSystemApplication(p)) continue;

      applicationInfoStruct.setAppname(p.applicationInfo.loadLabel(packageManager).toString());
      applicationInfoStruct.setPname(p.packageName);
      applicationInfoStruct.setDatadir(p.applicationInfo.dataDir);
      applicationInfoStruct.setIcon(p.applicationInfo.loadIcon(packageManager));

      res.add(applicationInfoStruct);
    }
    return res;
  }

  private boolean isSystemApplication(PackageInfo p) {
    return (p.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
  }
}
