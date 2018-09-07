package com.example.tsmpc47.catalogmovie.ui.search;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.example.tsmpc47.catalogmovie.ViewModelProviderFactory;
import com.example.tsmpc47.catalogmovie.data.DataManager;
import com.example.tsmpc47.catalogmovie.data.model.Result;
import com.example.tsmpc47.catalogmovie.ui.detail.DetailMovieViewModel;
import com.example.tsmpc47.catalogmovie.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchActivityModule {

    @Provides
    SearchViewModel provideSearchViewModel(DataManager dataManager,
                                                     SchedulerProvider schedulerProvider){
        return new SearchViewModel(dataManager, schedulerProvider);
    }

    @Provides
    SearchAdapter provideMovieSearchAdapter() {
        return new SearchAdapter(new ArrayList<Result>());
    }

    @Provides
    LinearLayoutManager provideSearchLinearLayoutManager(SearchActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    ViewModelProvider.Factory provideSearchViewModelProviderFactory(SearchViewModel viewModel) {
        return new ViewModelProviderFactory<>(viewModel);
    }
}
