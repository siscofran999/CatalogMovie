package com.example.favorite.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.example.favorite.ViewModelProviderFactory;
import com.example.favorite.data.DataManager;
import com.example.favorite.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    MainViewModel provideMainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new MainViewModel(dataManager, schedulerProvider);
    }

    @Provides
    MainAdapter provideMainAdapter(MainActivity activity, DataManager dataManager, SchedulerProvider schedulerProvider){
        return new MainAdapter(activity,dataManager,schedulerProvider);
    }

    @Provides
    ViewModelProvider.Factory provideMainViewModelProviderFactory(MainViewModel mainViewModel) {
        return new ViewModelProviderFactory<>(mainViewModel);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(MainActivity activity){
        return new LinearLayoutManager(activity.getApplication());
    }

}
