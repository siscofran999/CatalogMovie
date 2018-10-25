package com.example.tsmpc47.catalogmovie.ui.home.favorite;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tsmpc47.catalogmovie.BR;
import com.example.tsmpc47.catalogmovie.R;
import com.example.tsmpc47.catalogmovie.data.model.Result;
import com.example.tsmpc47.catalogmovie.databinding.FragmentFavoriteBinding;
import com.example.tsmpc47.catalogmovie.ui.base.BaseFragment;
import com.example.tsmpc47.catalogmovie.ui.detail.DetailMovieActivity;

import java.util.List;

import javax.inject.Inject;

import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.CONTENT_URI;
import static com.example.tsmpc47.catalogmovie.ui.detail.DetailMovieActivity.REQUEST_UPDATE;
import static com.example.tsmpc47.catalogmovie.ui.detail.DetailMovieActivity.RESULT_DELETE;

public class FavoriteFragment extends BaseFragment<FragmentFavoriteBinding, FavoriteViewModel> implements FavoriteNavigator {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    FavoriteAdapter mFavoriteAdapter;

    @Inject
    LinearLayoutManager mLinearLayoutManager;

    @Inject
    FavoriteViewModel mFavoriteViewModel;

    FragmentFavoriteBinding mFragmentFavoriteBinding;

    private Cursor listFavorite;

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
        new LoadFavorite().execute();
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
        mFavoriteAdapter.setListFavorite(listFavorite);
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

    private class LoadFavorite extends AsyncTask<Void, Void, Cursor> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            return getBaseActivity().getContentResolver().query(CONTENT_URI,null,null,null,null);
        }

        @Override
        protected void onPostExecute(Cursor notes) {
            super.onPostExecute(notes);

            listFavorite = notes;
            Log.i(TAG, "onPostExecute: "+notes.getCount());
            mFavoriteAdapter.setListFavorite(listFavorite);
            mFavoriteAdapter.notifyDataSetChanged();

            Log.i(TAG, "onPostExecute: " + listFavorite.getCount());

            if (listFavorite.getCount() == 0){
                Toast.makeText(getActivity(), "Maaf, Kosong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == DetailMovieActivity.REQUEST_ADD){
            if (resultCode == DetailMovieActivity.RESULT_ADD){
                new LoadFavorite().execute();
                Toast.makeText(getActivity(), "Satu item berhasil ditambahkan", Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == REQUEST_UPDATE) {

            if (resultCode == DetailMovieActivity.RESULT_UPDATE) {
                new LoadFavorite().execute();
                Toast.makeText(getActivity(), "Satu item berhasil diubah", Toast.LENGTH_SHORT).show();
            }

            else if (resultCode == RESULT_DELETE) {
                new LoadFavorite().execute();
                Toast.makeText(getActivity(), "Satu item berhasil dihapus", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
