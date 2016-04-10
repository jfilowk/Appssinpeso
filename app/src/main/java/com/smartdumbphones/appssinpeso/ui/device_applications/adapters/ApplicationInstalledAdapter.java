package com.smartdumbphones.appssinpeso.ui.device_applications.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.smartdumbphones.appssinpeso.R;
import com.smartdumbphones.appssinpeso.models.AllApplications;
import com.smartdumbphones.appssinpeso.models.ApplicationInfoStruct;
import java.util.List;

public class ApplicationInstalledAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int TYPE_HEADER = 100;
  private static final int TYPE_ROW = 200;
  private List<ApplicationInfoStruct> listApplication;
  // FIXME: REVIEW
  private static Context context;
  private AllApplications allApplications;


  public ApplicationInstalledAdapter(Context context, AllApplications allApplications) {
    ApplicationInstalledAdapter.context = context;
    this.allApplications = allApplications;
    this.listApplication = allApplications.getListApplications();
  }

  public void refreshData(AllApplications allApplications) {
    this.allApplications = allApplications;
    this.listApplication.clear();
    this.listApplication.addAll(allApplications.getListApplications());
    notifyDataSetChanged();
  }

  public static class RowViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.txtApplication) TextView txtNameApplication;
    @Bind(R.id.txtApplicationSize) TextView txtAppSize;
    @Bind(R.id.txtCacheSize) TextView txtCacheSize;
    @Bind(R.id.txtDataSize) TextView txtDataSize;
    @Bind(R.id.txtTotalSize) TextView txtTotalSize;
    @Bind(R.id.iconApplication) ImageView imgIcon;

    public RowViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    public void bindApplicationInfo(ApplicationInfoStruct applicationInfoStruct) {
      txtNameApplication.setText(applicationInfoStruct.getAppname());
      txtAppSize.setText("App: " + getSizeHumanReadbeable(applicationInfoStruct.getApkSize()));
      if (applicationInfoStruct.getCacheSize() == 0.0) {
        txtCacheSize.setText("Cache: -");
      } else {
        txtCacheSize.setText(
            "Cache: " + getSizeHumanReadbeable(applicationInfoStruct.getCacheSize()));
      }
      if (applicationInfoStruct.getDataSize() == 0.0) {
        txtDataSize.setText("Data: -");
      } else {
        txtDataSize.setText("Data: " + getSizeHumanReadbeable(applicationInfoStruct.getDataSize()));
      }
      txtTotalSize.setText(getSizeHumanReadbeable(applicationInfoStruct.getTotalSize()));
      imgIcon.setImageDrawable(applicationInfoStruct.getIcon());
    }
  }

  public static class HeaderViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.lblHeaderName) TextView lblHeaderName;
    @Bind(R.id.lblHeaderValue) TextView lblHeaderSize;
    @Bind(R.id.separator) View separator;

    public HeaderViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    public void bindAllApplications(AllApplications allApplications, int position) {
      if (position == ItemTypes.HEADER_NUM.getValue()) {
        lblHeaderName.setText(R.string.num_applications_header);
        lblHeaderSize.setText(String.valueOf(allApplications.getTotalNumApplications()));
        separator.setVisibility(View.GONE);
      } else if (position == ItemTypes.HEADER_ALL_APPLICATIONS.getValue()) {
        lblHeaderName.setText(R.string.total_applications_header);
        lblHeaderSize.setText(getSizeHumanReadbeable(allApplications.getTotalSizeApplications()));
        separator.setVisibility(View.GONE);
      } else if (position == ItemTypes.HEADER_CACHE.getValue()) {
        lblHeaderName.setText(R.string.total_cache_header);
        lblHeaderSize.setText(getSizeHumanReadbeable(allApplications.getTotalSizeCache()));
        separator.setVisibility(View.VISIBLE);
      }
    }
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == TYPE_HEADER) {
      View itemView = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.list_cache_header_item, parent, false);
      return new HeaderViewHolder(itemView);
    } else {
      View itemView =
          LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cache_item, parent, false);
      return new RowViewHolder(itemView);
    }
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof HeaderViewHolder) {
      ((HeaderViewHolder) holder).bindAllApplications(allApplications, position);
    } else if (holder instanceof RowViewHolder) {
      ApplicationInfoStruct applicationInfoStruct = getApplicationInfoStruct(position);
      ((RowViewHolder) holder).bindApplicationInfo(applicationInfoStruct);
    }
  }

  @Override public int getItemCount() {
    return listApplication == null || allApplications == null ? 0
        : listApplication.size() + ItemTypes.values().length;
  }

  enum ItemTypes {
    HEADER_NUM(0),
    HEADER_ALL_APPLICATIONS(1),
    HEADER_CACHE(2);

    private int value;

    ItemTypes(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
  }

  @Override public int getItemViewType(int position) {
    if (position == ItemTypes.HEADER_NUM.getValue() || position == ItemTypes.HEADER_ALL_APPLICATIONS
        .getValue() || position == ItemTypes.HEADER_CACHE.getValue()) {
      return TYPE_HEADER;
    }
    return TYPE_ROW;
  }

  private ApplicationInfoStruct getApplicationInfoStruct(int position) {
    return listApplication.get(position - 3);
  }

  private static String getSizeHumanReadbeable(long size) {
    return android.text.format.Formatter.formatShortFileSize(context, size);
  }
}
