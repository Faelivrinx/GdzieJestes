package com.gdziejestes.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gdziejestes.R;
import com.gdziejestes.async.AsyncLogin;
import com.gdziejestes.common.Authorization;
import com.gdziejestes.interfaces.IAsyncLogin;
import com.gdziejestes.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dominik on 2017-03-20.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, IAsyncLogin{

    @BindView(R.id.activity_login_btnLogin)
    AppCompatButton btnLogin;

    @BindView(R.id.activity_login_EtUsername)
    EditText etUsername;

    @BindView(R.id.activity_login_EtPassword)
    EditText etPassword;

    User user;
    String username, password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == btnLogin){

            username = etUsername.getText().toString();
            password = etPassword.getText().toString();

            AsyncLogin asyncLogin = new AsyncLogin ();
            asyncLogin.delegate = this;
            asyncLogin.execute(username,password);

        }
    }

    @Override
    public void processFinish(String output) {
        if(!output.isEmpty())
        {
            user =  new User(username, password);
            user.setJsonData(output);

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("user",user);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Nie udało się zalogować", Toast.LENGTH_SHORT).show();
        }

    }
}
