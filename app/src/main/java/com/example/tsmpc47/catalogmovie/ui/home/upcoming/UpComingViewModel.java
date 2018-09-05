package com.example.tsmpc47.catalogmovie.ui.home.upcoming;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.util.Log;

import com.example.tsmpc47.catalogmovie.data.DataManager;
import com.example.tsmpc47.catalogmovie.data.model.NowPlaying;
import com.example.tsmpc47.catalogmovie.data.model.Result;
import com.example.tsmpc47.catalogmovie.ui.base.BaseViewModel;
import com.example.tsmpc47.catalogmovie.utils.rx.SchedulerProvider;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

public class UpComingViewModel extends BaseViewModel<UpComingNavigator> {

    private static final String TAG = "UpComingViewModel";
    private final ObservableArrayList<Result> upItemViewModels = new ObservableArrayList<>();
    private final MutableLiveData<List<Result>> upItemLiveData;

    public UpComingViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        upItemLiveData = new MutableLiveData<>();
        upComing();
    }

    private void upComing() {
        setIsLoading(true);

        getCompositeDisposable().add(getDataManager().getUpComingData()
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<NowPlaying>() {
                    @Override
                    public void accept(NowPlaying nowPlaying) throws Exception {
                        Log.i(TAG, "accept: "+nowPlaying.getTotalResults());
                        if (nowPlaying.getTotalResults() != 0){
                            upItemLiveData.setValue(nowPlaying.getResults());
                        }else{
                            getNavigator().nullData();
                        }
                        setIsLoading(false);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getNavigator().checkConnection();
                        Log.e(TAG, "accept: "+throwable.getMessage());
                        setIsLoading(false);
                    }
                }));
    }

    public MutableLiveData<List<Result>> getUpItemLiveData() {
        return upItemLiveData;
    }

    public ObservableArrayList<Result> getUpItemViewModels() {
        return upItemViewModels;
    }

    public void addUpItemsToList(List<Result> results){
        upItemViewModels.clear();
        upItemViewModels.addAll(results);
    }
}
