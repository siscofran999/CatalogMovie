package com.example.tsmpc47.catalogmovie.ui.home.nowplaying;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class NowPlayingFragmentProvider {

    @ContributesAndroidInjector(modules = NowPlayingFragmentModule.class)
    abstract NowPlayingFragment provideNowPlayingFragment();

}
