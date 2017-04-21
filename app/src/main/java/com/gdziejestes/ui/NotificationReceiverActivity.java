package com.gdziejestes.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.gdziejestes.R;
import com.gdziejestes.core.MainApplication;
import com.gdziejestes.model.User;
import com.gdziejestes.model.entities.Accounts;
import com.gdziejestes.ui.mainactivity.MainActivity;
import com.gdziejestes.util.JsonFormatter;
import com.squareup.otto.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.gdziejestes.common.Authorization.AUTH_PREFERENCS_JSON_INFORMATION;

/**
 * Created by Dominik on 2017-04-20.
 */

public class NotificationReceiverActivity extends BaseAuthenticationActivity {

    private static final String TAG = NotificationReceiverActivity.class.getSimpleName();
    private String mainUsername;
    private String friendUsername = null;

    private boolean send = false;


    private String friendFromMessage(String message) {
        String friend = null;
        String word;
        int i = 0;
        if (message != null) {
            friend = message;
            word = friend.substring(i, i + 1);
            while (!word.equals(" ")) {
                i++;
            }
        }
        return friend.substring(0, i);
    }

    private List<String> mainUsernameFromMessage(String message) {
        String username[] = message.split(" ");
        List<String> list = new ArrayList<String>();
        list.add(username[0]);
        list.add(username[5]);
        return list;
    }

/*    @Override
    public void onReceive(Context context, Intent intent) {
        formatter = new JsonFormatter();
        app = MainApplication.getApplication();
        //mainUser= app.getAuth().getUser();
        friendUsername = friendFromMessage(intent.getExtras().getString("message"));

        Log.e(TAG, intent.getExtras().getString("message") + " " + intent.getExtras().getString("yes"));
    }*/


   /* @Override
    public void onReceive(Context context, Intent intent) {
        List<String> list = new ArrayList<String>();
        list = mainUsernameFromMessage(intent.getExtras().getString("message"));
        mainUsername = list.get(0);
        friendUsername = list.get(1);

        OkHttpClient client = new OkHttpClient();


       String url = "http:/damrod.16mb.com/android/gdzie-jestes/database-update.php";

        RequestBody requestBody = new FormBody.Builder().
                add("username1", mainUsername)
                .add("username2", friendUsername)
                .build();

        Request request = new Request.Builder()
                    .url(url).post(requestBody)
                    .build();

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

    @Override
    protected void onSocialCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_notification_receiver);

        List<String> list = new ArrayList<String>();
        list = mainUsernameFromMessage(getIntent().getExtras().getString("message"));
        mainUsername = list.get(0);
        friendUsername = list.get(1);


        if(send == false){
            bus.post(new Accounts.InviteFriend(mainUsername,friendUsername));
            send = true;
        }


    }

    @Subscribe
    public void onDataResponse(Accounts.InviteFriendResponse response){
        Toast.makeText(this, response.json, Toast.LENGTH_SHORT).show();
        finish();
        //startActivity(new Intent(this, MainActivity.class));
}
}
