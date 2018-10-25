package com.example.tsmpc47.catalogmovie.ui.home.favorite;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tsmpc47.catalogmovie.data.DataManager;
import com.example.tsmpc47.catalogmovie.data.model.Result;
import com.example.tsmpc47.catalogmovie.databinding.ItemFavoriteBinding;
import com.example.tsmpc47.catalogmovie.ui.base.BaseViewHolder;
import com.example.tsmpc47.catalogmovie.ui.detail.DetailMovieActivity;
import com.example.tsmpc47.catalogmovie.utils.rx.SchedulerProvider;

import java.util.List;

import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.CONTENT_URI;

public class FavoriteAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final String TAG = "FavoriteAdapter";

    private Cursor listMovie;
    private Fragment mFragment;
    private DataManager mDataManager;
    private SchedulerProvider mSchedulerProvider;

    public FavoriteAdapter(FavoriteFragment fragment, DataManager dataManager, SchedulerProvider schedulerProvider){
        this.mFragment = fragment;
        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
    }

    public void setListFavorite(Cursor list){
        this.listMovie = list;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFavoriteBinding binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new FavoriteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (listMovie== null) return 0;
        return listMovie.getCount();
    }

    private Result getItem(int position){
        if (!listMovie.moveToPosition(position)) {
            throw new IllegalStateException("Salah Posisi");
        }
        return new Result(listMovie);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class FavoriteViewHolder extends BaseViewHolder implements FavoriteItemViewModel.ItemFavoriteListener{

        private ItemFavoriteBinding mBinding;
        private FavoriteItemViewModel mFavoriteItemViewModel;

        public FavoriteViewHolder(ItemFavoriteBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            Result favorite = getItem(position);
            mFavoriteItemViewModel = new FavoriteItemViewModel(mDataManager,mSchedulerProvider);
            mFavoriteItemViewModel.setDataItem(favorite,this);
            mBinding.setViewModel(mFavoriteItemViewModel);
            mBinding.executePendingBindings();
        }

        @Override
        public void clickMovieDetailActivity(Result result) {
            Intent intent = DetailMovieActivity.gotoDetailMovieActivity(mFragment.getContext(),result);
            Uri uri = Uri.parse(CONTENT_URI + "/" + result.getId());
            intent.setData(uri);
            mFragment.startActivity(intent);
        }

        @Override
        public void shareButton(String judul) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, judul);
            mFragment.startActivity(Intent.createChooser(sharingIntent, "Share via"));
        }
    }
}
