package com.smartdumbphones.appssinpeso.device_applications;

import com.smartdumbphones.appssinpeso.internal.manager.ApplicationsManager;
import com.smartdumbphones.appssinpeso.models.AllApplications;
import com.smartdumbphones.appssinpeso.models.ApplicationInfoStruct;
import com.smartdumbphones.appssinpeso.ui.device_applications.DeviceApplicationInstalledPresenter;
import com.smartdumbphones.appssinpeso.ui.device_applications.DeviceApplicationInstalledPresenterImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class DeviceApplicationInstalledPresenterTest {

  @Mock private DeviceApplicationInstalledPresenter.DeviceApplicationInstalledView view;

  @Mock private ApplicationsManager applicationsManager;

  @Captor ArgumentCaptor<ApplicationsManager.OnApplicationsListener>
      applicationsListenerArgumentCaptor;

  private DeviceApplicationInstalledPresenter presenter;

  @Before public void setupApplicationTest() {
    MockitoAnnotations.initMocks(this);
    presenter = new DeviceApplicationInstalledPresenterImpl(applicationsManager);
    presenter.attachView(view);
  }

  @Test public void loadPackages() {

    ApplicationInfoStruct applicationInfoStruct =
        new ApplicationInfoStruct("Hola", "Hola", null, "/dasd/das", 10, 10, 10, 10, false);
    ApplicationInfoStruct applicationInfoStruct2 =
        new ApplicationInfoStruct("Hola", "Hola", null, "/dasd/das", 10, 10, 10, 10, false);
    ApplicationInfoStruct applicationInfoStruct3 =
        new ApplicationInfoStruct("Hola", "Hola", null, "/dasd/das", 10, 10, 10, 10, false);

    List<ApplicationInfoStruct> applicationInfoStructList = new ArrayList<>();
    applicationInfoStructList.add(applicationInfoStruct);
    applicationInfoStructList.add(applicationInfoStruct2);
    applicationInfoStructList.add(applicationInfoStruct3);

    AllApplications build = new AllApplications.Builder().setTotalNumApplications(12)
        .setTotalSizeApplications(10)
        .setTotalSizeCache(10)
        .setListApplications(applicationInfoStructList)
        .build();

    presenter.getPackages();
    verify(view).showLoading();
    verify(applicationsManager).attachOnApplicationListener(
        applicationsListenerArgumentCaptor.capture());
    applicationsListenerArgumentCaptor.getValue().onSuccess(build);
    verify(view).displayListCache(build);
    verify(view).hideLoading();
  }
}
