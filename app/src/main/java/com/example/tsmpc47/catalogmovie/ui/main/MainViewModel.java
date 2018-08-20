package com.example.tsmpc47.catalogmovie.ui.main;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.util.Log;
import android.widget.Toast;

import com.example.tsmpc47.catalogmovie.data.DataManager;
import com.example.tsmpc47.catalogmovie.data.model.MovieResponse;
import com.example.tsmpc47.catalogmovie.data.model.Result;
import com.example.tsmpc47.catalogmovie.ui.base.BaseViewModel;
import com.example.tsmpc47.catalogmovie.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

public class MainViewModel extends BaseViewModel<MainNavigator> {

    private static final String TAG = "MainViewModel";
    private final ObservableArrayList<Result> mainItemViewModels = new ObservableArrayList<>();
    private final MutableLiveData<List<Result>> mainItemLiveData;

    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        mainItemLiveData = new MutableLiveData<>();
    }

    public void searchClick(){
        getNavigator().search();
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
                            mainItemLiveData.setValue(movieResponse.getResults());
                        }else{
                            getNavigator().nullData();
                        }
                        setIsLoading(false);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Log.e(TAG, "accept: "+throwable.getMessage());
                        setIsLoading(false);

                    }
                }));
    }

    public ObservableArrayList<Result> getMainItemViewModels() {
        return mainItemViewModels;
    }

    public MutableLiveData<List<Result>> getMainItemLiveData() {
        return mainItemLiveData;
    }

    public void addMainItemsToList(List<Result> results) {
        mainItemViewModels.clear();
        mainItemViewModels.addAll(results);

    }
}
