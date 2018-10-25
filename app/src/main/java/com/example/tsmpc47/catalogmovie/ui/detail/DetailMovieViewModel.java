package com.example.tsmpc47.catalogmovie.ui.detail;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.net.Uri;
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

import static android.provider.BaseColumns._ID;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.CONTENT_URI;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.bintang;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.deskripsi;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.fovorite;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.gambar;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.judul;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.popular;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.tgl;
import static com.example.tsmpc47.catalogmovie.utils.CommonUtils.converDate;

public class DetailMovieViewModel extends BaseViewModel<DetailMovieNavigator> {

    public ObservableField<String> img = new ObservableField<>();
    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> overview = new ObservableField<>();
    public ObservableField<String> date = new ObservableField<>();
    public ObservableField<String> popularity = new ObservableField<>();
    public ObservableBoolean isStarSelected = new ObservableBoolean();
    private String posterPath_k = "";
    private String tanggal_k = "";
    private String judul_k = "";
    private String deskr_k = "";
    private String rating_k = "";
    private String popular_k = "";
    private int id;
    private static final String TAG = "DetailMovieViewModel";
    private Cursor list;

    private boolean isFavorite;

    public DetailMovieViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        setOpenDb();
    }

    public void setDataMovie(Result result, Resources resources) {
        this.img.set(BuildConfig.POSTER+result.getPosterPath());
        this.date.set(resources.getString(R.string.date)+" "+converDate(result.getReleaseDate()));
        this.title.set(resources.getString(R.string.title)+" "+result.getTitle());
        this.overview.set(resources.getString(R.string.overview)+" "+result.getOverview());
        this.popularity.set(resources.getString(R.string.popularity)+" "+String.valueOf(result.getPopularity()));
        id = result.getId();
        posterPath_k = result.getPosterPath();
        tanggal_k = result.getReleaseDate();
        judul_k = result.getTitle();
        deskr_k = result.getOverview();
        rating_k = String.valueOf(result.getVoteAverage()/2);
        popular_k = String.valueOf(result.getPopularity());
    }

    public void favorite(){
        isFavorite = !isFavorite;
        Log.i(TAG, "favorite: "+isFavorite);
        if (isFavorite){
            getNavigator().toastBerhasil();
            isStarSelected.set(true);
        }else{
            getNavigator().toastDelete();
            isStarSelected.set(false);
        }
    }

    public void insertContentProvider(ContentResolver contentResolver) {
        Log.i(TAG, "insertContentProvider: insert");
        ContentValues values = new ContentValues();
        values.put(_ID, id);
        values.put(judul, judul_k);
        values.put(deskripsi, deskr_k);
        values.put(tgl, tanggal_k);
        values.put(gambar, posterPath_k);
        values.put(bintang, rating_k);
        values.put(popular, popular_k);
        values.put(fovorite, true);

        contentResolver.insert(CONTENT_URI, values);
        Log.i(TAG, "insertProvider: "+values.get(judul));
    }

    public void deleteContentProvider(ContentResolver contentResolver, Uri data) {
        Log.i(TAG, "deleteContentProvider: delete "+data);
        contentResolver.delete(data, null, null);
    }

    public void setOpenDb() {
        getDataManager().openDB();
    }

    public void setMovieFavorite() {
        isFavorite = true;
        isStarSelected.set(isFavorite);
    }

    public void closeDb() {
        getDataManager().closeDb();
    }
}
