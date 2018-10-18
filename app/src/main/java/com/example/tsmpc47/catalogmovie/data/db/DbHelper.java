package com.example.tsmpc47.catalogmovie.data.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;

import com.example.tsmpc47.catalogmovie.data.model.Result;

import java.util.List;

import io.reactivex.Observable;

public interface DbHelper {

    AppDbHelper openDB() throws SQLException;

    void closeDb();

    void insertDB(String img, String title, String overview, String date, String rating, String popular);

    int searchData(String title);

    Observable<List<Result>> getDataFavorite();

    void deletedFavorite(String judul);

    Cursor queryProvider();

    Cursor queryByIdProvider(String lastPathSegment);

    long insertProvider(ContentValues contentValues);

    int deleteProvider(String lastPathSegment);
}
