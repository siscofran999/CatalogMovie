package com.example.favorite.data;

import android.content.Context;

import com.example.favorite.data.db.DbHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppDataManager implements DataManager {

    private final Context mContext;
    private final DbHelper mDbHelper;

    @Inject
    public AppDataManager(Context mContext, DbHelper mDbHelper) {
        this.mContext = mContext;
        this.mDbHelper = mDbHelper;
    }
}
