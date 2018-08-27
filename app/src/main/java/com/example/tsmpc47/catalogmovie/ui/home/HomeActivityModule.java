package com.example.tsmpc47.catalogmovie.ui.home;

import com.example.tsmpc47.catalogmovie.data.DataManager;
import com.example.tsmpc47.catalogmovie.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeActivityModule {

    @Provides
    HomeViewModel provideHomeViewModel(DataManager dataManager,
                                       SchedulerProvider schedulerProvider){
        return new HomeViewModel(dataManager, schedulerProvider);
    }

}
