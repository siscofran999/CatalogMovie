package com.example.tsmpc47.catalogmovie.di.module;

import android.app.Application;
import android.content.Context;

import com.example.tsmpc47.catalogmovie.BuildConfig;
import com.example.tsmpc47.catalogmovie.data.AppDataManager;
import com.example.tsmpc47.catalogmovie.data.DataManager;
import com.example.tsmpc47.catalogmovie.data.db.AppDbHelper;
import com.example.tsmpc47.catalogmovie.data.db.DatabaseHelper;
import com.example.tsmpc47.catalogmovie.data.db.DbHelper;
import com.example.tsmpc47.catalogmovie.data.remote.ApiHelper;
import com.example.tsmpc47.catalogmovie.data.remote.AppApiHelper;
import com.example.tsmpc47.catalogmovie.di.DatabaseInfo;
import com.example.tsmpc47.catalogmovie.utils.AppConstants;
import com.example.tsmpc47.catalogmovie.utils.rx.AppSchedulerProvider;
import com.example.tsmpc47.catalogmovie.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    DatabaseHelper provideDatabaseHelper(Context context){
        return new DatabaseHelper(context);
    }

}
