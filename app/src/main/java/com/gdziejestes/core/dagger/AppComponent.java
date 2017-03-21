package com.gdziejestes.core.dagger;

import com.gdziejestes.common.Authorization;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
            modules = {
                    AuthModule.class,
            }
        )
public interface AppComponent {
    Authorization getAuth();
}
