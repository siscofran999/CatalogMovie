package com.example.tsmpc47.catalogmovie.ui.home.favorite;

import android.databinding.ObservableField;

import com.example.tsmpc47.catalogmovie.BuildConfig;
import com.example.tsmpc47.catalogmovie.data.model.Result;

import static com.example.tsmpc47.catalogmovie.utils.CommonUtils.converDate;
import static com.example.tsmpc47.catalogmovie.utils.CommonUtils.cutText;

public class FavoriteItemViewModel {

    private Result mResult;
    private ItemFavoriteListener mListener;
    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableField<String> judulnow = new ObservableField<>();
    public ObservableField<String> descnow = new ObservableField<>();
    public ObservableField<String> datenow = new ObservableField<>();
    public ObservableField<String> star = new ObservableField<>();

    public FavoriteItemViewModel(Result result, ItemFavoriteListener listener) {
        this.mResult = result;
        mListener = listener;
        this.imageUrl.set(BuildConfig.POSTER+result.getPosterPath());
        this.judulnow.set(mResult.getTitle());
        this.descnow.set(cutText(mResult.getOverview()));
        this.datenow.set(converDate(mResult.getReleaseDate()));
        this.star.set(String.valueOf(mResult.getVoteAverage()/2));
    }

    public void detail(){
        mListener.clickMovieDetailActivity(mResult);
    }

    public void share(){
        mListener.shareButton("Ada Film "+mResult.getTitle()+" di bioskop nih, yuk pergi nonton");
    }

    public interface ItemFavoriteListener {
        void clickMovieDetailActivity(Result result);

        void shareButton(String judul);
    }
}
