package com.example.tsmpc47.catalogmovie.ui.home.nowplaying;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tsmpc47.catalogmovie.data.model.Result;
import com.example.tsmpc47.catalogmovie.databinding.ItemNowPlayingBinding;
import com.example.tsmpc47.catalogmovie.ui.base.BaseViewHolder;
import com.example.tsmpc47.catalogmovie.ui.detail.DetailMovieActivity;

import java.util.List;

import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.CONTENT_URI;

public class NowPlayingAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final String TAG = "NowPlayingAdapter";
    private List<Result> mNowPlayingList;
    private final Activity mActivity;

    public NowPlayingAdapter(List<Result> nowPlayingList, Activity activity){
        this.mNowPlayingList = nowPlayingList;
        this.mActivity = activity;
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

    public void clearItems() {
        mNowPlayingList.clear();
    }

    public class NowPlayingViewHolder extends BaseViewHolder implements NowPlayingItemViewModel.ItemNowPlayingListener {

        private ItemNowPlayingBinding mBinding;

        public NowPlayingViewHolder(ItemNowPlayingBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            Result nowPlaying = mNowPlayingList.get(position);
            NowPlayingItemViewModel mNowPlayingItemViewModel = new NowPlayingItemViewModel(nowPlaying,this);
            mBinding.setViewModel(mNowPlayingItemViewModel);
            mBinding.executePendingBindings();
        }

        @Override
        public void clickMovieDetailActivity(Result result) {
            Log.i(TAG, "clickMovieDetailActivity: "+result.getPopularity());
            Context context = mBinding.getRoot().getContext();
            Intent intent = DetailMovieActivity.gotoDetailMovieActivity(context,result);
            Uri uri = Uri.parse(CONTENT_URI + "/" + result.getId());
            intent.setData(uri);
            mActivity.startActivityForResult(intent,DetailMovieActivity.REQUEST_UPDATE);
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
