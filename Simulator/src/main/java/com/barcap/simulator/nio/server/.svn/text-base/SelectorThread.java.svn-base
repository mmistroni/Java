/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.nio.server;

import com.barcap.simulator.nio.server.handlers.SocketHandler;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class SelectorThread implements Runnable {

    private Selector selector;
    private Acceptor acceptor;


    public SelectorThread(Selector selector, Acceptor acceptor) {
        this.selector = selector;
        this.acceptor = acceptor;

    }

    public void run() {
        while (true) {
            try {
                // Wait for an event one of the registered channels
                this.selector.select();
                // Iterate over the set of keys for which events are available
                Iterator<SelectionKey> selectedKeys = this.selector.selectedKeys().iterator();
                while (selectedKeys.hasNext()) {
                    SelectionKey key =  selectedKeys.next();
                    selectedKeys.remove();

                    if (!key.isValid()) {
                        continue;
                    }

                    // Check what event is available and deal with it
                    if (key.isAcceptable()) {
                        acceptor.handleAccept(key);
                    } else if (key.isReadable()) {
                        SocketHandler handler =
                                (SocketHandler) key.attachment();
                        handler.handleRead(key);
                    } else if (key.isWritable()) {
                        SocketHandler handler =
                                (SocketHandler) key.attachment();
                        handler.handleRead(key);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
