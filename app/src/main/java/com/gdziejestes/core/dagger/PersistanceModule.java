package com.gdziejestes.core.dagger;

import com.gdziejestes.core.MainApplication;
import com.gdziejestes.data.ContactRepository;
import com.gdziejestes.ui.mainactivity.MainActivityContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dominik on 26.03.2017.
 */

@Module
public class PersistanceModule {

    @Provides
    public MainActivityContract.repository providesContactRepository(){
        return new ContactRepository();
    }

}
