package com.example.favorite.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.favorite.data.DataManager;
import com.example.favorite.data.model.Result;
import com.example.favorite.databinding.ItemMainBinding;
import com.example.favorite.ui.base.BaseViewHolder;
import com.example.favorite.utils.rx.SchedulerProvider;

import static com.example.favorite.data.db.DatabaseContract.CONTENT_URI;

public class MainAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final String TAG = "FavoriteAdapter";

    private Cursor listMovie;
    private Activity mActivity;
    private DataManager mDataManager;
    private SchedulerProvider mSchedulerProvider;
    private CursorAdapter mCursorAdapter;

    public MainAdapter(MainActivity activity, DataManager dataManager, SchedulerProvider schedulerProvider){
        this.mActivity = activity;
        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
    }

    public void setListMain(Cursor list){
        this.listMovie = list;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemMainBinding binding = ItemMainBinding.inflate(LayoutInflater.from(viewGroup.getContext()),viewGroup,false);
        return new MainViewHolder(binding);
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

    public class MainViewHolder extends BaseViewHolder implements MainItemViewModel.ItemFavoriteListener{

        private ItemMainBinding mBinding;
        private MainItemViewModel mMainItemViewModel;

        public MainViewHolder(ItemMainBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            Result favorite = getItem(position);
            mMainItemViewModel = new MainItemViewModel(mDataManager,mSchedulerProvider);
            mMainItemViewModel.setDataItem(favorite,this);
            mBinding.setViewModel(mMainItemViewModel);
            mBinding.executePendingBindings();
        }

        @Override
        public void shareButton(String judul) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, judul);
            mActivity.startActivity(Intent.createChooser(sharingIntent, "Share via"));
        }
    }
}
