/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barcap.simulator.nio.server.handlers;

import com.barcap.simulator.nio.server.handlers.SocketHandler;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.barcap.simulator.fix.FixAdapter;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
class SocketHandlerImpl implements SocketHandler {

    private SocketChannel socketChannel;
    private ByteBuffer inBuffer = ByteBuffer.allocate(512);
    private ByteBuffer outBuffer = ByteBuffer.allocate(512);
    private static final Log LOGGER =
            LogFactory.getLog(SocketHandlerImpl.class);
    private FixAdapter fixFacade;
    private String clientAddress;


    SocketHandlerImpl(SocketChannel socketChannel, FixAdapter fixFacade) {
        this.socketChannel = socketChannel;
        this.fixFacade = fixFacade;
        clientAddress = socketChannel.socket().getRemoteSocketAddress().toString();
        registerHandler();
    }

    private void registerHandler() {
        fixFacade.subscribe(this);
    }


    public void handleRead(SelectionKey selectionKey) {
        LOGGER.info("read.cleaning buffer....");
        inBuffer.clear();
        int numRead;
        try {
            synchronized(inBuffer) {
                numRead = socketChannel.read(this.inBuffer);
            }

            if (numRead == -1) {
                // Remote entity shut the socket down cleanly. Do the
                // same from our end and cancel the channel.
                selectionKey.cancel();
                return;
            }

            // Hand the data off to our worker thread
            processData(inBuffer, numRead);
        } catch (Exception e) {
            // The remote forcibly closed the connection, cancel
            // the selection key and close the channel.
            LOGGER.error("Error in handling read for:" + clientAddress, e);
            selectionKey.cancel();
            return;
        }
    }

    public void handleWrite(SelectionKey selectionKey) {
        

    }

    private void processData(ByteBuffer buffer, int numRead) throws Exception {

            byte[] dataCopy = new byte[numRead];
            System.arraycopy(buffer.array(), 0, dataCopy, 0, numRead);
            fixFacade.processMessage(dataCopy);
    }

    public void writeBack(byte[] response) {
        try {
            outBuffer.clear();
            outBuffer.put(response);
            outBuffer.flip();
            socketChannel.write(outBuffer);
        } catch(IOException e) {
            LOGGER.error("Failed to write to client:" + clientAddress, e);
        }
    }


    private void writeToClient(byte[] message)  {
        try {
            outBuffer.clear();
            outBuffer.put(message);
            outBuffer.flip();
            socketChannel.write(outBuffer);
        } catch (Exception e) {
            LOGGER.warn("Error in writing data..");
        }
    }

    

}
