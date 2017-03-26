package com.gdziejestes.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gdziejestes.R;
import com.gdziejestes.ui.activities.BaseActivity;

/**
 * Created by Dominik on 2017-03-20.
 */

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}
