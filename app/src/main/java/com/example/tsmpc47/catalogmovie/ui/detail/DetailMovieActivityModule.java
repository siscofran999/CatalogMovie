package com.example.tsmpc47.catalogmovie.ui.detail;

import com.example.tsmpc47.catalogmovie.data.DataManager;
import com.example.tsmpc47.catalogmovie.ui.main.MainViewModel;
import com.example.tsmpc47.catalogmovie.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailMovieActivityModule {

    @Provides
    DetailMovieViewModel provideDetailMovieViewModel(DataManager dataManager,
                                                     SchedulerProvider schedulerProvider){
        return new DetailMovieViewModel(dataManager, schedulerProvider);
    }


}
