package com.gdziejestes.core;

import android.app.Application;

import com.gdziejestes.common.Authorization;

/**
 * Created by Dominik on 2017-03-16.
 *
 * Głowna aplikacja
 */

public class MainApplication  extends Application {

    private static MainApplication instance = new MainApplication();

    private Authorization auth;

    @Override
    public void onCreate() {
        super.onCreate();

        auth = new Authorization(this);
       // auth.login();

    }

    public static MainApplication getApplication(){
        return instance;
    }

    public Authorization getAuth() {
        return auth;
    }
}
