package com.gdziejestes.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gdziejestes.R;

public class MainActivity extends BaseAuthenticationActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}