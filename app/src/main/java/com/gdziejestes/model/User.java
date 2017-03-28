package com.gdziejestes.model;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gdziejestes.async.AsyncLogin;
import com.gdziejestes.interfaces.IAsyncLogin;

/**
 * Created by Dominik on 2017-03-16.
 */

public class User implements IAsyncLogin{

    //TODO: Stworzyć sensowny model użytkowika

    private String name;
    private String email;

    private String username, password, jsonData = "";



    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }



    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean SignInToDatabase()
    {
        AsyncLogin asyncLogin = new AsyncLogin ();
        asyncLogin.delegate = this;
        asyncLogin.execute(username,password);
        //if(task done)
        return true;
        //else
        //return false;
    }


    @Override
    public void processFinish(String output) {
        this.jsonData = output;
        Log.v("User", jsonData);
    }

    public String getJsonData() {
        return jsonData;
    }
}
