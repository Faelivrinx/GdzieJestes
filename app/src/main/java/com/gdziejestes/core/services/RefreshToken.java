package com.gdziejestes.core.services;

import android.os.AsyncTask;
import android.util.Log;

import com.gdziejestes.core.MainApplication;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;

/**
 * Created by Dominik on 12.04.2017.
 */

public class RefreshToken extends AsyncTask<Void, String, String>{

    private MainApplication application;

    public RefreshToken(MainApplication application) {
        this.application = application;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String token = null;
        if(application.getAuth().getFirebaseToken().equals("")){
            token = FirebaseInstanceId.getInstance().getToken();
        } else {
            try {
                FirebaseInstanceId.getInstance().deleteInstanceId();
                FirebaseInstanceId.getInstance().getToken();
            } catch (IOException e) {
                e.printStackTrace();
            }
            token = "";
        }

        return token;
    }

    @Override
    protected void onPostExecute(String token) {
        application.getAuth().setFirebaseToken(token);
        //application.getAuth().logout();
        Log.e(application.getClass().getSimpleName(), application.getAuth().getFirebaseToken() + " ");
    }
}
