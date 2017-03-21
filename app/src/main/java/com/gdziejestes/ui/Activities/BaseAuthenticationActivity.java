package com.gdziejestes.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gdziejestes.common.Authorization;
import com.gdziejestes.core.MainApplication;

import javax.inject.Inject;

/**
 * Created by Dominik on 2017-03-20.
 */

public class BaseAuthenticationActivity extends BaseActivity {

    private Authorization auth;

    @Inject
    Authorization auth1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = getMyApp().getAuth();

        if(!auth.hasAuthToken()){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

    }
}
