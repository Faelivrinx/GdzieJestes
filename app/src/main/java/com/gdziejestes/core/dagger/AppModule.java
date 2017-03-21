package com.gdziejestes.core.dagger;

import android.content.Context;

import com.gdziejestes.core.MainApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dominik on 2017-03-21.
 */

@Module
public class AppModule {

    private final MainApplication application;

    public AppModule(MainApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public MainApplication provideApp() {
        return application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }
}
