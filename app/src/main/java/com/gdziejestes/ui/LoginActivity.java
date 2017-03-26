package com.gdziejestes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;

import com.gdziejestes.R;
import com.gdziejestes.ui.activities.BaseActivity;
import com.gdziejestes.ui.mainactivity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dominik on 2017-03-20.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.activity_login_btnLogin)
    AppCompatButton btnLogin;

    @BindView(R.id.activity_login_etEmail)
    EditText etEmail;

    @BindView(R.id.activity_login_EtPassword)
    EditText etPassword;

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
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
