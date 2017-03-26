package com.gdziejestes.core.dagger;

import com.gdziejestes.common.Authorization;
import com.gdziejestes.ui.mainactivity.MainActivity;
import com.gdziejestes.ui.mainactivity.MainActivityPresenter;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
            modules = {
                    AuthModule.class,
                    BusModule.class,
                    PersistanceModule.class
            }
        )
public interface AppComponent {
    Authorization getAuth();
    Bus getBus();
    void inject(MainActivityPresenter presenter);

}
