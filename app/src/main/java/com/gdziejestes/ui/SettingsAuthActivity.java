package com.gdziejestes.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gdziejestes.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsAuthActivity extends AppCompatActivity {

    @BindView(R.id.activity_settings_auth_btnCheck)
    Button btnCheckPassword;

    @BindView(R.id.activity_settings_auth_EtPassword)
    EditText passwordEt;

    private String mainUserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_auth);
        ButterKnife.bind(this);

        mainUserPassword = getIntent().getStringExtra("mainUserPassword");

        btnCheckPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passwordEt.getText().toString().equals(mainUserPassword))
                {
                    startActivity (new Intent(SettingsAuthActivity.this, SettingsActivity.class));
                    finish();
                }
                else
                {
                    Toast.makeText(SettingsAuthActivity.this, "Błędne hasło", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
