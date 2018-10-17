package com.example.tsmpc47.catalogmovie.ui.home.favorite;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FavoriteFragmentProvider {

    @ContributesAndroidInjector(modules = FavoriteFragmentModule.class)
    abstract FavoriteFragment provideFavoriteFragment();

}
