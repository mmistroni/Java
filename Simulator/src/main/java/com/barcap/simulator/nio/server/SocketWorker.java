/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.nio.server;

import java.nio.channels.SocketChannel;

/**
 *
 * @author marco
 */
public interface SocketWorker extends Runnable {

    public void handleData(Server server, SocketChannel socketChannel,
            byte[] clientData, int numRead);


}
