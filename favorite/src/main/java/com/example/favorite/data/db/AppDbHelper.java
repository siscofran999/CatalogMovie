package com.example.favorite.data.db;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppDbHelper implements DbHelper {

    private final Context mContext;

    @Inject
    public AppDbHelper(Context mContext) {
        this.mContext = mContext;
    }
}
