package com.example.tsmpc47.catalogmovie.ui.home.nowplaying;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tsmpc47.catalogmovie.data.model.NowPlaying;
import com.example.tsmpc47.catalogmovie.data.model.Result;
import com.example.tsmpc47.catalogmovie.databinding.ItemNowPlayingBinding;
import com.example.tsmpc47.catalogmovie.ui.base.BaseViewHolder;

import java.util.List;

public class NowPlayingAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final String TAG = "NowPlayingAdapter";
    private List<Result> mNowPlayingList;

    public NowPlayingAdapter(List<Result> nowPlayingList){
        this.mNowPlayingList = nowPlayingList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNowPlayingBinding binding = ItemNowPlayingBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new NowPlayingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return mNowPlayingList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void addItems(List<Result> list){
        mNowPlayingList.addAll(list);
        notifyDataSetChanged();
    }

    public class NowPlayingViewHolder extends BaseViewHolder{

        private ItemNowPlayingBinding mBinding;

        public NowPlayingViewHolder(ItemNowPlayingBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            Result nowPlaying = mNowPlayingList.get(position);
            NowPlayingItemViewModel mNowPlayingItemViewModel = new NowPlayingItemViewModel(nowPlaying);
            mBinding.setViewModel(mNowPlayingItemViewModel);
            mBinding.executePendingBindings();
        }
    }
}