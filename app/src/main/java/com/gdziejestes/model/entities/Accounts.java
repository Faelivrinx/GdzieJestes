package com.gdziejestes.model.entities;

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

        public LoginWithUserNameRequest(String userName, String password) {
            UserName = userName;
            Password = password;
        }
    }

    public static class LoginWithUserNameResponse extends ServiceResponse{
        public String json;
    }


}
