package com.example.tsmpc47.catalogmovie.ui.home.favorite;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.example.tsmpc47.catalogmovie.BR;
import com.example.tsmpc47.catalogmovie.R;
import com.example.tsmpc47.catalogmovie.data.model.Result;
import com.example.tsmpc47.catalogmovie.databinding.FragmentFavoriteBinding;
import com.example.tsmpc47.catalogmovie.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

public class FavoriteFragment extends BaseFragment<FragmentFavoriteBinding, FavoriteViewModel> implements FavoriteNavigator {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    FavoriteAdapter mFavoriteAdapter;

    @Inject
    LinearLayoutManager mLinearLayoutManager;

    private FavoriteViewModel mFavoriteViewModel;
    FragmentFavoriteBinding mFragmentFavoriteBinding;

    private static final String TAG = "FavoriteFragment";

    public static FavoriteFragment newInstance() {
        Bundle args = new Bundle();
        FavoriteFragment fragment = new FavoriteFragment();
        fragment.setArguments(args);
        Log.i(TAG, "newInstance: ");
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentFavoriteBinding = getViewDataBinding();
        setUp();
        subscribeToLiveData();
    }

    private void subscribeToLiveData() {
        mFavoriteViewModel.getFavoriteLiveData().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(@Nullable List<Result> favorites) {
                mFavoriteViewModel.addFavoriteToList(favorites);
            }
        });
    }

    private void setUp() {
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentFavoriteBinding.rvfavorite.setLayoutManager(mLinearLayoutManager);
        mFragmentFavoriteBinding.rvfavorite.setItemAnimator(new DefaultItemAnimator());
        mFragmentFavoriteBinding.rvfavorite.setAdapter(mFavoriteAdapter);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_favorite;
    }

    @Override
    public FavoriteViewModel getViewModel() {
        mFavoriteViewModel = ViewModelProviders.of(this, mViewModelFactory).get(FavoriteViewModel.class);
        return mFavoriteViewModel;
    }

}
