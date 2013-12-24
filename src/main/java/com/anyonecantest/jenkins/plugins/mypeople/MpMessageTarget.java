package com.anyonecantest.jenkins.plugins.mypeople;

import hudson.plugins.im.IMMessageTarget;



import org.kohsuke.stapler.export.Exported;

final class MpMessageTarget implements IMMessageTarget {

    private static final long serialVersionUID = 1L;

    private final String buddyId;

    /**
     * Creates a target who should receive notification messages.
     * 
     * @param buddyId My People buddy ID.
     */
    MpMessageTarget(String buddyId) {
        this.buddyId = buddyId;
    }

    @Exported
    public String getBuddyId() {
        return buddyId;
    }

    /** @return Returns null */
    String getToken() {
    	return null;
    	
    }

    @Override
    public String toString() {
        return getBuddyId();
    }

}
