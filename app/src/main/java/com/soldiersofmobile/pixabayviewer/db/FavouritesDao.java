package com.soldiersofmobile.pixabayviewer.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

public class FavouritesDao {

    public static final String TABLE_NAME = "favourites";

    public static final String C_ID = "id";
    public static final String C_PREVIEW_URL = "preview_url";
    public static final String C_PAGE_URL = "page_url";
    public static final String C_TAGS = "tags";
    private final DbHelper dbHelper;

    public FavouritesDao(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void create(Favourite favourite) {
        SQLiteDatabase writableDatabase = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(C_ID, favourite.getId());
        values.put(C_PREVIEW_URL, favourite.getPreviewURL());
        values.put(C_PAGE_URL, favourite.getPageURL());
        values.put(C_TAGS, favourite.getTags());

        writableDatabase.insert(TABLE_NAME, null, values);

    }

    public void delete(Favourite favourite) {
        SQLiteDatabase writableDatabase = dbHelper.getWritableDatabase();
        writableDatabase.delete(TABLE_NAME, String.format("%s = ?", C_ID),
                new String[]{String.valueOf(favourite.getId())});
    }

    public List<Favourite> getAll() {
        SQLiteDatabase readableDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = readableDatabase.query(TABLE_NAME, null, null, null, null,
                null, String.format("%s ASC", C_ID));
        List<Favourite> favourites = new LinkedList<>();
        while (cursor.moveToNext()) {
            Favourite favourite = new Favourite();
            favourite.setId(cursor.getLong(cursor.getColumnIndex(C_ID)));
            favourite.setPreviewURL(cursor.getString(cursor.getColumnIndex(C_PREVIEW_URL)));
            favourite.setPageURL(cursor.getString(cursor.getColumnIndex(C_PAGE_URL)));
            favourite.setTags(cursor.getString(cursor.getColumnIndex(C_TAGS)));

            favourites.add(favourite);
        }
        cursor.close();
        return favourites;
    }
}
