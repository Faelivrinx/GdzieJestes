package com.gdziejestes.core;

import android.app.Application;

/**
 * Created by Dominik on 2017-03-16.
 *
 * Głowna aplikacja
 */

public class MainApplication  extends Application {

    private static MainApplication instance = new MainApplication();

    public static MainApplication getApplication(){
        return instance;
    }
}
