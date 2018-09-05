package com.example.tsmpc47.catalogmovie.ui.home.upcoming;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.GridLayoutManager;

import com.example.tsmpc47.catalogmovie.ViewModelProviderFactory;
import com.example.tsmpc47.catalogmovie.data.DataManager;
import com.example.tsmpc47.catalogmovie.data.model.Result;
import com.example.tsmpc47.catalogmovie.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class UpComingFragmentModule {

    @Provides
    UpComingViewModel upComingViewModel(DataManager dataManager,
                                            SchedulerProvider schedulerProvider) {
        return new UpComingViewModel(dataManager, schedulerProvider);
    }

    @Provides
    GridLayoutManager provideGridLayoutManager(UpComingFragment fragment) {
        return new GridLayoutManager(fragment.getActivity(),3);
    }

    @Provides
    UpComingAdapter provideNowPlayingAdapter(){
        return new UpComingAdapter(new ArrayList<Result>());
    }

    @Provides
    ViewModelProvider.Factory provideBlogViewModel(UpComingViewModel blogViewModel) {
        return new ViewModelProviderFactory<>(blogViewModel);
    }

}
