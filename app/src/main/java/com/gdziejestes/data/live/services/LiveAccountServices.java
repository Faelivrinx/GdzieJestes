package com.gdziejestes.data.live.services;

import com.gdziejestes.common.Authorization;
import com.gdziejestes.common.ServiceResponse;
import com.gdziejestes.core.MainApplication;
import com.gdziejestes.model.entities.Accounts;
import com.google.common.eventbus.Subscribe;


public class LiveAccountServices extends BaseLiveService {

    private final Authorization auth;

    public LiveAccountServices(MainApplication application) {
        super(application);
        auth = application.getAuth();
    }

    @Subscribe
    public void LoginWithUserName(Accounts.LoginWithUserNameRequest request){
        Accounts.LoginWithUserNameResponse response = new Accounts.LoginWithUserNameResponse();


    }


    private void loginUser(Accounts.ServerResponse response){

        if(response.jsonInformation.equals("")){

        }
    }
}
