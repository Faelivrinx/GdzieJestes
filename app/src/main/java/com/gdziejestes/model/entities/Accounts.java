package com.gdziejestes.model.entities;

import android.content.Context;

import com.gdziejestes.common.ServiceResponse;
import com.gdziejestes.ui.RegisterActivity;

/**
 * Created by Dominik on 2017-03-29.
 */

public class Accounts {

    private Accounts() {
    }

    public static class ServerResponse {
        public String jsonInformation;
    }

    public static class LoginWithUserNameRequest {
        public String username, password, email, firebase_key;
        public Context context;

        public LoginWithUserNameRequest(String username, String password, String firebase_key) {
            this.username = username;
            this.password = password;
            this.firebase_key = firebase_key;
        }

    }
    public static class LoginWithUserNameResponse extends ServiceResponse {
        public String json;


    }

    public static class RegisterRequest {

        public String username, password, email;

        public RegisterRequest(String username, String password, String email) {
            this.username = username;
            this.password = password;
            this.email = email;

        }
    }
    public static class RegisterResponse extends ServiceResponse {
        public String json;
    }

    public static class InviteFriend {
        public String ownUsername, friendUsername;

        public InviteFriend(String ownUsername, String friendUsername) {
            this.ownUsername = ownUsername;
            this.friendUsername = friendUsername;
        }
    }
    public static class InviteFriendResponse extends ServiceResponse {
        public String json;
    }

    public static class SendNotification {
        public String username;
        public String message;

        public SendNotification(String username, String message) {
            this.username = username;
            this.message = message;
        }
    }

    public static class RefreshRequest {
        public String username, password, email, firebase_key;
        public String latitude, longitude;

        public RefreshRequest(String username, String password, String firebase_key, String latitude, String longitude) {
            this.username = username;
            this.password = password;
            this.firebase_key = firebase_key;
            this.latitude = latitude;
            this.longitude = longitude;
        }

    }
    public static class RefreshResponse extends ServiceResponse {
        public String json;
    }

    public static class UpdateDataRequest {

        public String username, password, email, displayName;

        public UpdateDataRequest(String username, String password, String email, String displayName) {
            this.username = username;
            this.password = password;
            this.email = email;
            this.displayName = displayName;
        }
    }
    public static class UpdateDataResponse extends ServiceResponse {
        public String json;
    }

    public static class DeleteFriendRequest {
        public String ownUsername, friendUsername;
        public DeleteFriendRequest(String ownUsername, String friendUsername) {
            this.ownUsername = ownUsername;
            this.friendUsername = friendUsername;
        }
    }
    public static class DeleteFriendResponse extends ServiceResponse {
        public String json;
    }


}