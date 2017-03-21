package com.gdziejestes.model;

/**
 * Created by Dominik on 2017-03-16.
 */

public class User {

    //TODO: Stworzyć sensowny model użytkowika

    private String name;
    private String email;
    private String password;

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
