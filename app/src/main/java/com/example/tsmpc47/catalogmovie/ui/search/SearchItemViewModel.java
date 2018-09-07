package com.example.tsmpc47.catalogmovie.ui.search;

import android.databinding.ObservableField;

import com.example.tsmpc47.catalogmovie.BuildConfig;
import com.example.tsmpc47.catalogmovie.data.model.Result;

import static com.example.tsmpc47.catalogmovie.utils.CommonUtils.converDate;

public class SearchItemViewModel {

    private Result mResult;
    private ItemSearchListener mListener;

    public ObservableField<String> imgSearch = new ObservableField<>();
    public ObservableField<String> judulSearch = new ObservableField<>();
    public ObservableField<String> ratingSearch = new ObservableField<>();
    public ObservableField<String> dateSearch = new ObservableField<>();

    public SearchItemViewModel(Result result, ItemSearchListener listener) {
        this.mResult = result;
        mListener = listener;
        this.imgSearch.set(BuildConfig.POSTER+result.getPosterPath());
        this.judulSearch.set(mResult.getTitle());
        this.ratingSearch.set(String.valueOf(mResult.getVoteAverage()/2));
        this.dateSearch.set(converDate(mResult.getReleaseDate()));
    }

    public void detail(){
        mListener.clickMovieDetailActivity(mResult);
    }

    public interface ItemSearchListener {
        void clickMovieDetailActivity(Result result);
    }
}
