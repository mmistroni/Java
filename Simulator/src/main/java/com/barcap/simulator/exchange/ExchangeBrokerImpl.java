/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.exchange;

import com.barcap.simulator.oms.OrderManager;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Required;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class ExchangeBrokerImpl implements ExchangeBroker {

    private Map<ParticipantKey, Participant> participantsMap =
            new ConcurrentHashMap<ParticipantKey, Participant>();

    private OrderManager orderManager;

    @Required
    public void setOrderManager(OrderManager orderManager) {
        this.orderManager = orderManager;
    }




    public void registerParticipant(Participant participant) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void unregisterParticipant(Participant participant) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void publishEvent(ExchangeEvent exchangeEvent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Map<ParticipantKey, ParticipantHandler> getParticipantHandlers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
