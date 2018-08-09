package com.example.tsmpc47.catalogmovie.data.remote;

import com.example.tsmpc47.catalogmovie.data.model.MovieResponse;

import io.reactivex.Single;

public interface ApiHelper {

    Single<MovieResponse> getMovieData(String query);

}
