package com.anyonecantest.jenkins.plugins.mypeople;

import hudson.Extension;

import hudson.model.User;
import hudson.plugins.im.IMConnection;
import hudson.plugins.im.IMException;
import hudson.plugins.im.IMMessageTarget;
import hudson.plugins.im.IMMessageTargetConversionException;
import hudson.plugins.im.MatrixJobMultiplier;
import hudson.plugins.im.IMPublisher;
import hudson.plugins.im.build_notify.BuildToChatNotifier;

import java.util.List;



public final class MpPublisher extends IMPublisher {

    @Extension
    public static final MpPublisherDescriptor DESCRIPTOR = new MpPublisherDescriptor();

    MpPublisher(List<IMMessageTarget> targets, String notificationStrategy,
            boolean notifyGroupChatsOnBuildStart, boolean notifySuspects, boolean notifyCulprits,
            boolean notifyFixers, boolean notifyUpstreamCommitters,
            BuildToChatNotifier buildToChatNotifier, MatrixJobMultiplier matrixJobMultiplier)
            throws IMMessageTargetConversionException {
        super(targets, notificationStrategy, notifyGroupChatsOnBuildStart, notifySuspects,
                notifyCulprits, notifyFixers, notifyUpstreamCommitters, buildToChatNotifier,
                matrixJobMultiplier);
    }

    @Override
    public MpPublisherDescriptor getDescriptor() {
        return MpPublisher.DESCRIPTOR;
    }

    @Override
    protected IMConnection getIMConnection() throws IMException {
        return MpImConnectionProvider.getInstance().currentConnection();
    }

    @Override
    protected String getPluginName() {
        // Used in log messages
        return "MyPeople";
    }

    @Override
    protected String getConfiguredIMId(User user) {
    	return null;
	
    }

}
