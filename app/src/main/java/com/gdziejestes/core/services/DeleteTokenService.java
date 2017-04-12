package com.gdziejestes.core.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.gdziejestes.core.MainApplication;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;

import static com.gdziejestes.common.Authorization.AUTH_PREFERENCS_FIREBASE_TOKEN;

/**
 * Created by Dominik on 07.04.2017.
 */

public class DeleteTokenService extends IntentService {

    public static final String TAG = DeleteTokenService.class.getSimpleName();

    private MainApplication app;

    public DeleteTokenService() {
        super(TAG);
        app = MainApplication.getApplication();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        try {

            String originalToken = getTokenFromPrefs();
            Log.d(TAG, "Token before deletion: " + originalToken);

            FirebaseInstanceId.getInstance().deleteInstanceId();

            String tokenCheck = getTokenFromPrefs();
            Log.d(TAG, "Token deleted. Proof: " + tokenCheck);

            Log.d(TAG, "Getting new token");

            String token =FirebaseInstanceId.getInstance().getToken();
            saveTokenToPrefs(token);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveTokenToPrefs(String _token) {
        // Access Shared Preferences

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        // Save to SharedPreferences
        editor.putString(AUTH_PREFERENCS_FIREBASE_TOKEN, _token);
        editor.apply();
    }


    public String getTokenFromPrefs() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getString(AUTH_PREFERENCS_FIREBASE_TOKEN, null);
    }
}
