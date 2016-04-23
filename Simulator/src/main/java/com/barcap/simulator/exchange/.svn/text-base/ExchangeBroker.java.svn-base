/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.exchange;

import java.util.Map;

/**
 *
 * @author marco
 */
public interface ExchangeBroker {

    public void registerParticipant(Participant participant);

    public void unregisterParticipant(Participant participant);

    public void publishEvent(ExchangeEvent exchangeEvent);

    public Map<ParticipantKey, ParticipantHandler> getParticipantHandlers();



}
