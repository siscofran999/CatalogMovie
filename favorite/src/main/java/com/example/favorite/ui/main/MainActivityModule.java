package com.example.favorite.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.example.favorite.ViewModelProviderFactory;
import com.example.favorite.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    MainViewModel provideMainViewModel(SchedulerProvider schedulerProvider) {
        return new MainViewModel(schedulerProvider);
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
