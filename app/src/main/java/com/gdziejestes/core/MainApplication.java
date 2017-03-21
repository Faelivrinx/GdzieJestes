package com.gdziejestes.core;

import android.app.Application;

import com.gdziejestes.common.Authorization;
import com.gdziejestes.core.dagger.AppComponent;


/**
 * Created by Dominik on 2017-03-16.
 *
 * Głowna aplikacja
 */

public class MainApplication  extends Application {

    private AppComponent appComponent;

    private static MainApplication instance = new MainApplication();
    private Authorization auth;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public static MainApplication getApplication(){
        return instance;
    }

    public Authorization getAuth() {
        return auth;
    }

    public AppComponent getAppComponent(){
        if(appComponent == null){

        }
        return null;
    }
}
