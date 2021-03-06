package com.example.tsmpc47.catalogmovie.data.remote;

import android.content.Context;
import android.util.Log;

import com.example.tsmpc47.catalogmovie.BuildConfig;
import com.example.tsmpc47.catalogmovie.data.model.MovieResponse;
import com.example.tsmpc47.catalogmovie.data.model.NowPlaying;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class AppApiHelper implements ApiHelper {

    private Context mContext;
    private static final String TAG = "AppApiHelper";

    @Inject
    public AppApiHelper(Context context){
        mContext = context;
    }

    @Override
    public Single<MovieResponse> getMovieData(String query) {
        Log.i(TAG, "getMovieData: "+query);
        return Rx2AndroidNetworking.get(ApiEndPoint.URL+"search/movie?")
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .addQueryParameter("language","en-US")
                .addQueryParameter("query", query)
                .build()
                .getObjectSingle(MovieResponse.class);
    }

    @Override
    public Single<NowPlaying> getNowPlayingData() {
        return Rx2AndroidNetworking.get(ApiEndPoint.URL+"movie/now_playing?")
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .addQueryParameter("language","en-US")
                .build()
                .getObjectSingle(NowPlaying.class);
    }

    @Override
    public Single<NowPlaying> getUpComingData() {
        return Rx2AndroidNetworking.get(ApiEndPoint.URL+"movie/upcoming?")
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .addQueryParameter("language","en-US")
                .build()
                .getObjectSingle(NowPlaying.class);
    }
}
