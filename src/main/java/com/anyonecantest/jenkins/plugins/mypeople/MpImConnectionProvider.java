package com.anyonecantest.jenkins.plugins.mypeople;

import hudson.plugins.im.IMConnection;

import hudson.plugins.im.IMConnectionProvider;
import hudson.plugins.im.IMException;

final class MpImConnectionProvider extends IMConnectionProvider {

    private static final IMConnectionProvider INSTANCE = new MpImConnectionProvider();

    static final synchronized IMConnectionProvider getInstance() {
        return INSTANCE;
    }

    public MpImConnectionProvider() {
        super();

        // Init must be called in order to start a 'connection'
        init();
    }

    @Override
    public synchronized IMConnection createConnection() throws IMException {
        return MpImConnection.getInstance();
    }

}
