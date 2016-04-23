/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.nio.server.config;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
class SessionStaticDataImpl implements SessionStaticData {

    private final String targetCompId;
    private final String senderCompId;
    private final String fixVersion;
    private final String host;
    private final long heartBeatInterval;

    public SessionStaticDataImpl(String targetCompId, String senderCompId,
            String fixVersion, String host, long heartBeatInterval) {
        this.targetCompId = targetCompId;
        this.senderCompId = senderCompId;
        this.fixVersion = fixVersion;
        this.host = host;
        this.heartBeatInterval = heartBeatInterval;
    }




    public String getTargetCompId() {
        return targetCompId;
    }

    public String getSenderCompId() {
        return senderCompId;
    }

    public String getFixVersion() {
        return fixVersion;
    }

    public String getHost() {
        return host;
    }

    public long getHeartbeatInterval() {
        return heartBeatInterval;
    }


}
