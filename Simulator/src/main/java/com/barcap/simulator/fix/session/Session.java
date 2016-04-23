/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix.session;

import com.barcap.simulator.fix.FixMessage;
import com.barcap.simulator.nio.server.config.SessionStaticData;

/**
 *
 * @author marco
 */
public interface Session {

    public boolean isActive();

    public void initialize();

    public void execute(FixMessage fixMessage);

    public void sendMessage(FixMessage fixMessage);

    public MessageStore getMessageStore();

    public SessionStaticData getSessionStaticData();

    public Long getSessionId();
    
    

}
