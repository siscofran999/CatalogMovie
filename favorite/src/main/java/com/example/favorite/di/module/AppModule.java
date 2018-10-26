package com.example.favorite.di.module;

import android.app.Application;
import android.content.Context;

import com.example.favorite.data.AppDataManager;
import com.example.favorite.data.DataManager;
import com.example.favorite.data.db.AppDbHelper;
import com.example.favorite.data.db.DbHelper;
import com.example.favorite.utils.rx.AppSchedulerProvider;
import com.example.favorite.utils.rx.SchedulerProvider;

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
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

}
