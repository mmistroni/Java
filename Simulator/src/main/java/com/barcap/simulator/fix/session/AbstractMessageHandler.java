/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix.session;

import java.util.Set;
import com.barcap.simulator.fix.FixMessage;
import com.barcap.simulator.fix.FixMessageFactory;
import com.barcap.simulator.fix.FixMessageType;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
abstract class AbstractMessageHandler implements MessageHandler {

    private Session session;


    @Override
    public void initialize(Session session) {
        this.session = session;
    }


    @Override
    public boolean canHandle(FixMessage fixMessage) {
        return getSupportedTypes().contains(fixMessage.getMessageType());
    }


    void sendBack(FixMessage fixMessage) {
        session.sendMessage(fixMessage);
    }

    FixMessage createMessage(FixMessageType fixMessageType) {
        return FixMessageFactory.createMessage(fixMessageType);
    }

    Session getSession() {
        return session;
    }


    abstract Set<FixMessageType> getSupportedTypes();



}
