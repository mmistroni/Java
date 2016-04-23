/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.oms;

import com.barcap.simulator.exchange.ParticipantKey;
import com.barcap.simulator.fix.session.SessionKey;

/**
 *
 * @author marco
 */
public interface OrderId {

    public ParticipantKey getParticipantKey();

    public SessionKey getSessionId();

    public Long getOrderNumber();

}
