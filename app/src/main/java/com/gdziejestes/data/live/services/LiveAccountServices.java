package com.gdziejestes.data.live.services;

import com.gdziejestes.common.Authorization;
import com.gdziejestes.common.ServiceResponse;
import com.gdziejestes.core.MainApplication;
import com.gdziejestes.model.User;
import com.gdziejestes.model.entities.Accounts;
import com.gdziejestes.ui.LoginActivity;
import com.gdziejestes.util.JsonFormatter;
import com.squareup.otto.Subscribe;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LiveAccountServices extends BaseLiveService   {

    private final Authorization auth;

    public LiveAccountServices(MainApplication application) {
        super(application);
        auth = application.getAuth();
    }

    @Subscribe
    public void LoginWithUserName(final Accounts.LoginWithUserNameRequest request) throws InterruptedException {
        final Accounts.LoginWithUserNameResponse response = new Accounts.LoginWithUserNameResponse();

        OkHttpClient okHttpClient = new OkHttpClient();

        final ITaskFinished taskFinished = (ITaskFinished) request.context;

        String url = "http://damrod.16mb.com/android/gdzie-jestes/database-login.php";

        RequestBody requestBody = new FormBody.Builder().add("username", request.UserName).add("password", request.Password).build();
        Request request1 = new Request.Builder().url(url).post(requestBody).build();

        okHttpClient.newCall(request1).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                taskFinished.getData("Server error");
            }

            @Override
            public void onResponse(Call call, Response jsonResponse) throws IOException {
                taskFinished.getData(jsonResponse.body().string());
            }
        });


    }


    private void loginUser(Accounts.ServerResponse response){

        if(response.jsonInformation.equals("")){

        }
    }
}
