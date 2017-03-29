package com.gdziejestes.data;

import com.gdziejestes.core.MainApplication;
import com.gdziejestes.model.User;
import com.gdziejestes.ui.mainactivity.MainActivityContract;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominik on 26.03.2017.
 */

public class ContactRepository  implements MainActivityContract.repository{


    @Override
    public List<User> getAllContacts() {
        List<User> users = new ArrayList<>();

        for(int i = 0; i<10; i++){
            User user = new User();
            user.setUserName("User "+i);
            user.setEmail(null);
            user.setPassword(null);
            user.setAvatarUrl(null);
            user.setCoordinate(new LatLng(40.0 + i, 40.0 + i));
            users.add(user);
        }

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
