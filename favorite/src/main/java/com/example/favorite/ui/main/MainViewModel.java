package com.example.favorite.ui.main;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;

import com.example.favorite.data.DataManager;
import com.example.favorite.data.model.Result;
import com.example.favorite.ui.base.BaseViewModel;
import com.example.favorite.utils.rx.SchedulerProvider;

import java.util.List;

public class MainViewModel extends BaseViewModel<MainNavigator> {

    private final ObservableArrayList<Result> favoriteViewModels = new ObservableArrayList<>();
    private final MutableLiveData<List<Result>> favoriteLiveData;

    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager,schedulerProvider);
        favoriteLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Result>> getFavoriteLiveData() {
        return favoriteLiveData;
    }

    public ObservableArrayList<Result> getFavoriteViewModels() {
        return favoriteViewModels;
    }

    public void addFavoriteToList(List<Result> results){
        favoriteViewModels.clear();
        favoriteViewModels.addAll(results);
    }
}
