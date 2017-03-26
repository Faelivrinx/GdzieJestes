package com.gdziejestes.core;

import android.app.Application;

import com.gdziejestes.common.Authorization;
import com.gdziejestes.core.dagger.AppComponent;
import com.gdziejestes.core.dagger.AppModule;
import com.gdziejestes.core.dagger.BusModule;
import com.gdziejestes.core.dagger.DaggerAppComponent;

import com.gdziejestes.core.dagger.PersistanceModule;
import com.squareup.otto.Bus;

/**
 * Created by Dominik on 2017-03-16.
 *
 * Głowna aplikacja
 */

public class MainApplication  extends Application {


    private static MainApplication app = new MainApplication();
    private AppComponent appComponent;
    private Authorization auth;
    private Bus bus;


    public MainApplication() {
        getAppComponent();
        bus = appComponent.getBus();
    }


    @Override
    public void onCreate() {
        super.onCreate();

        auth = appComponent.getAuth();
      //  auth.login();
       //auth.login();
       // auth = new Authorization(this);
    }



    public static MainApplication getApplication(){
        return app;
    }

    public Authorization getAuth() {
        return auth;
    }

    public AppComponent getAppComponent(){
        if(appComponent == null){
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .busModule(new BusModule())
                    .persistanceModule(new PersistanceModule())
                    .build();
        }
        return appComponent;
    }


    public Bus getBus() {
        return bus;
    }
}
