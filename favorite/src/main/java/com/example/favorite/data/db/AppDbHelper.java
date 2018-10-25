package com.example.favorite.data.db;

import android.content.Context;

public class AppDbHelper implements DbHelper {

    private final Context mContext;
    private DatabaseHelper mDatabaseHelper;

    public AppDbHelper(Context mContext, DatabaseHelper mDatabaseHelper) {
        this.mContext = mContext;
        this.mDatabaseHelper = mDatabaseHelper;
    }
}
