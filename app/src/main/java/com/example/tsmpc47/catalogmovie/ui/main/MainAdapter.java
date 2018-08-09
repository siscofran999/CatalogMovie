package com.example.tsmpc47.catalogmovie.ui.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tsmpc47.catalogmovie.data.model.Result;
import com.example.tsmpc47.catalogmovie.databinding.ItemMainBinding;
import com.example.tsmpc47.catalogmovie.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final String TAG = "MainAdapter";
    private List<Result> mMainResponseList;

    public MainAdapter(List<Result> results){
        this.mMainResponseList = results;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMainBinding binding = ItemMainBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new MainViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount: "+mMainResponseList.size());
        return mMainResponseList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void clearItems() {
        mMainResponseList.clear();
    }

    public void addItems(List<Result> repoList) {
        Log.i(TAG, "addItems: "+repoList);
        mMainResponseList.addAll(repoList);
        notifyDataSetChanged();
    }

    public class MainViewHolder extends BaseViewHolder implements MainItemViewModel.ItemMainViewListener{

        private ItemMainBinding mBinding;

        public MainViewHolder(ItemMainBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            Result result = mMainResponseList.get(position);
            MainItemViewModel mMainItemViewModel = new MainItemViewModel(result, this);
            mBinding.setViewModel(mMainItemViewModel);

            mBinding.executePendingBindings();
        }

        @Override
        public void clickMovieDetailActivity(Result result) {
            Log.i(TAG, "clickMovieDetailActivity: ");
        }
    }
}
