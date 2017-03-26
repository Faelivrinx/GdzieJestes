package com.gdziejestes.ui.mainactivity;

import android.widget.Toast;

import com.gdziejestes.core.MainApplication;

import com.gdziejestes.model.User;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Dominik on 26.03.2017.
 */

public class MainActivityPresenter implements MainActivityContract.Actions {

    private MainActivityContract.Views view;

    @Inject
    Bus bus;

    @Inject
    MainActivityContract.repository repository;


    public MainActivityPresenter(MainActivityContract.Views view) {
        this.view = view;
        MainApplication.getApplication().getAppComponent().inject(this);
        bus.register(this);

    }

    @Override
    public void loadContacts() {
        List<User> users = repository.getAllContacts();
        if(users != null && !users.isEmpty()){
            view.showContacts(users);
        } else{
            view.showErrorToast("Cos nie dziala");
        }
    }

    @Override
    public User getUserToUpdateMap(User userName) {
        return null;
    }

}
