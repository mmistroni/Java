/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.nio.server.config;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
class SessionStaticDataManagerImpl implements SessionStaticDataManager {

    public SessionStaticData getConfigData(String senderCompId, String targetCompId, String fixVersion) {
        return new SessionStaticDataImpl("targetCompId", "senderCompId", "4.2",
                "localhost", 10000);
    }


}
