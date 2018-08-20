package com.example.tsmpc47.catalogmovie.ui.detail;

import android.databinding.ObservableField;
import android.util.Log;

import com.example.tsmpc47.catalogmovie.BuildConfig;
import com.example.tsmpc47.catalogmovie.data.DataManager;
import com.example.tsmpc47.catalogmovie.data.model.Result;
import com.example.tsmpc47.catalogmovie.ui.base.BaseViewModel;
import com.example.tsmpc47.catalogmovie.utils.rx.SchedulerProvider;

import static com.example.tsmpc47.catalogmovie.utils.CommonUtils.converDate;

public class DetailMovieViewModel extends BaseViewModel<DetailMovieNavigator> {

    public ObservableField<String> img = new ObservableField<>();
    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> overview = new ObservableField<>();
    public ObservableField<String> date = new ObservableField<>();

    public DetailMovieViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void setDataMovie(Result result) {
        this.img.set(BuildConfig.POSTER+result.getPosterPath());
        this.date.set("Tanggal Rilis : "+converDate(result.getReleaseDate()));
        this.title.set(result.getTitle());
        this.overview.set("Deskripsi : "+result.getOverview());
    }
}
