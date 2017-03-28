package com.gdziejestes.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.gdziejestes.R;
import com.gdziejestes.model.User;

public class MainActivity extends BaseAuthenticationActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getIntent().getExtras().getParcelable("user") != null ){
            User user = getIntent().getExtras().getParcelable("user");
            Toast.makeText(this, user.getJsonData(), Toast.LENGTH_SHORT).show();
        }



    }
}
