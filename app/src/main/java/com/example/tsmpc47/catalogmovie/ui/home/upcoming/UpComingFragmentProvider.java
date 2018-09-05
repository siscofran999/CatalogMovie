package com.example.tsmpc47.catalogmovie.ui.home.upcoming;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class UpComingFragmentProvider {

    @ContributesAndroidInjector(modules = UpComingFragmentModule.class)
    abstract UpComingFragment provideUpComingFragment();

}
