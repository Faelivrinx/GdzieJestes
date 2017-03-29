package com.gdziejestes.ui.loginactivity;

/**
 * Created by Dominik on 2017-03-28.
 */

public interface LoginContract {

    interface Views{
        void showLoginDialog();
        void showErrors();
    }

    interface Actions{
        void login(String email, String password);

        //TODO: Response as a parameter
        void onLoginResponse();
    }
}
