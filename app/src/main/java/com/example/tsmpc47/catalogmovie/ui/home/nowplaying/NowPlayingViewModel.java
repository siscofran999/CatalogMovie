package com.example.tsmpc47.catalogmovie.ui.home.nowplaying;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.util.Log;

import com.example.tsmpc47.catalogmovie.data.DataManager;
import com.example.tsmpc47.catalogmovie.data.model.NowPlaying;
import com.example.tsmpc47.catalogmovie.data.model.Result;
import com.example.tsmpc47.catalogmovie.ui.base.BaseViewModel;
import com.example.tsmpc47.catalogmovie.utils.NetworkUtils;
import com.example.tsmpc47.catalogmovie.utils.rx.SchedulerProvider;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

public class NowPlayingViewModel extends BaseViewModel<NowPlayingNavigator> {

    private static final String TAG = "NowPlayingViewModel";
    private final ObservableArrayList<Result> nowItemViewModels = new ObservableArrayList<>();
    private final MutableLiveData<List<Result>> nowItemLiveData;

    public NowPlayingViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        nowItemLiveData = new MutableLiveData<>();
        nowPlaying();
    }

    public void nowPlaying(){
        setIsLoading(true);

        getCompositeDisposable().add(getDataManager().getNowPlayingData()
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<NowPlaying>() {
                    @Override
                    public void accept(NowPlaying nowPlaying) {
                        if (nowPlaying.getTotalResults() != 0){
                            nowItemLiveData.setValue(nowPlaying.getResults());
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

    public ObservableArrayList<Result> getNowItemViewModels() {
        return nowItemViewModels;
    }

    public MutableLiveData<List<Result>> getNowItemLiveData() {
        return nowItemLiveData;
    }

    public void addNowItemsToList(List<Result> results){
        nowItemViewModels.clear();
        nowItemViewModels.addAll(results);
    }
}
