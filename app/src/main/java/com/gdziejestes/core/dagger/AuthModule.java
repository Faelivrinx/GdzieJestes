package com.gdziejestes.core.dagger;

import com.gdziejestes.common.Authorization;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dominik on 2017-03-21.
 */
@Module
public class AuthModule {

    private final Authorization auth;

    public AuthModule(Authorization auth) {
        this.auth = auth;
    }

    @Provides
    @Singleton
    public Authorization getAuth() {
        return auth;
    }
}
