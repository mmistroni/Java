/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix;

import java.util.Map;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class FixUtil {

    private FixUtil() {}

    public static SessionListenerKey getFixKey(FixMessage fixMessage) {
        Map<FixTag, Object> fixTagMap = fixMessage.getFixTagMap();
        String senderCompId =
                (String) fixTagMap.get(FixTag.SENDERCOMPID);
        String targetCompId =
                (String) fixTagMap.get(FixTag.TARGETCOMPID);
        String fixVersion =
                (String) fixTagMap.get(FixTag.BEGINSTRING);

        System.err.println("-- creating key...");
        return new FixSessionListenerKeyImpl(senderCompId, targetCompId, fixVersion);
    }

}
