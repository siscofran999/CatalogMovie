package com.example.tsmpc47.catalogmovie.di.component;

import android.app.Application;

import com.example.tsmpc47.catalogmovie.CatalogMovie;
import com.example.tsmpc47.catalogmovie.di.builder.ActivityBuilder;
import com.example.tsmpc47.catalogmovie.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();

    }

    void inject(CatalogMovie app);
}
