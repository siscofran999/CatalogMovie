package com.example.tsmpc47.catalogmovie.ui.home.nowplaying;

import android.content.Intent;
import android.databinding.ObservableField;
import android.util.Log;

import com.example.tsmpc47.catalogmovie.BuildConfig;
import com.example.tsmpc47.catalogmovie.data.model.Result;

import static com.example.tsmpc47.catalogmovie.utils.CommonUtils.converDate;
import static com.example.tsmpc47.catalogmovie.utils.CommonUtils.cutText;

public class NowPlayingItemViewModel {

    private static final String TAG = "NowPlayingItemViewModel";
    private ItemNowPlayingListener mListerner;

    private Result mResult;
    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableField<String> judulnow = new ObservableField<>();
    public ObservableField<String> descnow = new ObservableField<>();
    public ObservableField<String> datenow = new ObservableField<>();
    public ObservableField<String> star = new ObservableField<>();

    public NowPlayingItemViewModel(Result result, ItemNowPlayingListener listener) {
        this.mResult = result;
        mListerner = listener;
        this.imageUrl.set(BuildConfig.POSTER+result.getPosterPath());
        this.judulnow.set(mResult.getTitle());
        this.descnow.set(cutText(mResult.getOverview()));
        this.datenow.set(converDate(mResult.getReleaseDate()));
        this.star.set(String.valueOf(mResult.getVoteAverage()/2));
    }

    public void detail(){
        mListerner.clickMovieDetailActivity(mResult);
    }

    public void share(){
        mListerner.shareButton("Ada Film "+mResult.getTitle()+" di bioskop nih, yuk pergi nonton");
    }

    public interface ItemNowPlayingListener {
        void clickMovieDetailActivity(Result result);

        void shareButton(String judul);
    }
}
