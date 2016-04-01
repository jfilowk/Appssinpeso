package com.smartdumbphones.appssinpeso.datasize;

import com.smartdumbphones.appssinpeso.datasize.models.ApplicationInfoStruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApplicationsManagerImpl implements ApplicationsManager {

  private ExecutorService executorService;
  private OnApplicationsListener listener;

  public ApplicationsManagerImpl() {
    executorService = Executors.newSingleThreadExecutor();
  }

  @Override public void attachOnApplicationListener(OnApplicationsListener listener) {
    this.listener = listener;
  }

  // TODO: Listener
  @Override public void start() {
    executorService.submit(new Runnable() {
      @Override public void run() {
        AppDetails appDetails = new AppDetails();
        ArrayList<ApplicationInfoStruct> packages = appDetails.getPackages();
        if (packages.size() == 0) {
          if (listener != null) {
            listener.onError();
          }
        }

        sortPackagesAlpha(packages);
      }
    });
  }

  private void sortPackagesAlpha(ArrayList<ApplicationInfoStruct> packages) {
    Collections.sort(packages, new Comparator<ApplicationInfoStruct>() {
      @Override public int compare(ApplicationInfoStruct lhs, ApplicationInfoStruct rhs) {
        return lhs.getAppname().compareTo(rhs.getAppname());
      }
    });
  }

  @Override public void stop() {
    listener = null;
  }
}
