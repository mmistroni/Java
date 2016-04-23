/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barcap.simulator.nio.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import com.barcap.simulator.nio.server.handlers.HandlerFactory;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class SocketServerReactor implements Acceptor, Startable {

    private InetAddress hostAddress;
    private int port;
    // The channel on which we'll accept connections
    private ServerSocketChannel serverChannel;
    // The selector we'll be monitoring
    private Selector selector;
    // The buffer into which we'll read data when it's available
    private final static Log LOGGER = LogFactory.getLog(SocketServerReactor.class);
    private final static String SELECTOR_THREAD = "SELECTOR_THREAD";
    private HandlerFactory handlerFactory;

    @Required
    public void setHandlerFactory(HandlerFactory handlerFactory) {
        this.handlerFactory = handlerFactory;
    }


    @Required
    public void setPort(int port) {
        this.port = port;
    }

    
    public SocketServerReactor() throws IOException {
        this.hostAddress = null;


    }

    

    private Selector initSelector() throws IOException {
        // Create a new selector
        this.selector = SelectorProvider.provider().openSelector();

        // Create a new non-blocking server socket channel
        this.serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);

        // Bind the server socket to the specified address and port
        InetSocketAddress isa = new InetSocketAddress("localhost", this.port);
        serverChannel.socket().bind(isa);

        // Register the server socket channel, indicating an interest in
        // accepting new connections
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.err.println(".. Starting server...at port:" + port);
        return selector;
    }

    
    public void handleAccept(SelectionKey key) throws SocketException  {
        try {
            // For an accept to be pending the channel must be a server socket channel.

            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            // Accept the connection and make it non-blocking
            SocketChannel socketChannel = serverSocketChannel.accept();
            Socket socket = socketChannel.socket();
            socketChannel.configureBlocking(false);
            System.err.println("got connection from:" + socketChannel.socket().getInetAddress());
            // Register the new SocketChannel with our Selector, indicating
            // we'd like to be notified when there's data waiting to be read
            socketChannel.register(this.selector, 
                    SelectionKey.OP_READ,
                    handlerFactory.createHandler(socketChannel));
        } catch (IOException e) {
            LOGGER.error("IOException while accepting socket", e);
            try {
                key.channel().close();
                key.cancel();
            } catch(Exception se) {
                LOGGER.error("Failed to close key");
                throw new SocketException(se);
            }
        }
    }

    
    
    @Override
    public void startup() {
        LOGGER.info("Server starting up..");
        try {
            initSelector();
            startSelectorThread();
        } catch(IOException e) {
            LOGGER.info("Exception starting up server", e);
            System.exit(0);
        }
    }

    private void startSelectorThread() {
        SelectorThread selThread = new SelectorThread(selector, this);
        Thread t =  new Thread(selThread);
        t.setName(SELECTOR_THREAD);
        t.run();
    }

    
}


