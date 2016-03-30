package com.smartdumbphones.appssinpeso.datasize.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.smartdumbphones.appssinpeso.datasize.models.ApplicationInfoStruct;
import java.util.List;

public class ListApplicationAdapter
    extends RecyclerView.Adapter<ListApplicationAdapter.ViewHolder> {

  private List<ApplicationInfoStruct> listApplication;

  public static class ViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;

    public ViewHolder(View itemView, TextView textView) {
      super(itemView);
      this.textView = textView;
    }
  }

  public ListApplicationAdapter(List<ApplicationInfoStruct> listApplication) {
    this.listApplication = listApplication;
  }

  @Override
  public ListApplicationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return null;
  }

  @Override public void onBindViewHolder(ListApplicationAdapter.ViewHolder holder, int position) {

  }

  @Override public int getItemCount() {
    return 0;
  }
}
