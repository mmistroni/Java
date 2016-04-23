/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.oms;

import com.barcap.simulator.exchange.ParticipantKey;
import com.barcap.simulator.fix.session.SessionKey;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class OrderIdImpl implements OrderId{

    private final ParticipantKey participantKey;
    private final SessionKey sessionKey;
    private final Long orderNumber;

    public OrderIdImpl(ParticipantKey routingKey,
            SessionKey sessionKey,
            Long orderNumber) {
        this.participantKey = routingKey;
        this.sessionKey = sessionKey;
        this.orderNumber = orderNumber;
    }


    public ParticipantKey getParticipantKey() {
        return participantKey;
    }

    public SessionKey getSessionId() {
        return sessionKey;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderIdImpl other = (OrderIdImpl) obj;
        if (this.participantKey != other.participantKey && (this.participantKey == null || !this.participantKey.equals(other.participantKey))) {
            return false;
        }
        if (this.sessionKey != other.sessionKey && (this.sessionKey == null || !this.sessionKey.equals(other.sessionKey))) {
            return false;
        }
        if (this.orderNumber != other.orderNumber && (this.orderNumber == null || !this.orderNumber.equals(other.orderNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.participantKey != null ? this.participantKey.hashCode() : 0);
        hash = 59 * hash + (this.sessionKey != null ? this.sessionKey.hashCode() : 0);
        hash = 59 * hash + (this.orderNumber != null ? this.orderNumber.hashCode() : 0);
        return hash;
    }



}
