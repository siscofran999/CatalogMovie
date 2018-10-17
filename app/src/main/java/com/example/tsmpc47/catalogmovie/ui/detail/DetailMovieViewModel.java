package com.example.tsmpc47.catalogmovie.ui.detail;

import android.content.res.Resources;
import android.databinding.ObservableField;
import android.util.Log;

import com.example.tsmpc47.catalogmovie.BuildConfig;
import com.example.tsmpc47.catalogmovie.R;
import com.example.tsmpc47.catalogmovie.data.DataManager;
import com.example.tsmpc47.catalogmovie.data.model.MovieResponse;
import com.example.tsmpc47.catalogmovie.data.model.Result;
import com.example.tsmpc47.catalogmovie.ui.base.BaseViewModel;
import com.example.tsmpc47.catalogmovie.utils.rx.SchedulerProvider;

import java.util.List;

import javax.security.auth.login.LoginException;

import static com.example.tsmpc47.catalogmovie.utils.CommonUtils.converDate;

public class DetailMovieViewModel extends BaseViewModel<DetailMovieNavigator> {

    public ObservableField<String> img = new ObservableField<>();
    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> overview = new ObservableField<>();
    public ObservableField<String> date = new ObservableField<>();
    public ObservableField<String> popularity = new ObservableField<>();
    private String posterPath = "";
    private String tanggal = "";
    private String judul = "";
    private String deskr = "";
    private String rating = "";
    private static final String TAG = "DetailMovieViewModel";

    public DetailMovieViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void setDataMovie(Result result, Resources resources) {
        this.img.set(BuildConfig.POSTER+result.getPosterPath());
        this.date.set(resources.getString(R.string.date)+" "+converDate(result.getReleaseDate()));
        this.title.set(resources.getString(R.string.title)+" "+result.getTitle());
        this.overview.set(resources.getString(R.string.overview)+" "+result.getOverview());
        this.popularity.set(resources.getString(R.string.popularity)+" "+String.valueOf(result.getPopularity()));
        posterPath = result.getPosterPath();
        tanggal = result.getReleaseDate();
        judul = result.getTitle();
        deskr = result.getOverview();
        rating = String.valueOf(result.getVoteAverage()/2);
    }

    public void favorite(){
        getDataManager().openDB();
        int jml = getDataManager().searchData(judul);
        if(jml == 1){
            getNavigator().sudahAda();
        }else{
            getDataManager().insertDB(posterPath,judul,deskr,tanggal,rating);
            getNavigator().toastBerhasil();
        }
    }
}
