package com.gdziejestes.model;

import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gdziejestes.async.AsyncLogin;
import com.gdziejestes.interfaces.IAsyncLogin;

/**
 * Created by Dominik on 2017-03-16.
 */

public class User implements Parcelable {

    //TODO: Stworzyć sensowny model użytkowika

    private String name;
    private String email;

    private String username, password, jsonData = "";



    public User() {
    }

    public User(Parcel in)
    {
        username = in.readString();
        password = in.readString();
        jsonData = in.readString();
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }



    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }


    public String getJsonData() {
        return jsonData;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(jsonData);

    }
}
