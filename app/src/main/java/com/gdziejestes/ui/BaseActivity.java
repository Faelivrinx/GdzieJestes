package com.gdziejestes.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gdziejestes.R;
import com.gdziejestes.core.MainApplication;
import com.squareup.otto.Bus;

import butterknife.ButterKnife;

/**
 * Created by Dominik on 2017-03-20.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected MainApplication application;
    protected Bus bus;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        application = (MainApplication) getApplication();
        bus = application.getBus();
        bus.register(this);
        ButterKnife.setDebug(true);
    }

    public MainApplication getMyApp() {
        return application;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }
}
