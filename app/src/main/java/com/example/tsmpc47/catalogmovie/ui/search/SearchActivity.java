package com.example.tsmpc47.catalogmovie.ui.search;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tsmpc47.catalogmovie.BR;
import com.example.tsmpc47.catalogmovie.R;
import com.example.tsmpc47.catalogmovie.data.model.Result;
import com.example.tsmpc47.catalogmovie.databinding.ActivitySearchBinding;
import com.example.tsmpc47.catalogmovie.ui.base.BaseActivity;
import com.example.tsmpc47.catalogmovie.utils.NetworkUtils;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

import javax.inject.Inject;

public class SearchActivity extends BaseActivity<ActivitySearchBinding, SearchViewModel> implements SearchNavigator {

    private static final String TAG = "SearchActivity";
    
    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    LinearLayoutManager mLayoutManager;

    @Inject
    SearchAdapter mSearchAdapter;

    private SearchViewModel mSearchViewModel;

    private ActivitySearchBinding mActivitySearchBinding;

    public static Intent gotoSearchActivity(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySearchBinding = getViewDataBinding();
        mSearchViewModel.setNavigator(this);
        setSupportActionBar(mActivitySearchBinding.toolbar);
        setUp();
        subscribeToLiveData();
    }

    private void subscribeToLiveData() {
        mSearchViewModel.getSearchLiveData().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(@Nullable List<Result> results) {
                mSearchViewModel.addSearchItemsToList(results);
            }
        });
    }

    private void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mActivitySearchBinding.rvSearch.setLayoutManager(mLayoutManager);
        mActivitySearchBinding.rvSearch.setItemAnimator(new DefaultItemAnimator());
        mActivitySearchBinding.rvSearch.setAdapter(mSearchAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        mActivitySearchBinding.searchView.setMenuItem(item);

        setSearchView();

        return true;
    }

    private void setSearchView() {
        mActivitySearchBinding.searchView.showSearch();
        mActivitySearchBinding.searchView.setVoiceSearch(false);
        mActivitySearchBinding.searchView.setEllipsize(true);
        mActivitySearchBinding.searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mActivitySearchBinding.searchView.closeSearch();
                return true;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                if (newText.length() >=3){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mSearchViewModel.search(newText);
                        }
                    }, 500);
                }
                return true;
            }
        });

        mActivitySearchBinding.searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                Log.i(TAG, "onSearchViewShown: ");
            }

            @Override
            public void onSearchViewClosed() {
                Log.i(TAG, "onSearchViewClosed: ");
                finish();
            }
        });
    }

    @Override
    public void nullData() {
        Toast.makeText(this, "Maaf, tidak ada film sekarang", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkConnection() {
        if (!NetworkUtils.isNetworkConnected(this)){
            Toast.makeText(this, "Maaf, harap cek internet anda", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public SearchViewModel getViewModel() {
        mSearchViewModel = ViewModelProviders.of(this, mViewModelFactory).get(SearchViewModel.class);
        return mSearchViewModel;
    }
}
