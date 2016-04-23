/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class FixMessageFactory {


    public static FixMessage createMessage(
            FixMessageType fixMessageType) {
        FixMessage fixMessage = new FixMessageImpl();
        fixMessage.addTag(FixTag.MSGTYPE, fixMessageType.toMessageTypeStr());
        return fixMessage;
    }

    public static FixMessage createLoginReply(String beginString, FixMessage fixMessage) {
        return null;
    }




    public static FixMessage createHeartbeat(String beginString) {
        FixMessage fixMessage = new FixMessageImpl();
        fixMessage.addTag(FixTag.BEGINSTRING, beginString);
        fixMessage.addTag(FixTag.MSGTYPE, FixMessageType.HEARTBEAT);
        return fixMessage;
    }

    public static FixMessage createLogoutReply(FixMessage fixMessage) {
        return null;

    }

    public static  FixMessage createTestMessage(String beginString) {
        FixMessage fixMessage = new FixMessageImpl();
        fixMessage.addTag(FixTag.BEGINSTRING, beginString);
        fixMessage.addTag(FixTag.MSGTYPE, FixMessageType.TESTREQUEST);
        return fixMessage;
    }



}
