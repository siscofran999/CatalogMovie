package com.example.tsmpc47.catalogmovie.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.example.tsmpc47.catalogmovie.BR;
import com.example.tsmpc47.catalogmovie.R;
import com.example.tsmpc47.catalogmovie.data.model.Result;
import com.example.tsmpc47.catalogmovie.databinding.ActivityMainBinding;
import com.example.tsmpc47.catalogmovie.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainNavigator {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    MainAdapter mMainAdapter;

    @Inject
    LinearLayoutManager mLinearLayoutManager;

    private MainViewModel mMainViewModel;
    ActivityMainBinding mActivityMainBinding;
    private static final String TAG = "MainActivity";

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = getViewDataBinding();
        mMainViewModel.setNavigator(this);
        setUp();
        subscribeToLiveData();
    }

    private void subscribeToLiveData() {
        mMainViewModel.getMainItemLiveData().observe(this, results ->
                mMainViewModel.addMainItemsToList(results));
    }

    private void setUp() {
        mActivityMainBinding.recycleView.setLayoutManager(mLinearLayoutManager);
        mActivityMainBinding.recycleView.setAdapter(mMainAdapter);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        mMainViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel.class);
        return mMainViewModel;
    }

    @Override
    public void search() {
        String inputSearh = mActivityMainBinding.search.getText().toString();
        Log.i(TAG, "search: "+inputSearh);
        if (!inputSearh.equals("") && isNetworkConnected()){
            Log.i(TAG, "masuk sini: ");
            mMainViewModel.search(inputSearh);
        }else if (inputSearh.equals("")){
            Toast.makeText(this, "Maaf, Masih kosong inputannya", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Maaf, harap cek koneksi", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void nullData() {
        Toast.makeText(this, "Maaf, kata kunci tidak ditemukan", Toast.LENGTH_SHORT).show();
    }
}
