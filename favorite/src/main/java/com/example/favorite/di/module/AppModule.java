package com.example.favorite.di.module;

import android.app.Application;
import android.content.Context;

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

}
