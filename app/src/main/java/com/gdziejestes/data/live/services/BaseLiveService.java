package com.gdziejestes.data.live.services;

import com.gdziejestes.core.MainApplication;
import com.squareup.otto.Bus;

/**
 * Created by Dominik on 26.03.2017.
 */

public class BaseLiveService {

    protected final MainApplication application;
    protected Bus bus;

    public BaseLiveService(MainApplication application) {
        this.application = application;
        bus = application.getBus();
        bus.register(this);
    }
}
