package com.example.tsmpc47.catalogmovie.data.remote;

import com.example.tsmpc47.catalogmovie.data.model.MovieResponse;
import com.example.tsmpc47.catalogmovie.data.model.NowPlaying;

import io.reactivex.Single;

public interface ApiHelper {

    Single<MovieResponse> getMovieData(String query);

    Single<NowPlaying> getNowPlayingData();

}
