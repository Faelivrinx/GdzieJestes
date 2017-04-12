package com.gdziejestes.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;

import com.gdziejestes.model.User;

/**
 * Created by Dominik on 2017-03-20.
 * Autoryzacja sprawdza czy posiadamy authToken. Bez niego nie możemy być zalogowani.
 */

public class Authorization {

    public static final String AUTH_PREFERENCES = "AUTH_PREFERENCES";

    public static final String AUTH_PREFERENCS_JSON_INFORMATION = "AUTH_PREFERENCS_JSON_INFORMATION";

    public static final String AUTH_PREFERENCS_FIREBASE_TOKEN = "AUTH_PREFERENCS_FIREBASE_TOKEN";

    private final Context context;
    private final SharedPreferences preferences;

    private User user;
    private String jsonInformation;
    private String firebaseToken;

    public Authorization(Context context) {
        this.context = context;
        user = new User();
        this.preferences = context.getSharedPreferences(AUTH_PREFERENCES, Context.MODE_PRIVATE);
        jsonInformation = preferences.getString(AUTH_PREFERENCS_JSON_INFORMATION, null);
        firebaseToken = preferences.getString(AUTH_PREFERENCS_FIREBASE_TOKEN, null);
    }

    public boolean hasAuthToken(){
        String json = preferences.getString(AUTH_PREFERENCS_JSON_INFORMATION, null);

        return json != null && !json.isEmpty();
    }

    public void setPreferences(String jsonInformation) {

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(AUTH_PREFERENCS_JSON_INFORMATION, jsonInformation);
        editor.commit();

    }

    public User getUser() {
        return user;
    }

    public void login(){
        setPreferences("AUTH");
    }

    public SharedPreferences getPreferences(){
        return preferences;
    }


    public void logout() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();

    }

    public void setFirebaseToken(String firebaseToken){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(AUTH_PREFERENCS_FIREBASE_TOKEN, firebaseToken);
        editor.commit();
    }

    public String getFirebaseToken(){
        String token =preferences.getString(AUTH_PREFERENCS_FIREBASE_TOKEN, null);
        if(token != null && !token.isEmpty()){
            return token;
        } else{
            return "";
        }
    }
}
