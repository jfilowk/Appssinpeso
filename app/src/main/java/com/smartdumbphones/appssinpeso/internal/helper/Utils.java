package com.smartdumbphones.appssinpeso.internal.helper;

import android.content.Context;
import javax.inject.Inject;

public final class Utils {

  private static Context context;

  @Inject public Utils(Context context) {
    Utils.context = context;
  }

  public String getSizeHumanReadbeable(long size) {
    return android.text.format.Formatter.formatShortFileSize(context, size);
  }
}
