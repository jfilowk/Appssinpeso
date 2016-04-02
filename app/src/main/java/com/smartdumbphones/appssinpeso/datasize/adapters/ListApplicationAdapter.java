package com.smartdumbphones.appssinpeso.datasize.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.smartdumbphones.appssinpeso.R;
import com.smartdumbphones.appssinpeso.datasize.models.ApplicationInfoStruct;
import java.util.List;

public class ListApplicationAdapter
    extends RecyclerView.Adapter<ListApplicationAdapter.ViewHolder> {

  private List<ApplicationInfoStruct> listApplication;

  public static class ViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.txtApplication) TextView txtNameApplication;
    @Bind(R.id.txtApplicationSize) TextView txtAppSize;
    @Bind(R.id.txtCacheSize) TextView txtCacheSize;
    @Bind(R.id.txtDataSize) TextView txtDataSize;
    @Bind(R.id.txtTotalSize) TextView txtTotalSize;
    @Bind(R.id.iconApplication) ImageView imgIcon;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    public void bindApplicationInfo(ApplicationInfoStruct applicationInfoStruct) {
      txtNameApplication.setText(applicationInfoStruct.getAppname());
      txtAppSize.setText("App: " + String.valueOf(applicationInfoStruct.getApkSize()) + "MB");
      if (applicationInfoStruct.getCacheSize() == 0.0) {
        txtCacheSize.setText("Cache: -");
      } else {
        txtCacheSize.setText(
            "Cache: " + String.valueOf(applicationInfoStruct.getCacheSize()) + "MB");
      }
      if (applicationInfoStruct.getDataSize() == 0.0) {
        txtDataSize.setText("Data: -");
      } else {
        txtDataSize.setText("Data: " + String.valueOf(applicationInfoStruct.getDataSize()) + "MB");
      }
      txtTotalSize.setText(String.valueOf(applicationInfoStruct.getTotalSize()) + "MB");
      imgIcon.setImageDrawable(applicationInfoStruct.getIcon());
    }
  }

  public ListApplicationAdapter(List<ApplicationInfoStruct> listApplication) {
    this.listApplication = listApplication;
  }

  @Override
  public ListApplicationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewGroup) {
    View itemView =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cache_item, parent, false);
    return new ViewHolder(itemView);
  }

  @Override public void onBindViewHolder(ListApplicationAdapter.ViewHolder holder, int position) {
    ApplicationInfoStruct applicationInfoStruct = listApplication.get(position);
    holder.bindApplicationInfo(applicationInfoStruct);
  }

  @Override public int getItemCount() {
    return listApplication == null ? 0 : listApplication.size();
  }
}