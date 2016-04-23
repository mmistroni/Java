/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.exchange;

import java.sql.Timestamp;

/**
 *
 * @author marco
 */
public interface ExchangeEvent {

    public ExchangeEventType getExchangeEventType();

    public ParticipantKey getParticipantKey();

    public String getSenderCompId();

    public String getTargetCompId();

    public Timestamp getSendingTime();

    public String getCurrency();

    public String getQuoteId();



}
