package com.gdziejestes.core.services;

import android.os.AsyncTask;
import android.util.Log;

import com.gdziejestes.core.MainApplication;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;

/**
 * Created by Dominik on 12.04.2017.
 */

public class DeleteToken extends AsyncTask<Void, String, Void>{

    MainApplication app;

    public DeleteToken(MainApplication app) {
        this.app = app;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            FirebaseInstanceId.getInstance().deleteInstanceId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        app.getAuth().setFirebaseToken("");
        Log.e(app.getClass().getSimpleName(), app.getAuth().getFirebaseToken() + " token");
        //app.getAuth().logout();
    }
}
