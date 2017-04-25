package com.gdziejestes.data.live.services;

import android.os.Handler;
import android.os.Looper;

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

        // final ITaskFinished taskFinished = (ITaskFinished) request.context;

        String url = "http://damrod.16mb.com/android/gdzie-jestes/database-login.php";

        RequestBody requestBody = new FormBody.Builder()
                .add("username", request.username)
                .add("password", request.password)
                .add("firebase_key", request.firebase_key)
                .build();

        Request request1 = new Request.Builder().url(url).post(requestBody).build();

        okHttpClient.newCall(request1).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //taskFinished.getData("Server error");
            }

            @Override
            public void onResponse(Call call, Response jsonResponse) throws IOException {
                //taskFinished.getData(jsonResponse.body().string());
                response.json = jsonResponse.body().string();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (response.json.equals("")) {
                            response.setPropertyError("Error", "Błędne dane");
                        }
                        bus.post(response);
                    }
                });

            }
        });
    }
    @Subscribe
    public void RefreshRequest(final Accounts.RefreshRequest request) throws InterruptedException {
        final Accounts.RefreshResponse response = new Accounts.RefreshResponse();

        OkHttpClient okHttpClient = new OkHttpClient();

        // final ITaskFinished taskFinished = (ITaskFinished) request.context;

        String url = "http://damrod.16mb.com/android/gdzie-jestes/database-login.php";

        RequestBody requestBody = new FormBody.Builder()
                .add("username", request.username)
                .add("password", request.password)
                .add("firebase_key", request.firebase_key)
                .add("latitude", request.latitude)
                .add("longitude", request.longitude)
                .build();

        Request request1 = new Request.Builder().url(url).post(requestBody).build();

        okHttpClient.newCall(request1).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //taskFinished.getData("Server error");
            }

            @Override
            public void onResponse(Call call, Response jsonResponse) throws IOException {
                //taskFinished.getData(jsonResponse.body().string());
                response.json = jsonResponse.body().string();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (response.json.equals("")) {
                            response.setPropertyError("Error", "Błędne dane");
                        }
                        bus.post(response);
                    }
                });

            }
        });
    }

        @Subscribe
        public void RegisterWithUserName(final Accounts.RegisterRequest request) throws InterruptedException {
            final Accounts.RegisterResponse response = new Accounts.RegisterResponse();

            OkHttpClient okHttpClient = new OkHttpClient();


            String url = "http://damrod.16mb.com/android/gdzie-jestes/database-register.php";

            RequestBody requestBody = new FormBody.Builder().
                    add("username", request.username)
                    .add("password", request.password)
                    .add("email", request.email)
                    .build();

            Request req = new Request.Builder().url(url).post(requestBody).build();

            okHttpClient.newCall(req).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }

                @Override
                public void onResponse(Call call, Response jsonResponse) throws IOException {
                    response.json = jsonResponse.body().string();
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            if(response.json.equals("")){
                                response.setPropertyError("Error", "Brak danych");
                            }
                            bus.post(response);
                        }
                    });

                }
            });


    }

    @Subscribe
    public void sendNotification(final Accounts.SendNotification request){
        OkHttpClient okHttpClient = new OkHttpClient();


        String url = "http://damrod.16mb.com/android/gdzie-jestes/brodcast.php";

        RequestBody requestBody = new FormBody.Builder().
                add("username", request.username)
                .add("message", request.message)
                .build();

        Request req = new Request.Builder().url(url).post(requestBody).build();

        okHttpClient.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response jsonResponse) throws IOException {
/*                response.json = jsonResponse.body().string();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if(response.json.equals("")){
                            response.setPropertyError("Error", "Brak danych");
                        }
                        bus.post(response);
                    }
                });*/

            }
        });
    }

    @Subscribe
    public void InviteFriend(final Accounts.InviteFriend request){
        final Accounts.InviteFriendResponse response = new Accounts.InviteFriendResponse();

        OkHttpClient okHttpClient = new OkHttpClient();


        String url = "http://damrod.16mb.com/android/gdzie-jestes/database-update.php";

        RequestBody requestBody = new FormBody.Builder().
                add("username1", request.ownUsername)
                .add("username2", request.friendUsername)
                .build();

        Request req = new Request.Builder().url(url).post(requestBody).build();

        okHttpClient.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response jsonResponse) throws IOException {
               response.json = jsonResponse.body().string();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        bus.post(response);
                    }
                });

            }
        });
    }

    @Subscribe
    public void UpdateDataRequest(final Accounts.UpdateDataRequest request) throws InterruptedException {
        final Accounts.UpdateDataResponse response = new Accounts.UpdateDataResponse();

        OkHttpClient okHttpClient = new OkHttpClient();


        String url = "http://damrod.16mb.com/android/gdzie-jestes/database-register.php";

        RequestBody requestBody = new FormBody.Builder().
                add("username", "username_test")
                .add("password", "test1234")
                .add("email", "małpa@op.pl")
                .build();

        Request req = new Request.Builder().url(url).post(requestBody).build();

        okHttpClient.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response jsonResponse) throws IOException {
                response.json = jsonResponse.body().string();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if(response.json.equals("")){
                            response.setPropertyError("Error", "Brak danych");
                        }
                        bus.post(response);
                    }
                });

            }
        });
    }


    @Subscribe
    public void DeleteFriendRequest(final Accounts.DeleteFriendRequest request){
        final Accounts.DeleteFriendResponse response = new Accounts.DeleteFriendResponse();

        OkHttpClient okHttpClient = new OkHttpClient();

        String url = "http://damrod.16mb.com/android/gdzie-jestes/database-update.php";

        RequestBody requestBody = new FormBody.Builder().
                add("username1", request.ownUsername)
                .add("username2", request.friendUsername)
                .add("mode", "remove")
                .build();

        Request req = new Request.Builder().url(url).post(requestBody).build();

        okHttpClient.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response jsonResponse) throws IOException {
                response.json = jsonResponse.body().string();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        bus.post(response);
                    }
                });

            }
        });
    }

    private void loginUser(Accounts.ServerResponse response){

        if(response.jsonInformation.equals("")){

        }
    }


}
