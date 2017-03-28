package com.gdziejestes.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gdziejestes.R;
import com.gdziejestes.core.MainApplication;

import butterknife.ButterKnife;

/**
 * Created by Dominik on 2017-03-20.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private MainApplication application;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        application = (MainApplication) getApplication();
        ButterKnife.setDebug(true);
    }

    public MainApplication getMyApp() {
        return application;
    }
}
