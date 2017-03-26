package com.gdziejestes.data.live.services;

import com.gdziejestes.core.MainApplication;
import com.squareup.otto.Bus;

/**
 * Created by Dominik on 26.03.2017.
 */

public class BaseLiveService {

    private final MainApplication application;
    private Bus bus;

    public BaseLiveService(MainApplication application) {
        this.application = application;
        bus = application.getBus();
        bus.register(this);
    }
}
