/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix.router;

import com.barcap.simulator.exchange.AbstractParticipant;
import com.barcap.simulator.exchange.ExchangeBroker;
import com.barcap.simulator.exchange.ParticipantHandler;
import com.barcap.simulator.exchange.ParticipantKey;
import com.barcap.simulator.fix.session.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Required;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 * TODO: find out how to link Router to Handler.
 */
public class ImsParticipantRouterImpl extends AbstractParticipant
        implements FixParticipantRouter {

    private Map<Long, Session> sessionMap = new ConcurrentHashMap<Long, Session>();
    private ExchangeBroker exchange;
    private String imsParticipantKey;
    
    @Required
    public void setParticipantId(String participantId) {
        this.imsParticipantKey = participantId;
    }


    @Override
    public ParticipantKey getParticipantKey() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ParticipantHandler getParticipantHandler() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void sendEvent(com.barcap.simulator.exchange.ExchangeEvent exchangeEvent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public void registerSession(Session session) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void unregistrerSession(Session session) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getParticipantId() {
        return imsParticipantKey;
    }

}
