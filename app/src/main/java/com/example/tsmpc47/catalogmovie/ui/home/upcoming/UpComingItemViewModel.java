package com.example.tsmpc47.catalogmovie.ui.home.upcoming;

import android.databinding.ObservableField;

import com.example.tsmpc47.catalogmovie.BuildConfig;
import com.example.tsmpc47.catalogmovie.data.model.Result;

public class UpComingItemViewModel {

    private ItemUpComingListener mListener;
    private Result mResult;

    public ObservableField<String> imageup = new ObservableField<>();
    public ObservableField<String> judulup = new ObservableField<>();
    public ObservableField<String> starup = new ObservableField<>();

    public UpComingItemViewModel(Result result, ItemUpComingListener listener) {
        this.mListener = listener;
        this.mResult = result;
        this.imageup.set(BuildConfig.POSTER+result.getPosterPath());
        this.judulup.set(mResult.getTitle());
        this.starup.set(String.valueOf(mResult.getVoteAverage()/2));
    }

    public void detail(){
        mListener.clickMovieDetailActivity(mResult);
    }

    public interface ItemUpComingListener {
        void clickMovieDetailActivity(Result result);
    }

}
