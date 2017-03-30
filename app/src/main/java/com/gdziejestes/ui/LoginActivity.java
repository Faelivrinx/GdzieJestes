package com.gdziejestes.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gdziejestes.R;
import com.gdziejestes.data.live.services.ITaskFinished;
import com.gdziejestes.model.entities.Accounts;
import com.gdziejestes.ui.mainactivity.MainActivity;
import com.squareup.otto.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.gdziejestes.util.Constants.JSON_EXTRAS;

/**
 * Created by Dominik on 2017-03-20.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    public static final int LOGIN_REQUEST = 1;

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
            login();
            //startActivity(new Intent(this, MainActivity.class));
            //startActivityForResult(new Intent(this, AuthorizationActivity.class), LOGIN_REQUEST);
        }
    }

    private void login() {
        if(!validate()){
            onLoginFailed();
            return;

        }

        btnLogin.setEnabled(false);
        etEmail.setEnabled(false);
        etPassword.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, android.support.design.R.style.Base_Theme_AppCompat_Light_Dialog_Alert);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authentication...");
        progressDialog.show();

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();


        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        onLoginSucces();
                        progressDialog.dismiss();
                    }
                }, 3000);

        bus.post(new Accounts.LoginWithUserNameRequest(email,password, this));

    }

    private void onLoginSucces() {
        btnLogin.setEnabled(true);
        etEmail.setEnabled(true);
        etPassword.setEnabled(true);

    }

    private void onLoginFailed() {

    }

    public boolean validate(){
        return true;
    }


    @Subscribe
    public void getData(Accounts.LoginWithUserNameResponse response){

        if(response.didSucceed()){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(JSON_EXTRAS, response.json);
            startActivity(intent);
        }

    }
/*

    @Override
    public void getData(String data) {

        if(!data.equals(""))
        {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("json", data);
            startActivity(intent);
        }
       else if(data.equals("Server error"))
        {
            showMessage("Błąd serwera");
            //Toast.makeText(LoginActivity.this, "Błąd serwera", Toast.LENGTH_SHORT).show();
        }
        else
        {

            showMessage("Błędne dane logowania");

        }
    }

    private void showMessage(final String message){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
*/

}
