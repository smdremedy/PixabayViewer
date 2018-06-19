package com.soldiersofmobile.pixabayviewer.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final String PIXABAY_DB = "pixabay.db";
    private static final int VERSION = 1;

    public DbHelper(Context context) {
        super(context, PIXABAY_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = String.format("CREATE TABLE %s (%s INT PRIMARY KEY NOT NULL," +
                        "%s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL)", FavouritesDao.TABLE_NAME, FavouritesDao.C_ID,
                FavouritesDao.C_PREVIEW_URL, FavouritesDao.C_PAGE_URL, FavouritesDao.C_TAGS);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
