package com.example.tsmpc47.catalogmovie.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.tsmpc47.catalogmovie.BuildConfig;
import com.example.tsmpc47.catalogmovie.R;
import com.example.tsmpc47.catalogmovie.data.model.Result;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.CONTENT_URI;

public class FavoriteWidgetAdapter implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    private int mAppWidgetId;
    private Cursor mWidgetItems;
    private static final String TAG = "FavoriteWidgetAdapter";

    public FavoriteWidgetAdapter(Context mContext, Intent intent) {
        this.mContext = mContext;
        this.mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        if (mWidgetItems != null){
            mWidgetItems.close();
        }
        final long identityToken = Binder.clearCallingIdentity();

        mWidgetItems = mContext.getContentResolver().query(CONTENT_URI,null,null,null,null);

        Binder.restoreCallingIdentity(identityToken);

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        Log.i(TAG, "getCount: "+mWidgetItems.getCount());
        return mWidgetItems.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {

        Result item = getItem(position);
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
        Log.i(TAG, "getViewAt: "+BuildConfig.POSTER_WIDGET+item.getPosterPath());
        Bitmap bitmap = null;
        try {
            bitmap = Glide.with(mContext)
                    .asBitmap()
                    .load(BuildConfig.POSTER_WIDGET+item.getPosterPath())
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        rv.setImageViewBitmap(R.id.imageView, bitmap);
        Bundle extras = new Bundle();
        extras.putInt(FavoriteWidget.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);

        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    private Result getItem(int position) {
        if (!mWidgetItems.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid!");
        }

        return new Result(mWidgetItems);
    }
}
