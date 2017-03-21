package com.gdziejestes.core;

import android.app.Application;

import com.gdziejestes.common.Authorization;
import com.gdziejestes.core.dagger.AppComponent;
import com.gdziejestes.core.dagger.AppModule;
import com.gdziejestes.core.dagger.DaggerAppComponent;

/**
 * Created by Dominik on 2017-03-16.
 *
 * Głowna aplikacja
 */

public class MainApplication  extends Application {


    private static MainApplication app;
    private AppComponent appComponent;
    private Authorization auth;


    @Override
    public void onCreate() {
        super.onCreate();

        getAppComponent();
        auth = appComponent.getAuthorization();
        //authorization
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
            appComponent =  DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
        return appComponent;
    }
}
