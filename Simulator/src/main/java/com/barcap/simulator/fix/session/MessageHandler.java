/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix.session;

import com.barcap.simulator.fix.FixMessage;

/**
 *
 * @author marco
 */
public interface MessageHandler {

    public void initialize(Session session);

    public void onFixMessage(FixMessage incomingMessage);

    public boolean canHandle(FixMessage fixMessage);




}
