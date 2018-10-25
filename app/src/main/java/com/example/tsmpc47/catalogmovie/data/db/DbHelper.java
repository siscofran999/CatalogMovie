package com.example.tsmpc47.catalogmovie.data.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;

import com.example.tsmpc47.catalogmovie.data.model.Result;

import java.util.List;

import io.reactivex.Observable;

public interface DbHelper {

    AppDbHelper openDB() throws SQLException;

    void closeDb();

    Cursor queryProvider();

    Cursor queryByIdProvider(String lastPathSegment);

    int deleteProviders(String lastPathSegment);

    long insertProviders(ContentValues contentValues);

    int updateProvider(String lastPathSegment, ContentValues contentValues);
}
