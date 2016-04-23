/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.nio.server;

import java.nio.channels.SelectionKey;

/**
 *
 * @author marco
 */
public interface Acceptor {

    public void handleAccept(SelectionKey selectionKey) throws SocketException;

}
