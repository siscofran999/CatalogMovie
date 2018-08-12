package com.example.tsmpc47.catalogmovie.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

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

    public static Intent gotoDetailMovieActivity(Context context, Result movie){
        Intent intent = new Intent(context, DetailMovieActivity.class);
        intent.putExtra("dataMovie", movie);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityDetailMovieBinding = getViewDataBinding();
        getDataIntent();
    }

    private void getDataIntent() {
        Intent intent = getIntent();
        Result result = intent.getParcelableExtra("dataMovie");
        mDetailMovieViewModel.setDataMovie(result);
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
}
