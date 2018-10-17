package com.example.tsmpc47.catalogmovie.ui.home.favorite;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.util.Log;

import com.example.tsmpc47.catalogmovie.data.DataManager;
import com.example.tsmpc47.catalogmovie.data.model.Result;
import com.example.tsmpc47.catalogmovie.ui.base.BaseViewModel;
import com.example.tsmpc47.catalogmovie.utils.rx.SchedulerProvider;

import java.util.List;

import io.reactivex.functions.Consumer;

public class FavoriteViewModel extends BaseViewModel<FavoriteNavigator> {

    private final ObservableArrayList<Result> favoriteViewModels = new ObservableArrayList<>();
    private final MutableLiveData<List<Result>> favoriteLiveData;
    private static final String TAG = "FavoriteViewModel";

    public FavoriteViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        favoriteLiveData = new MutableLiveData<>();
        favorite();
    }

    private void favorite() {
        setIsLoading(true);

        getCompositeDisposable().add(getDataManager().getDataFavorite()
            .subscribeOn(getSchedulerProvider().io())
            .observeOn(getSchedulerProvider().ui())
            .subscribe(new Consumer<List<Result>>() {
                @Override
                public void accept(List<Result> favorites) throws Exception {
                    for (int i = 0; i < favorites.size(); i++) {
                        Log.i(TAG, "accept: "+favorites.get(i).getTitle());
                    }
                    favoriteLiveData.setValue(favorites);
                    setIsLoading(false);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    Log.e(TAG, "accept: "+throwable.getMessage());
                    setIsLoading(false);
                }
            }));
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
