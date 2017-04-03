package com.gdziejestes.common;

/**
 * Created by Damrod on 03.04.2017.
 */

public class DataValidator {

    String username, password, passwordRepeat, email, firebase_key;

    public DataValidator(String username, String password, String passwordRepeat, String email, String firebase_key) {
        this.username = username;
        this.password = password;
        this.passwordRepeat = passwordRepeat;
        this.email = email;
        this.firebase_key = firebase_key;
    }

    public boolean checkPassword()
    {
        if(password.equals(passwordRepeat) && password.length() >= 6)
            return true;
        return false;
    }

    public boolean checkUsername()
    {
        if(username.length() >= 4 && username.matches("[a-zA-Z0-9]*"))
            return  true;
        return false;
    }

    public boolean checkEmail()
    {
        if(email.contains("@"))
            return  true;
        return false;
    }
}
