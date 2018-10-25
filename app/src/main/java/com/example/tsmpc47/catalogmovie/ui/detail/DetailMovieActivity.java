package com.example.tsmpc47.catalogmovie.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.tsmpc47.catalogmovie.R;
import com.example.tsmpc47.catalogmovie.data.model.Result;
import com.example.tsmpc47.catalogmovie.databinding.ActivityDetailMovieBinding;
import com.example.tsmpc47.catalogmovie.ui.base.BaseActivity;
import com.example.tsmpc47.catalogmovie.BR;

import javax.inject.Inject;

public class DetailMovieActivity extends BaseActivity<ActivityDetailMovieBinding, DetailMovieViewModel> implements DetailMovieNavigator {

    @Inject
    DetailMovieViewModel mDetailMovieViewModel;

    private ActivityDetailMovieBinding mActivityDetailMovieBinding;
    private static final String TAG = "DetailMovieActivity";

    private Result mResult;

    public static int REQUEST_ADD = 100;
    public static int RESULT_ADD = 101;
    public static int REQUEST_UPDATE = 200;
    public static int RESULT_UPDATE = 201;
    public static int RESULT_DELETE = 301;

    public static Intent gotoDetailMovieActivity(Context context, Result movie){
        Intent intent = new Intent(context, DetailMovieActivity.class);
        Log.i(TAG, "gotoDetailMovieActivity: "+movie.getId());
        intent.putExtra("dataMovie", movie);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityDetailMovieBinding = getViewDataBinding();
        mDetailMovieViewModel.setNavigator(this);
        getDataIntent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDetailMovieViewModel.closeDb();
    }

    private void getDataIntent() {
        mDetailMovieViewModel.setOpenDb();
        Uri uri = getIntent().getData();
        Log.i(TAG, "setCursor: "+uri);

        if (uri != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            Log.i(TAG, "setCursor: "+cursor.getCount());
            if (cursor != null){
                if(cursor.moveToFirst()) mResult = new Result(cursor);
                cursor.close();
            }
        }
        if (mResult != null) {
            mDetailMovieViewModel.setMovieFavorite();
        }else {
            mResult = getIntent().getParcelableExtra("dataMovie");
        }

        mDetailMovieViewModel.setDataMovie(mResult, getResources());
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail_movie;
    }

    @Override
    public DetailMovieViewModel getViewModel() {
        return mDetailMovieViewModel;
    }

    @Override
    public void toastBerhasil() {
        mDetailMovieViewModel.insertContentProvider(getContentResolver());
        Toast.makeText(this, "Berhasil Menambahkan Favorite", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastDelete() {
        mDetailMovieViewModel.deleteContentProvider(getContentResolver(),getIntent().getData());
        Toast.makeText(this, "Berhasil Menghapus Favorite", Toast.LENGTH_SHORT).show();
    }
}
