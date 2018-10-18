/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.example.tsmpc47.catalogmovie.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.example.tsmpc47.catalogmovie.data.db.AppDbHelper;
import com.example.tsmpc47.catalogmovie.data.db.DbHelper;
import com.example.tsmpc47.catalogmovie.data.model.MovieResponse;
import com.example.tsmpc47.catalogmovie.data.model.NowPlaying;
import com.example.tsmpc47.catalogmovie.data.model.Result;
import com.example.tsmpc47.catalogmovie.data.remote.ApiHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by amitshekhar on 07/07/17.
 */
@Singleton
public class AppDataManager implements DataManager {

    private final Context mContext;
    private final ApiHelper mApiHelper;
    private final DbHelper mDbHelper;

    @Inject
    public AppDataManager(Context context, ApiHelper apiHelper, DbHelper dbHelper) {
        mContext = context;
        mApiHelper = apiHelper;
        this.mDbHelper = dbHelper;
    }

    @Override
    public Single<MovieResponse> getMovieData(String query) {
        return mApiHelper.getMovieData(query);
    }

    @Override
    public Single<NowPlaying> getNowPlayingData() {
        return mApiHelper.getNowPlayingData();
    }

    @Override
    public Single<NowPlaying> getUpComingData() {
        return mApiHelper.getUpComingData();
    }

    @Override
    public AppDbHelper openDB() throws SQLException {
        return mDbHelper.openDB();
    }

    @Override
    public void closeDb() {
        mDbHelper.closeDb();
    }

    @Override
    public void insertDB(String img, String title, String overview, String date, String rating, String popular) {
        mDbHelper.insertDB(img,title,overview,date, rating, popular);
    }

    @Override
    public int searchData(String title) {
        return mDbHelper.searchData(title);
    }

    @Override
    public Observable<List<Result>> getDataFavorite() {
        return mDbHelper.getDataFavorite();
    }

    @Override
    public void deletedFavorite(String title) {
        mDbHelper.deletedFavorite(title);
    }

    @Override
    public Cursor queryProvider() {
        return mDbHelper.queryProvider();
    }

    @Override
    public Cursor queryByIdProvider(String lastPathSegment) {
        return mDbHelper.queryByIdProvider(lastPathSegment);
    }

    @Override
    public long insertProvider(ContentValues contentValues) {
        return mDbHelper.insertProvider(contentValues);
    }

    @Override
    public int deleteProvider(String lastPathSegment) {
        return mDbHelper.deleteProvider(lastPathSegment);
    }


}
