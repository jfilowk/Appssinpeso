package com.smartdumbphones.appssinpeso.datasize;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.smartdumbphones.appssinpeso.R;
import com.smartdumbphones.appssinpeso.datasize.adapters.ListApplicationAdapter;
import com.smartdumbphones.appssinpeso.datasize.models.ApplicationInfoStruct;
import java.util.ArrayList;
import java.util.List;

public class ListCache2Activity extends AppCompatActivity implements ListCacheView {

  @Bind(R.id.lblCacheSize) TextView txtCacheSize;
  @Bind(R.id.recycler_list) RecyclerView recyclerList;
  private ProgressDialog progressDialog;
  private List<ApplicationInfoStruct> res;
  private ListApplicationAdapter adapter;

  private ListCachePresenter presenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.list_cache_activity);
    ButterKnife.bind(this);

    res = new ArrayList<>();
    adapter = new ListApplicationAdapter(res);
    recyclerList.setAdapter(adapter);
    recyclerList.setLayoutManager(new LinearLayoutManager(this));

    presenter = new ListCachePresenterImpl(this);
    presenter.getPackages();
  }

  @Override public void showLoading() {
    progressDialog = new ProgressDialog(this);
    progressDialog.setIcon(android.R.drawable.alert_dark_frame);
    progressDialog.setMessage("Loading...");
    progressDialog.setCancelable(false);
    progressDialog.show();
  }

  @Override public void hideLoading() {
    progressDialog.dismiss();
  }

  @Override public void displayListCache(final List<ApplicationInfoStruct> applicationInfoStructList) {
    runOnUiThread(new Runnable() {
      @Override public void run() {
        res.clear();
        res.addAll(applicationInfoStructList);
        adapter.notifyDataSetChanged();
      }
    });
  }

  @Override public void showError(String error) {

  }
}
