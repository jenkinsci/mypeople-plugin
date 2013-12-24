package com.anyonecantest.jenkins.plugins.mypeople;

import hudson.model.User;
import hudson.plugins.im.IMConnection;
import hudson.plugins.im.IMConnectionListener;
import hudson.plugins.im.IMException;
import hudson.plugins.im.IMMessageTarget;
import hudson.plugins.im.IMPresence;

import java.io.IOException;


final class MpImConnection implements IMConnection {

    private static final MpImConnection INSTANCE = new MpImConnection();

    static MpImConnection getInstance() {
        return INSTANCE;
    }

    @Override
    public void send(IMMessageTarget target, String text) throws MpImException {
    	
        MpMessageTarget gcmTarget = (MpMessageTarget) target;
        String buddyId = gcmTarget.getBuddyId();

		String apikey = MpPublisher.DESCRIPTOR.getApiKey();
		MyPeople.setAPIKEY(apikey);
		MyPeople.sendMessage(buddyId, text);
			
    }


    @Override
    public boolean connect() {
        // Do nothing!
        return true;
    }

    @Override
    public boolean isConnected() {
        return true;
    }

    @Override
    public void setPresence(IMPresence presence, String statusMessage)
            throws IMException {
        // Not required
    }

    @Override
    public void addConnectionListener(IMConnectionListener listener) {
        // Not required
    }

    @Override
    public void removeConnectionListener(IMConnectionListener listener) {
        // Not required
    }

    @Override
    public void close() {
        // Not required
    }

}
