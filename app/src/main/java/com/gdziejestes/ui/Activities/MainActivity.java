package com.gdziejestes.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.gdziejestes.R;

public class MainActivity extends BaseAuthenticationActivity {

    String jsonData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jsonData = getIntent().getExtras().getString("jsonData");
        Toast.makeText(this, jsonData, Toast.LENGTH_SHORT).show();
    }
}
