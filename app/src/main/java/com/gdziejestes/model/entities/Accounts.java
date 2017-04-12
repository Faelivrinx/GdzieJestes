package com.gdziejestes.model.entities;

import android.content.Context;

import com.gdziejestes.common.ServiceResponse;
import com.gdziejestes.ui.RegisterActivity;

/**
 * Created by Dominik on 2017-03-29.
 */

public class Accounts {

    private Accounts(){}

    public static class ServerResponse{
        public String jsonInformation;
    }

    public static class LoginWithUserNameRequest{
        public String username, password, email, firebase_key;
        public Context context;

        public LoginWithUserNameRequest(String username, String password, String firebase_key) {
            this.username = username;
            this.password = password;
            this.firebase_key = firebase_key;
        }

    }

    public static class RegisterRequest{

        public String username, password, email;

        public RegisterRequest(String username, String password, String email) {
            this.username = username;
            this.password = password;
            this.email = email;

        }
    }

    public static class RegisterResponse extends ServiceResponse{
        public String json;
    }

    public static class LoginWithUserNameResponse extends ServiceResponse{
        public String json;
    }


}
