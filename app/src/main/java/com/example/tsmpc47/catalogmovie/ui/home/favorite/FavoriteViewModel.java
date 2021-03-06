package com.example.tsmpc47.catalogmovie.ui.home.favorite;

import android.arch.lifecycle.MutableLiveData;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.databinding.ObservableArrayList;
import android.net.Uri;
import android.util.Log;

import com.example.tsmpc47.catalogmovie.data.DataManager;
import com.example.tsmpc47.catalogmovie.data.model.Result;
import com.example.tsmpc47.catalogmovie.ui.base.BaseViewModel;
import com.example.tsmpc47.catalogmovie.utils.rx.SchedulerProvider;

import java.util.List;

import io.reactivex.functions.Consumer;

import static android.provider.BaseColumns._ID;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.CONTENT_URI;

public class FavoriteViewModel extends BaseViewModel<FavoriteNavigator> {

    private final ObservableArrayList<Result> favoriteViewModels = new ObservableArrayList<>();
    private final MutableLiveData<List<Result>> favoriteLiveData;
    private static final String TAG = "FavoriteViewModel";

    public FavoriteViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
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
