/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix;

import com.barcap.simulator.nio.server.handlers.SocketHandler;

/**
 *
 * @author marco
 */
public interface FixAdapter extends SessionListener {

    public void subscribe(SocketHandler fixHandler);
    
    public void unsubscribe(SocketHandler fixHandler);
    
    public void shutDown();

    public void processMessage(byte[] message) throws FixAdapterException;

}
