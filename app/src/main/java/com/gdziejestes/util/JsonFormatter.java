package com.gdziejestes.util;

import android.widget.Toast;

import com.gdziejestes.model.User;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Damrod on 30.03.2017.
 */

public class JsonFormatter {

    public User getMainUser (String json)
    {
        if(json !="" || json != null)
        {
            User user;
            String username, password,firebase_key, email, display_name, date, latitude, longitude, friends, avatar;
            JSONObject jsonObject;
            JSONArray jsonArray;
            try {
                jsonObject = new JSONObject(json);
                jsonArray = jsonObject.getJSONArray("server_response");

                    JSONObject jsonUser = jsonArray.getJSONObject(0);
                    username = jsonUser.getString("username");
                    password = jsonUser.getString("password");
                    firebase_key = jsonUser.getString("firebase_key");
                    email = jsonUser.getString("email");
                    display_name = jsonUser.getString("display_name");
                    date = jsonUser.getString("date");
                    latitude = jsonUser.getString("latitude");
                    longitude = jsonUser.getString("longitude");
                    friends = jsonUser.getString("friends");
                    avatar = jsonUser.getString("avatar");

                    user = new User(username, password, firebase_key, email, display_name, date, latitude, longitude, friends, avatar);

                    return user;
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }
        return null;
    }

    public List<User> getUserFriends (String json)
    {
        if(json !="" || json != null)
        {
            List<User> userFriends = new ArrayList<>();

            String username, password,firebase_key, email, display_name, date, latitude, longitude, friends, avatar;
            JSONObject jsonObject;
            JSONArray jsonArray;
            try {
                jsonObject = new JSONObject(json);
                jsonArray = jsonObject.getJSONArray("server_response");
                int count = 1;

                while (count<jsonArray.length()) {
                    JSONObject jsonUser = jsonArray.getJSONObject(count);
                    username = jsonUser.getString("username");
                    firebase_key = jsonUser.getString("firebase_key");
                    email = jsonUser.getString("email");
                    display_name = jsonUser.getString("display_name");
                    date = jsonUser.getString("date");
                    latitude = jsonUser.getString("latitude");
                    longitude = jsonUser.getString("longitude");
                    avatar = jsonUser.getString("avatar");

                    User user = new User(username, firebase_key, email, display_name, date, latitude, longitude,  avatar);
                    userFriends.add(user);
                    count++;
                }

                return userFriends;

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }
        return null;
    }
}
