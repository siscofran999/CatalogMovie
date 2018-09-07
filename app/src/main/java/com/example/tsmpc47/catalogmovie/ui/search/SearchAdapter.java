package com.example.tsmpc47.catalogmovie.ui.search;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tsmpc47.catalogmovie.data.model.Result;
import com.example.tsmpc47.catalogmovie.databinding.ItemSearchBinding;
import com.example.tsmpc47.catalogmovie.ui.base.BaseViewHolder;
import com.example.tsmpc47.catalogmovie.ui.detail.DetailMovieActivity;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Result> mSearchList;

    public SearchAdapter(List<Result> searchList){
        this.mSearchList = searchList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSearchBinding binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new SearchViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return mSearchList.size();
    }

    public void addItems(List<Result> list){
        mSearchList.addAll(list);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mSearchList.clear();
    }

    public class SearchViewHolder extends BaseViewHolder implements SearchItemViewModel.ItemSearchListener {

        private ItemSearchBinding mBinding;

        public SearchViewHolder(ItemSearchBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            Result search = mSearchList.get(position);
            SearchItemViewModel mSearchItemViewModel = new SearchItemViewModel(search,this);
            mBinding.setViewModel(mSearchItemViewModel);
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
