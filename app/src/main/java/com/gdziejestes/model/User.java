package com.gdziejestes.model;


import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Dominik on 2017-03-16.
 */

public class User {

    //TODO: Stworzyć sensowny model użytkowika


    private String username, password,firebase_key, email, display_name, date, latitude, longitude, friends, avatar;

    public User()
    {
    }

    public User(String username, String password, String firebase_key, String email, String display_name, String date, String latitude, String longitude, String friends, String avatar) {
        this.username = username;
        this.password = password;
        this.firebase_key = firebase_key;
        this.email = email;
        this.display_name = display_name;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.friends = friends;
        this.avatar = avatar;
    }

    public User(String username, String firebase_key, String email, String display_name, String date, String latitude, String longitude, String avatar) {
        this.username = username;
        this.firebase_key = firebase_key;
        this.email = email;
        this.display_name = display_name;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirebase_key() {
        return firebase_key;
    }

    public String getEmail() {
        return email;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public String getDate() {
        return date;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getFriends() {
        return friends;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirebase_key(String firebase_key) {
        this.firebase_key = firebase_key;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LatLng getCoordinate(){
        return new LatLng(Double.parseDouble(this.latitude), Double.parseDouble(this.longitude));
    }
}
