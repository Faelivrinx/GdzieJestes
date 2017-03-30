package com.gdziejestes.model.entities;

import android.content.Context;

import com.gdziejestes.common.ServiceResponse;

/**
 * Created by Dominik on 2017-03-29.
 */

public class Accounts {

    private Accounts(){}

    public static class ServerResponse{
        public String jsonInformation;
    }

    public static class LoginWithUserNameRequest{
        public String UserName;
        public String Password;
        public Context context;

        public LoginWithUserNameRequest(String userName, String password, Context context) {
            UserName = userName;
            Password = password;
            this.context = context;
        }
    }

    public static class LoginWithUserNameResponse extends ServiceResponse{
        public String json;
    }


}
