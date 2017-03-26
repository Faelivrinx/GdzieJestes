package com.gdziejestes.model;


import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Dominik on 2017-03-16.
 */

public class User {

    //TODO: Stworzyć sensowny model użytkowika

    private String userName;
    private String email;
    private String password;
    private LatLng coordinate;

    public LatLng getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(LatLng coordinate) {
        this.coordinate = coordinate;
    }

    private String avatarUrl;

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

}
