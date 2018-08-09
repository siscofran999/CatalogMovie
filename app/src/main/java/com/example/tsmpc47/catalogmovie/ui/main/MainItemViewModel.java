package com.example.tsmpc47.catalogmovie.ui.main;

import android.databinding.ObservableField;
import android.util.Log;

import com.example.tsmpc47.catalogmovie.BuildConfig;
import com.example.tsmpc47.catalogmovie.data.model.Result;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.tsmpc47.catalogmovie.utils.CommonUtils.converDate;
import static com.example.tsmpc47.catalogmovie.utils.CommonUtils.cutText;

public class MainItemViewModel {

    private Result mResult;
    private ItemMainViewListener mListener;
    private static final String TAG = "MainItemViewModel";

    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> overview = new ObservableField<>();
    public ObservableField<String> date = new ObservableField<>();

    public MainItemViewModel(Result result, ItemMainViewListener listener) {
        this.mResult = result;
        this.mListener = listener;
        Log.i(TAG, "MainItemViewModel: "+result.getTitle());

        this.imageUrl.set(BuildConfig.POSTER+result.getPosterPath());
        this.title.set(result.getTitle());
        this.overview.set(cutText(result.getOverview()));
        this.date.set(converDate(result.getReleaseDate()));
    }

    public void clickMovieDetailActivity() {
        mListener.clickMovieDetailActivity(mResult);
    }

    public interface ItemMainViewListener {
        void clickMovieDetailActivity(Result result);
    }
}
