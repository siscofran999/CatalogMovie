package com.example.tsmpc47.catalogmovie.data.db;

import android.database.SQLException;

public interface DbHelper {

    AppDbHelper openDB() throws SQLException;

    void closeDb();

    void insertDB(String img, String title, String overview, String date);

    int searchData(String title);

}
