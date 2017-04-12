package com.gdziejestes.core.services;

import android.os.AsyncTask;
import android.util.Log;

import com.gdziejestes.core.MainApplication;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;

/**
 * Created by Dominik on 12.04.2017.
 */

public class CreateToken extends AsyncTask<Void, String, String> {

    private MainApplication app;

    public CreateToken(MainApplication app) {
        this.app = app;
    }

    @Override
    protected String doInBackground(Void... voids) {

        return FirebaseInstanceId.getInstance().getToken();

    }

    @Override
    protected void onPostExecute(String token) {
        app.getAuth().setFirebaseToken(token);
        Log.e(app.getClass().getSimpleName(), app.getAuth().getFirebaseToken());
    }
}
