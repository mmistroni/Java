/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.nio.server;

import java.nio.channels.SocketChannel;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class SocketWorkerImpl implements SocketWorker {

    private BlockingQueue<ServerData> queue =
            new LinkedBlockingQueue<ServerData>();
    private final static Log LOGGER = LogFactory.getLog(SocketWorkerImpl.class);

    public void handleData(Server server, SocketChannel socketChannel,
            byte[] clientData, int numRead) {
        byte[] dataCopy = new byte[numRead];
        LOGGER.info("logging..");
        System.err.println("handling");
        System.arraycopy(clientData, 0, dataCopy, 0, numRead);
        queue.add(new ServerDataImpl(server, socketChannel, dataCopy));
    }

    public void run() {
        try {
            ServerData data = queue.take();
            LOGGER.info("--sending..");
            data.getServer().send(data.getSocketChannel(), data.getData());
        } catch(InterruptedException ie) {
            LOGGER.warn("Exception while readign from queue", ie);
        }

    }


}
