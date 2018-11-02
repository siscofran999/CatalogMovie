package com.example.favorite.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.example.favorite.R;
import com.example.favorite.data.model.Result;
import com.example.favorite.databinding.ActivityMainBinding;
import com.example.favorite.ui.base.BaseActivity;
import com.example.favorite.BR;

import java.util.List;

import javax.inject.Inject;

import static com.example.favorite.data.db.DatabaseContract.CONTENT_URI;


public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainNavigator {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    MainAdapter mMainAdapter;

    @Inject
    LinearLayoutManager mLinearLayoutManager;

    @Inject
    MainViewModel mMainViewModel;

    ActivityMainBinding mActivityMainBinding;

    private Cursor listFavorite;

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
        subscribeToLiveData();
        setUp();
        new LoadFavorite().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void subscribeToLiveData() {
        mMainViewModel.getFavoriteLiveData().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(@Nullable List<Result> favorites) {
                mMainViewModel.addFavoriteToList(favorites);
            }
        });
    }

    private void setUp() {
        mMainAdapter.setListMain(listFavorite);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mActivityMainBinding.rvmain.setLayoutManager(mLinearLayoutManager);
        mActivityMainBinding.rvmain.setItemAnimator(new DefaultItemAnimator());
        mActivityMainBinding.rvmain.setAdapter(mMainAdapter);
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
    protected void onDestroy() {
        super.onDestroy();
    }

    private class LoadFavorite extends AsyncTask<Void, Void, Cursor> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            return getContentResolver().query(CONTENT_URI,null,null,null,null);
        }

        @Override
        protected void onPostExecute(Cursor notes) {
            super.onPostExecute(notes);

            listFavorite = notes;
            //Log.i(TAG, "onPostExecute: "+notes.getCount());
            mMainAdapter.setListMain(listFavorite);
            mMainAdapter.notifyDataSetChanged();

            Log.i(TAG, "onPostExecute: " + listFavorite.getCount());

            if (listFavorite.getCount() == 0){
                Toast.makeText(MainActivity.this, "Maaf, Kosong", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
