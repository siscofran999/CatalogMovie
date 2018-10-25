package com.example.favorite.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.example.favorite.ui.main.MainAdapter;

import java.util.ArrayList;

public final class BindingUtils {

//    @BindingAdapter({"adapter"})
//    public static void addMainItems(RecyclerView recyclerView,
//                                    ArrayList<Result> mainItem) {
//        MainAdapter adapter = (MainAdapter) recyclerView.getAdapter();
//        if(adapter != null) {
//            adapter.clearItems();
//            adapter.addItems(mainItem);
//        }
//    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).into(imageView);
    }

    @BindingAdapter({"rating"})
    public static void setRating(RatingBar rating, String value){
        rating.setRating(Float.parseFloat(value));
    }

}
