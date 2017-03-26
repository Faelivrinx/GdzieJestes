package com.gdziejestes.core.dagger;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dominik on 26.03.2017.
 */

@Module
public class BusModule {


    @Provides
    @Singleton
    public Bus getBus(){
        return new Bus();
    }

}
