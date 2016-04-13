package com.smartdumbphones.appssinpeso.data.cache;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CacheApplicationDB extends SQLiteOpenHelper {

  public static final String DATABASE_NAME = "appssinpeso.db";

  public static final String APPLICATIONS_TABLE = "applications";

  public static final String ID = "id";
  public static final String PACKAGE_NAME = "package_name";
  public static final String APK_SIZE = "apk_size";
  public static final String CACHE_SIZE = "cache_size";
  public static final String DATA_SIZE = "data_size";
  public static final String CREATED_AT = "created_at";

  public static final String CREATE_TABLE_APPLICATIONS = "CREATE TABLE "
      + APPLICATIONS_TABLE
      + "("
      + ID
      + " integer primary key autoincrement, "
      + PACKAGE_NAME
      + " TEXT, "
      + APK_SIZE
      + " INTEGER, "
      + CACHE_SIZE
      + " INTEGER, "
      + DATA_SIZE
      + " INTEGER, "
      + CREATED_AT
      + " DATETIME DEFAULT CURRENT_TIMESTAMP);";

  public static final String DELETE_TABLE_APPLICATION =
      "DELETE TABLE IF EXISTS " + APPLICATIONS_TABLE;

  private static final int DB_VERSION = 1;

  public CacheApplicationDB(Context context) {
    super(context, DATABASE_NAME, null, DB_VERSION);
  }

  @Override public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_TABLE_APPLICATIONS);
  }

  @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL(DELETE_TABLE_APPLICATION);
    onCreate(db);
  }
}
