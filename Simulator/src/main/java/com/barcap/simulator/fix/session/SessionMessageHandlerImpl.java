/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barcap.simulator.fix.session;

import java.util.EnumSet;
import java.util.Set;
import com.barcap.simulator.fix.FixMessage;
import com.barcap.simulator.fix.FixMessageFactory;
import com.barcap.simulator.fix.FixMessageType;
import com.barcap.simulator.fix.FixTag;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
class SessionMessageHandlerImpl
        extends AbstractMessageHandler
        implements SessionMessageHandler {

    private final Set<FixMessageType> supportedTypes =
            EnumSet.of(
            FixMessageType.LOGON,
            FixMessageType.LOGOUT,
            FixMessageType.TESTREQUEST,
            FixMessageType.HEARTBEAT,
            FixMessageType.RESENDREQUEST,
            FixMessageType.SEQUENCERESET);

    
    @Override
    public void onLogon(FixMessage fixMessage) {
        FixMessage reply = super.createMessage(FixMessageType.LOGON);
        sendBack(reply);
    }

    @Override
    public void onLogoff(FixMessage fixMessage) {
        FixMessage reply = createMessage(FixMessageType.LOGOUT);
        sendBack(reply);
    }

    @Override
    public void onTestRequest(FixMessage fixMessage) {
        FixMessage reply = createMessage(FixMessageType.TESTREQUEST);
        sendBack(reply);
    }

    @Override
    public void onReject(FixMessage fixMessage) {
        FixMessage reply = createMessage(FixMessageType.REJECT);
        sendBack(reply);
    }

    @Override
    public void onSequenceReset(FixMessage fixMessage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onResend(FixMessage fixMessage) {
        MessageStore messageStore = getSession().getMessageStore();
        String startNum = (String) (fixMessage.getFixTagMap().get(FixTag.BEGINSEQNO));
        String endNum = (String) (fixMessage.getFixTagMap()).get(FixTag.ENDSEQNO);
        // get list of messages
        for (FixMessage fixMsg : messageStore.getSentMessages(Integer.parseInt(startNum),
                Integer.parseInt(endNum))) {
            sendBack(fixMsg);
        }
    }

    @Override
    public void onHeartbeat(FixMessage fixMessage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onFixMessage(FixMessage incomingMessage) {
        switch (incomingMessage.getMessageType()) {
            case LOGON:
                onLogon(incomingMessage);
                break;
            case LOGOUT:
                onLogoff(incomingMessage);
                break;
            case RESENDREQUEST:
                onResend(incomingMessage);
                break;
            case HEARTBEAT:
                onHeartbeat(incomingMessage);
                break;
            case SEQUENCERESET:
                onSequenceReset(incomingMessage);
                break;
            case TESTREQUEST:
                onTestRequest(incomingMessage);
                break;

        }

    }

    @Override
    Set<FixMessageType> getSupportedTypes() {
        return supportedTypes;
    }


}
