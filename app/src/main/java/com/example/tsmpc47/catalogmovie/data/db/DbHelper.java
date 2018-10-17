package com.example.tsmpc47.catalogmovie.data.db;

import android.database.SQLException;

import com.example.tsmpc47.catalogmovie.data.model.Result;

import java.util.List;

import io.reactivex.Observable;

public interface DbHelper {

    AppDbHelper openDB() throws SQLException;

    void closeDb();

    void insertDB(String img, String title, String overview, String date, String rating);

    int searchData(String title);

    Observable<List<Result>> getDataFavorite();

}
