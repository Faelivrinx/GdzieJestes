package com.gdziejestes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gdziejestes.common.Authorization;

/**
 * Created by Dominik on 2017-03-20.
 */

public abstract class BaseAuthenticationActivity extends BaseActivity {

    private Authorization auth;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = getMyApp().getAuth();

        if(!auth.hasAuthToken()){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        onSocialCreate(savedInstanceState);
    }

    protected abstract void onSocialCreate(Bundle savedInstanceState);
}
