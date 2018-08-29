package com.example.tsmpc47.catalogmovie.ui.home.nowplaying;

import android.databinding.ObservableField;

import com.example.tsmpc47.catalogmovie.BuildConfig;
import com.example.tsmpc47.catalogmovie.data.model.Result;

import static com.example.tsmpc47.catalogmovie.utils.CommonUtils.converDate;
import static com.example.tsmpc47.catalogmovie.utils.CommonUtils.cutText;

public class NowPlayingItemViewModel {

    private Result mResult;
    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableField<String> judulnow = new ObservableField<>();
    public ObservableField<String> descnow = new ObservableField<>();
    public ObservableField<String> datenow = new ObservableField<>();

    public NowPlayingItemViewModel(Result result) {
        this.mResult = result;

        this.imageUrl.set(BuildConfig.POSTER+result.getPosterPath());
        this.judulnow.set(result.getTitle());
        this.descnow.set(cutText(result.getOverview()));
        this.datenow.set(converDate(result.getReleaseDate()));
    }
}
