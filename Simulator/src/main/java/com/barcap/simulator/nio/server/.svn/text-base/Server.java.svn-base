/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.nio.server;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 *
 * @author marco
 */
public interface Server extends Runnable {

    public void send(SocketChannel channel, byte[] data);

    public void handleRead(SelectionKey selectionKey);

    public void handleWrite(SelectionKey selectionKey);




}
