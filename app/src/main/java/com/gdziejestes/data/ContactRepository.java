package com.gdziejestes.data;

import com.gdziejestes.core.MainApplication;
import com.gdziejestes.model.User;
import com.gdziejestes.ui.mainactivity.MainActivityContract;
import com.gdziejestes.util.JsonFormatter;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominik on 26.03.2017.
 */

public class ContactRepository  implements MainActivityContract.repository{


    @Override
    public List<User> getAllContacts(String json) {
        List<User> users = new ArrayList<>();
        JsonFormatter formatter = new JsonFormatter();

        users = formatter.getUserFriends(json);


        return users;
    }

    @Override
    public String getLocationUpdated(String location) {
        return null;
    }

    @Override
    public User getContact(String userName) {
        return null;
    }
}
