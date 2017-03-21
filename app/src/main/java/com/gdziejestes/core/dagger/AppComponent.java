package com.gdziejestes.core.dagger;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
            modules = {
                    AppModule.class,
                    AuthModule.class
            }
        )
public interface AppComponent {

}
