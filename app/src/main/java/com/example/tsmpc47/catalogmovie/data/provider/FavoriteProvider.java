package com.example.tsmpc47.catalogmovie.data.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.tsmpc47.catalogmovie.data.db.AppDbHelper;
import com.example.tsmpc47.catalogmovie.data.db.DatabaseContract;
import com.example.tsmpc47.catalogmovie.data.db.DatabaseHelper;
import com.example.tsmpc47.catalogmovie.data.db.DbHelper;

import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.AUTHORITY;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.CONTENT_URI;

public class FavoriteProvider extends ContentProvider {

    private static final int Favorite = 1;
    private static final int Favorite_Id = 2;
    private AppDbHelper helper;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY, DatabaseContract.Table_Name_Movie_Favorite, Favorite);

        sUriMatcher.addURI(AUTHORITY, DatabaseContract.Table_Name_Movie_Favorite+"/#", Favorite_Id);
    }

    @Override
    public boolean onCreate() {
        helper = new AppDbHelper(getContext());
        helper.openDB();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        Cursor cursor;
        switch(sUriMatcher.match(uri)){
            case Favorite:
                cursor = helper.queryProvider();
                break;
            case Favorite_Id:
                cursor = helper.queryByIdProvider(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }

        if (cursor != null){
            cursor.setNotificationUri(getContext().getContentResolver(),uri);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        long added;
        switch (sUriMatcher.match(uri)){
            case Favorite:
                added = helper.insertProvider(contentValues);
                break;
            default:
                added = 0;
                break;
        }

        if (added > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return Uri.parse(CONTENT_URI + "/" + added);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {

        int deleted;
        switch (sUriMatcher.match(uri)) {
            case Favorite:
                deleted =  helper.deleteProvider(uri.getLastPathSegment());
                break;
            default:
                deleted = 0;
                break;
        }

        if (deleted > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return deleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
