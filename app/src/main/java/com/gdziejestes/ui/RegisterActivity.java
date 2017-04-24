package com.gdziejestes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.CheckResult;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.gdziejestes.R;
import com.gdziejestes.common.DataValidator;
import com.gdziejestes.core.services.DeleteTokenService;
import com.gdziejestes.model.entities.Accounts;
import com.gdziejestes.ui.mainactivity.MainActivity;
import com.squareup.otto.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.gdziejestes.util.Constants.JSON_EXTRAS;

/**
 * Created by Dominik on 2017-03-20.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.activity_register_btnRegister)
    Button btnRegister;

    @BindView(R.id.activity_register_btnLogin)
    Button btnLogin;

    @BindView(R.id.activity_register_etUsername)
    EditText etUsername;

    @BindView(R.id.activity_register_EtPassword)
    EditText etPassword;

    @BindView(R.id.activity_register_EtPasswordRepeat)
    EditText etPasswordRepeat;

    @BindView(R.id.activity_register_etEmail)
    EditText etEmail;

    @BindView(R.id.activity_register_checkbox)
    CheckBox cbCheckbox;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        Intent intent = new Intent(this, DeleteTokenService.class);
        startService(intent);

    }

    @Override
    public void onClick(View view) {
        if(view == btnRegister)
        {
            register();
        }
        if(view == btnLogin)
        {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private void register() {
        String username, password, passwordRepeat, email, firebase_key;
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();
        passwordRepeat = etPasswordRepeat.getText().toString();
        firebase_key = "no key";
        email = etEmail.getText().toString();

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
        if(!cbCheckbox.isChecked())
        {
            Toast.makeText(this, "Zaakceptuj warunki", Toast.LENGTH_SHORT).show();
            isCorrect = false;
        }
        if (isCorrect)
        {
            bus.post(new Accounts.RegisterRequest(username,password, email));
        }



    }

    @Subscribe
    public void getData(final Accounts.RegisterResponse response){

        if(response.didSucceed()){
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(RegisterActivity.this, response.json, Toast.LENGTH_SHORT).show();
                }
            });
            if(response.json.equals("registration succesfull"))
            {
                //getMyApp().getAuth().setPreferences(response.json);
                Intent intent = new Intent(this, LoginActivity.class);
                //intent.putExtra(JSON_EXTRAS, response.json);
                startActivity(intent);
            }

        }

    }
}
