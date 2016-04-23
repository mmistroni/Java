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
public class ClientRequestImpl implements ClientRequest {

    private final SocketChannel socketChannel;
    private final ChangeRequest changeRequest;
    private final int interestedOp;

    public ClientRequestImpl(SocketChannel socketChannel,
            ChangeRequest changeRequestType, int interestedOp) {
        this.socketChannel = socketChannel;
        this.changeRequest = changeRequestType;
        this.interestedOp = interestedOp;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public ChangeRequest getChangeRequest() {
        return changeRequest;
    }

    public int getInterestedOp() {
        return interestedOp;
    }


}
