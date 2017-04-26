package com.gdziejestes.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gdziejestes.R;
import com.gdziejestes.common.DataValidator;
import com.gdziejestes.model.entities.Accounts;
import com.gdziejestes.ui.mainactivity.MainActivity;
import com.squareup.otto.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends BaseActivity {

    @BindView(R.id.activity_settings_btnSaveChanges)
    Button btnSaveChanges;

    @BindView(R.id.activity_settings_etDisplayName)
    EditText displayNameEt;

    @BindView(R.id.activity_settings_etEmail)
    EditText emailEt;

    @BindView(R.id.activity_settings_EtPasswordRepeat)
    EditText passwordRepeatEt;

    @BindView(R.id.activity_settings_etPassword)
    EditText passwordEt;

    private String mainUserUsername, mainUserPassword, mainUserEmail, mainUserDisplayName;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        mainUserUsername = getIntent().getStringExtra("mainUserUsername");
        mainUserPassword = getIntent().getStringExtra("mainUserPassword");
        mainUserEmail = getIntent().getStringExtra("mainUserEmail");
        mainUserDisplayName = getIntent().getStringExtra("mainUserDisplayName");

        displayNameEt.setText(mainUserDisplayName);
        emailEt.setText(mainUserEmail);

        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
    }

    private void update() {
        String  password, passwordRepeat, email, displayName;
        password = passwordEt.getText().toString();
        passwordRepeat = passwordRepeatEt.getText().toString();
        email = emailEt.getText().toString();
        displayName = displayNameEt.getText().toString();

        DataValidator dataValidator = new DataValidator(password, passwordRepeat, email);

        boolean isCorrect = true;


        if(!dataValidator.checkPassword2())
        {
            Toast.makeText(this, "Błędne hasło", Toast.LENGTH_SHORT).show();
            isCorrect = false;
        }

        if(!dataValidator.checkEmail())
        {
            Toast.makeText(this, "Błędny adres email", Toast.LENGTH_SHORT).show();
            isCorrect = false;
        }
        if (isCorrect)
        {
            bus.post(new Accounts.UpdateDataRequest(mainUserUsername, password, email, displayName));
        }
    }

    @Subscribe
    public void getData(final Accounts.UpdateDataResponse response){

        if(response.didSucceed()){
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(SettingsActivity.this, response.json, Toast.LENGTH_SHORT).show();
                }
            });
            if(response.json.equals("updated succesfully"))
            {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }

        }

    }
}
