package com.example.tsmpc47.catalogmovie.ui.home.favorite;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tsmpc47.catalogmovie.data.model.Result;
import com.example.tsmpc47.catalogmovie.databinding.ItemFavoriteBinding;
import com.example.tsmpc47.catalogmovie.ui.base.BaseViewHolder;
import com.example.tsmpc47.catalogmovie.ui.detail.DetailMovieActivity;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final String TAG = "FavoriteAdapter";
    private List<Result> mFavoriteList;

    public FavoriteAdapter(List<Result> favoriteList){
        this.mFavoriteList = favoriteList;
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
        return mFavoriteList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void addItems(List<Result> list){
        mFavoriteList.addAll(list);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mFavoriteList.clear();
    }

    public class FavoriteViewHolder extends BaseViewHolder implements FavoriteItemViewModel.ItemFavoriteListener{

        private ItemFavoriteBinding mBinding;

        public FavoriteViewHolder(ItemFavoriteBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            Result favorite = mFavoriteList.get(position);
            FavoriteItemViewModel mFavoriteItemViewModel = new FavoriteItemViewModel(favorite,this);
            mBinding.setViewModel(mFavoriteItemViewModel);
            mBinding.executePendingBindings();
        }

        @Override
        public void clickMovieDetailActivity(Result result) {
            Context context = mBinding.getRoot().getContext();
            Intent intent = DetailMovieActivity.gotoDetailMovieActivity(context,result);
            context.startActivity(intent);
        }

        @Override
        public void shareButton(String judul) {
            Context context = mBinding.getRoot().getContext();
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, judul);
            context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
        }
    }
}
