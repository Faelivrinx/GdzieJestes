package com.gdziejestes.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gdziejestes.R;
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
    Button btnLogin;

    @BindView(R.id.activity_login_etUsername)
    EditText etUsername;

    @BindView(R.id.activity_login_EtPassword)
    EditText etPassword;

    @BindView(R.id.activity_login_btnRegister)
    Button btnRegister;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == btnLogin){
            login();
            //startActivity(new Intent(this, MainActivity.class));
            //startActivityForResult(new Intent(this, AuthorizationActivity.class), LOGIN_REQUEST);
        }
        if(view == btnRegister)
        {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }
    }

    private void openRegisterActivity() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    private void login() {
        if(!validate()){
            onLoginFailed();
            return;

        }

        btnLogin.setEnabled(false);
        etUsername.setEnabled(false);
        etPassword.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, android.support.design.R.style.Base_Theme_AppCompat_Light_Dialog_Alert);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authentication...");
        progressDialog.show();

        String email = etUsername.getText().toString();
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
        etUsername.setEnabled(true);
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

            getMyApp().getAuth().setPreferences(response.json);
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(JSON_EXTRAS, response.json);
            startActivity(intent);
        } else {
            Toast.makeText(this, response.getPropertyError("Error"), Toast.LENGTH_SHORT).show();
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
