/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.nio.server;

import java.nio.channels.SocketChannel;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
class ServerDataImpl implements ServerData {
    private final Server server;
    private final SocketChannel socketChannel;
    private final byte[] data;

    ServerDataImpl(Server server, SocketChannel socketChannel,
               byte[] data) {
        this.server = server;
        this.socketChannel = socketChannel;
        this.data = data;
    }
               
    @Override
    public Server getServer() {
        return server;
    }

    @Override
    public SocketChannel getSocketChannel() {
        return socketChannel;
    }
    @Override
    public byte[] getData() {
        return data;
    }


}
