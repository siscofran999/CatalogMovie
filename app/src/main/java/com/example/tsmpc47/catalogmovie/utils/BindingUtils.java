package com.example.tsmpc47.catalogmovie.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.example.tsmpc47.catalogmovie.data.model.Result;
import com.example.tsmpc47.catalogmovie.ui.home.nowplaying.NowPlayingAdapter;
import com.example.tsmpc47.catalogmovie.ui.home.upcoming.UpComingAdapter;
import com.example.tsmpc47.catalogmovie.ui.main.MainAdapter;
import com.example.tsmpc47.catalogmovie.ui.main.MainItemViewModel;

import java.util.ArrayList;

public final class BindingUtils {

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).into(imageView);
    }

    @BindingAdapter({"adapter"})
    public static void addMainItems(RecyclerView recyclerView,
                                          ArrayList<Result> mainItem) {
        MainAdapter adapter = (MainAdapter) recyclerView.getAdapter();
        if(adapter != null) {
            adapter.clearItems();
            adapter.addItems(mainItem);
        }
    }

    @BindingAdapter({"adapterNow"})
    public static void addNowPlayingItems(RecyclerView recyclerView,
                                          ArrayList<Result> mainItem) {
        NowPlayingAdapter adapter = (NowPlayingAdapter) recyclerView.getAdapter();
        if(adapter != null) {
            adapter.clearItems();
            adapter.addItems(mainItem);
        }
    }

    @BindingAdapter({"adapterUp"})
    public static void addUpComingItems(RecyclerView recyclerView,
                                          ArrayList<Result> mainItem) {
        UpComingAdapter adapter = (UpComingAdapter) recyclerView.getAdapter();
        if(adapter != null) {
            adapter.clearItems();
            adapter.addItems(mainItem);
        }
    }

    @BindingAdapter({"rating"})
    public static void setRating(RatingBar rating, String value){
        rating.setRating(Float.parseFloat(value));
    }

}
