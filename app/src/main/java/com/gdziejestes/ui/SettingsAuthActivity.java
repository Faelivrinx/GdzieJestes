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

    private String mainUserUsername, mainUserPassword, mainUserEmail, mainUserDisplayName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_auth);
        ButterKnife.bind(this);

        mainUserUsername = getIntent().getStringExtra("mainUserUsername");
        mainUserPassword = getIntent().getStringExtra("mainUserPassword");
        mainUserEmail = getIntent().getStringExtra("mainUserEmail");
        mainUserDisplayName = getIntent().getStringExtra("mainUserDisplayName");

        btnCheckPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passwordEt.getText().toString().equals(mainUserPassword))
                {
                    Intent intent = new Intent(SettingsAuthActivity.this, SettingsActivity.class);
                    intent.putExtra("mainUserUsername", mainUserUsername);
                    intent.putExtra("mainUserPassword", mainUserPassword);
                    intent.putExtra("mainUserEmail", mainUserEmail);
                    intent.putExtra("mainUserDisplayName", mainUserDisplayName);
                    startActivity (intent);
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
