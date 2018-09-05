package com.example.tsmpc47.catalogmovie.ui.home.upcoming;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tsmpc47.catalogmovie.data.model.Result;
import com.example.tsmpc47.catalogmovie.databinding.ItemUpcomingBinding;
import com.example.tsmpc47.catalogmovie.ui.base.BaseViewHolder;
import com.example.tsmpc47.catalogmovie.ui.detail.DetailMovieActivity;

import java.util.List;

public class UpComingAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Result> mUpComingList;

    public UpComingAdapter(List<Result> upComingList){
        this.mUpComingList = upComingList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUpcomingBinding binding = ItemUpcomingBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new UpComingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return mUpComingList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void addItems(List<Result> list){
        mUpComingList.addAll(list);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mUpComingList.clear();
    }

    public class UpComingViewHolder extends BaseViewHolder implements UpComingItemViewModel.ItemUpComingListener{

        private ItemUpcomingBinding mBinding;

        public UpComingViewHolder(ItemUpcomingBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            Result upComing = mUpComingList.get(position);
            UpComingItemViewModel mUpComingItemViewModel = new UpComingItemViewModel(upComing, this);
            mBinding.setViewModel(mUpComingItemViewModel);
            mBinding.executePendingBindings();
        }

        @Override
        public void clickMovieDetailActivity(Result result) {
            Context context = mBinding.getRoot().getContext();
            Intent intent = DetailMovieActivity.gotoDetailMovieActivity(context,result);
            context.startActivity(intent);
        }
    }
}
