package com.example.tsmpc47.catalogmovie.ui.home.nowplaying;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.example.tsmpc47.catalogmovie.ViewModelProviderFactory;
import com.example.tsmpc47.catalogmovie.data.DataManager;
import com.example.tsmpc47.catalogmovie.data.model.Result;
import com.example.tsmpc47.catalogmovie.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class NowPlayingFragmentModule {

    @Provides
    NowPlayingViewModel nowPlayingViewModel(DataManager dataManager,
                                SchedulerProvider schedulerProvider) {
        return new NowPlayingViewModel(dataManager, schedulerProvider);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(NowPlayingFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }

    @Provides
    NowPlayingAdapter provideNowPlayingAdapter(NowPlayingFragment fragment){
        return new NowPlayingAdapter(new ArrayList<Result>(),fragment.getActivity());
    }

    @Provides
    ViewModelProvider.Factory provideBlogViewModel(NowPlayingViewModel blogViewModel) {
        return new ViewModelProviderFactory<>(blogViewModel);
    }

}
