package com.gdziejestes.ui.mainactivity;

import com.gdziejestes.model.User;

import java.util.List;

/**
 * Created by Dominik on 26.03.2017.
 */

public interface MainActivityContract {

    interface Views {
        void showContacts(List<User> users);
        void updateGoogleMap(User user);
        void showSendRequestToolbarOption();
        void showErrorToast(String error);
    }

    interface Actions {
        void loadContacts();
        void sendRequestToUpdateMap(User user);
        void receiveResponseToUpdateMap(String response);
        User getUserToUpdateMap(User user);
    }

    interface repository {
        List<User> getAllContacts();
        String getLocationUpdated(String location);
        User getContact(String userName);
    }
}
