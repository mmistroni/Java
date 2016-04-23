/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.nio.server.handlers;

import java.nio.channels.SelectionKey;

/**
 * ReadWriteHandler.
 * @author marco
 */
public interface SocketHandler {

    public void handleRead(SelectionKey seletionKey);
    
    public void handleWrite(SelectionKey selectionKey);

    public void writeBack(byte[] response);

}
