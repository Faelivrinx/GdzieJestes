package com.gdziejestes.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.gdziejestes.core.MainApplication;
import com.gdziejestes.model.User;

/**
 * Created by Dominik on 2017-03-20.
 * Autoryzacja sprawdza czy posiadamy authToken. Bez niego nie możemy być zalogowani.
 */

public class Authorization {

    private static final String AUTH_PREFERENCES = "AUTH_PREFERENCES";
    private static final String AUTH_PREFERENCES_TOKEN = "AUTH_PREFERENCES_TOKEN";

    private final Context context;
    private final SharedPreferences preferences;

    private User user;
    private String authToken;

    public Authorization(Context context) {
        this.context = context;
        user = new User();
        this.preferences = context.getSharedPreferences(AUTH_PREFERENCES, Context.MODE_PRIVATE);
        authToken = preferences.getString(AUTH_PREFERENCES_TOKEN, null);
    }

    public boolean hasAuthToken(){
        return authToken != null && !authToken.isEmpty();
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(AUTH_PREFERENCES_TOKEN, authToken);
        editor.commit();

    }

    public User getUser() {
        return user;
    }

    public void login(){
        this.user = new User("Dominik", "jurasz.do@gmaiol.com", "Wow");
        setAuthToken("AUTH");
    }


}
