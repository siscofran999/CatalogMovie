package com.example.tsmpc47.catalogmovie.ui.home;

import com.example.tsmpc47.catalogmovie.data.DataManager;
import com.example.tsmpc47.catalogmovie.ui.base.BaseViewModel;
import com.example.tsmpc47.catalogmovie.utils.rx.SchedulerProvider;

public class HomeViewModel extends BaseViewModel<HomeNavigator> {

    public HomeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

}
