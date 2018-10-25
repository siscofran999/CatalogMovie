package com.example.favorite.ui.main;

import com.example.favorite.ui.base.BaseViewModel;
import com.example.favorite.utils.rx.SchedulerProvider;

public class MainViewModel extends BaseViewModel<MainNavigator> {

    public MainViewModel(SchedulerProvider schedulerProvider) {
        super(schedulerProvider);
    }
}
