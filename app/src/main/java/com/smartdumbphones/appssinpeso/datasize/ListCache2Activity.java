package com.smartdumbphones.appssinpeso.datasize;

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

public class ListCache2Activity extends AppCompatActivity {

  @Bind(R.id.lbl_cache_size) TextView lbl_cache_size;
  @Bind(R.id.recycler_list) RecyclerView recyclerList;
  private List<ApplicationInfoStruct> res;
  private ListApplicationAdapter adapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.list_cache_activity);
    ButterKnife.bind(this);

    res = new ArrayList<>();
    adapter = new ListApplicationAdapter(res);
    recyclerList.setAdapter(adapter);
    recyclerList.setLayoutManager(new LinearLayoutManager(this));
  }
}
