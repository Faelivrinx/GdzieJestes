package com.gdziejestes.core.dagger;

import android.content.Context;

import com.gdziejestes.common.Authorization;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dominik on 2017-03-21.
 */
@Module(includes = AppModule.class)
public class AuthModule {

    @Provides
    @Singleton
    public Authorization getAuth(Context context) {
        return new Authorization(context);
    }
}
