package com.example.tsmpc47.catalogmovie.di.builder;

import com.example.tsmpc47.catalogmovie.ui.detail.DetailMovieActivity;
import com.example.tsmpc47.catalogmovie.ui.detail.DetailMovieActivityModule;
import com.example.tsmpc47.catalogmovie.ui.home.HomeActivity;
import com.example.tsmpc47.catalogmovie.ui.home.HomeActivityModule;
import com.example.tsmpc47.catalogmovie.ui.home.favorite.FavoriteFragmentProvider;
import com.example.tsmpc47.catalogmovie.ui.home.nowplaying.NowPlayingFragmentProvider;
import com.example.tsmpc47.catalogmovie.ui.home.upcoming.UpComingFragmentProvider;
import com.example.tsmpc47.catalogmovie.ui.main.MainActivity;
import com.example.tsmpc47.catalogmovie.ui.main.MainActivityModule;
import com.example.tsmpc47.catalogmovie.ui.search.SearchActivity;
import com.example.tsmpc47.catalogmovie.ui.search.SearchActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = DetailMovieActivityModule.class)
    abstract DetailMovieActivity bindDetailMovieActivity();

    @ContributesAndroidInjector(modules = {HomeActivityModule.class, NowPlayingFragmentProvider.class, UpComingFragmentProvider.class, FavoriteFragmentProvider.class})
    abstract HomeActivity bindHomeActivity();

    @ContributesAndroidInjector(modules = SearchActivityModule.class)
    abstract SearchActivity bindSearchActivity();

}
