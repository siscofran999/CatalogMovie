package com.example.tsmpc47.catalogmovie.ui.home.upcoming;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.tsmpc47.catalogmovie.R;
import com.example.tsmpc47.catalogmovie.data.model.Result;
import com.example.tsmpc47.catalogmovie.databinding.FragmentUpcomingBinding;
import com.example.tsmpc47.catalogmovie.ui.base.BaseFragment;
import com.example.tsmpc47.catalogmovie.BR;
import com.example.tsmpc47.catalogmovie.utils.NetworkUtils;

import java.util.List;

import javax.inject.Inject;

public class UpComingFragment extends BaseFragment<FragmentUpcomingBinding,UpComingViewModel> implements UpComingNavigator {

    private static final String TAG = "UpComingFragment";
    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    UpComingAdapter mUpComingAdapter;

    @Inject
    GridLayoutManager mGridLayoutManager;

    private UpComingViewModel mUpComingViewModel;
    FragmentUpcomingBinding mFragmentUpComingBinding;

    public static UpComingFragment newInstance() {

        Bundle args = new Bundle();

        UpComingFragment fragment = new UpComingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentUpComingBinding = getViewDataBinding();
        setUp();
        subscribeToLiveData();
    }

    private void subscribeToLiveData() {
        mUpComingViewModel.getUpItemLiveData().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(@Nullable List<Result> results) {
                mUpComingViewModel.addUpItemsToList(results);
            }
        });
    }

    private void setUp() {
        mFragmentUpComingBinding.rvupcoming.setLayoutManager(mGridLayoutManager);
        mFragmentUpComingBinding.rvupcoming.setItemAnimator(new DefaultItemAnimator());
        mFragmentUpComingBinding.rvupcoming.setAdapter(mUpComingAdapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUpComingViewModel.setNavigator(this);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_upcoming;
    }

    @Override
    public UpComingViewModel getViewModel() {
        mUpComingViewModel = ViewModelProviders.of(this, mViewModelFactory).get(UpComingViewModel.class);
        return mUpComingViewModel;
    }

    @Override
    public void nullData() {
        Toast.makeText(getActivity(), "Maaf, tidak ada film sekarang", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkConnection() {
        if (!NetworkUtils.isNetworkConnected(getActivity())){
            Toast.makeText(getActivity(), "Maaf, harap cek internet anda", Toast.LENGTH_SHORT).show();
        }
    }
}
