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
public interface MessageHandlerManager {

    public void initialize(Session session);

    public void handle(FixMessage fixMessage);

    


}
