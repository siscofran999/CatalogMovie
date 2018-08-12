package com.example.tsmpc47.catalogmovie.di.builder;

import com.example.tsmpc47.catalogmovie.ui.detail.DetailMovieActivity;
import com.example.tsmpc47.catalogmovie.ui.detail.DetailMovieActivityModule;
import com.example.tsmpc47.catalogmovie.ui.main.MainActivity;
import com.example.tsmpc47.catalogmovie.ui.main.MainActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = DetailMovieActivityModule.class)
    abstract DetailMovieActivity bindDetailMovieActivity();

}
