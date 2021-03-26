package com.example.subd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SimpleCursorAdapter;


public class Song {

    public String title;
    public String author;
    public String year;
    public String duration;

    public static String TITLE = "title";
    public static String AUTHOR = "artist";
    public static String YEAR = "year";
    public static String DURATION = "duration";

    public static int count = -1;
    public static int totalDuration = -1;
    public static SQLiteDatabase db = null;

    public static String TABLE = "playlist";

    public Song(String title, String author, String year, String duration) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.duration = duration;
    }

    public void save(Context context) {
        DBHelperWithLoader helper = new DBHelperWithLoader(context);
        SQLiteDatabase musicDB = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Song.TITLE, title);
        values.put(Song.AUTHOR, author);
        values.put(Song.YEAR, year);
        values.put(Song.DURATION, duration);
        musicDB.insert(Song.TABLE, null, values);
    }

    public static Cursor getSongCursor(SQLiteDatabase db, String order, Boolean asc) {
        Cursor cursor = db.rawQuery("SELECT SUM(duration) as Total FROM playlist", null);
        if (cursor.moveToFirst()) {
            totalDuration = cursor.getInt(cursor.getColumnIndex("Total"));
        }

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE + ((order != null) ? " ORDER BY " + order + ((asc) ? " ASC": " DESC"): ""), null);
        count = c.getCount();
        return c;
    }

    public static SimpleCursorAdapter getSongAdapter(Context context) {
        DBHelperWithLoader helper = new DBHelperWithLoader(context);
        db = helper.getWritableDatabase();

        Cursor tunes = getSongCursor(db, null, null);


        String[] playlist_fields = tunes.getColumnNames();

        int[] views = { R.id.itemId, R.id.itemAuthor, R.id.itemTitle, R.id.itemYear, R.id.itemDuration };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(context, R.layout.playlist_item, tunes, playlist_fields, views, 0 );
        return adapter;
    }

    public static void updateAdapter(SimpleCursorAdapter adapter) {
        updateAdapter(adapter, null, null);
    }

    public static void updateAdapter(SimpleCursorAdapter adapter, String order) {
        updateAdapter(adapter, order, true);
    }

    public static void updateAdapter(SimpleCursorAdapter adapter, String order, Boolean asc) {
        adapter.swapCursor(Song.getSongCursor(db, order, asc));
        adapter.notifyDataSetChanged();
    }

    public static int getCount(Context context) {
        if (count == -1) {
            Song.getSongAdapter(context);
        }
        return Song.count;
    }

    public static int getTotalDuration(Context context) {
        if (totalDuration == -1) {
            Song.getSongAdapter(context);
        }
        return Song.count;
    }

    public static String formatDuration(int duration) {
        String str = "";
        int min = duration / 60;
        int sec = duration - min * 60;
        str = "" + min + ":" + sec;
        return str;
    }

}
