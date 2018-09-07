package com.example.tsmpc47.catalogmovie.ui.search;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.util.Log;

import com.example.tsmpc47.catalogmovie.data.DataManager;
import com.example.tsmpc47.catalogmovie.data.model.MovieResponse;
import com.example.tsmpc47.catalogmovie.data.model.Result;
import com.example.tsmpc47.catalogmovie.ui.base.BaseViewModel;
import com.example.tsmpc47.catalogmovie.utils.rx.SchedulerProvider;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

public class SearchViewModel extends BaseViewModel<SearchNavigator>{

    private final ObservableArrayList<Result> searchItemViewModels = new ObservableArrayList<>();
    private final MutableLiveData<List<Result>> searchLiveData;

    private static final String TAG = "SearchViewModel";

    public SearchViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        searchLiveData = new MutableLiveData<>();
    }

    public void search(String inputSearh) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager().getMovieData(inputSearh)
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<MovieResponse>() {
                    @Override
                    public void accept(MovieResponse movieResponse) {
                        if (movieResponse.getTotalResults() != 0){
                            searchLiveData.setValue(movieResponse.getResults());
                        }else{
                            getNavigator().nullData();
                        }
                        setIsLoading(false);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        getNavigator().checkConnection();
                        Log.e(TAG, "accept: "+throwable.getMessage());
                        setIsLoading(false);
                    }
                }));
    }

    public MutableLiveData<List<Result>> getSearchLiveData() {
        return searchLiveData;
    }

    public ObservableArrayList<Result> getSearchItemViewModels() {
        return searchItemViewModels;
    }

    public void addSearchItemsToList(List<Result> results) {
        searchItemViewModels.clear();
        searchItemViewModels.addAll(results);
    }
}
