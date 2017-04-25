package com.gdziejestes.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
    }

    private void update() {
        String username, password, passwordRepeat, email, firebase_key;
        username = "";
        password = "";
        passwordRepeat = "";
        firebase_key = "no key";
        email = "";

        DataValidator dataValidator = new DataValidator(username, password, passwordRepeat, email, firebase_key);

        boolean isCorrect = true;
        if(!dataValidator.checkUsername())
        {
            Toast.makeText(this, "Błędna nazwa użytkownika", Toast.LENGTH_SHORT).show();
            isCorrect = false;
        }
        if(!dataValidator.checkPassword())
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
            bus.post(new Accounts.UpdateDataRequest("asd","asdasdasd", "ASdasdasd", "ASDASDA"));
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
