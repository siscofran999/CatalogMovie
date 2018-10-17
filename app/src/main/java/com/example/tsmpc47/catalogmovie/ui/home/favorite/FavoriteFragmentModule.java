package com.example.tsmpc47.catalogmovie.ui.home.favorite;

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
public class FavoriteFragmentModule {

    @Provides
    FavoriteViewModel favoriteViewModel(DataManager dataManager,
                                            SchedulerProvider schedulerProvider) {
        return new FavoriteViewModel(dataManager, schedulerProvider);
    }

    @Provides
    FavoriteAdapter provideFavoriteAdapter(){
        return new FavoriteAdapter(new ArrayList<Result>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(FavoriteFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }

    @Provides
    ViewModelProvider.Factory provideBlogViewModel(FavoriteViewModel blogViewModel) {
        return new ViewModelProviderFactory<>(blogViewModel);
    }


}
