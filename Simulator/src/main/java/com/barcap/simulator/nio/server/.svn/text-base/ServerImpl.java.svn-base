/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barcap.simulator.nio.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class ServerImpl implements Server, Startable {

    private InetAddress hostAddress;
    private int port;
    // The channel on which we'll accept connections
    private ServerSocketChannel serverChannel;
    // The selector we'll be monitoring
    private Selector selector;
    // The buffer into which we'll read data when it's available
    private ByteBuffer readBuffer = ByteBuffer.allocate(8192);
    private final static Log LOGGER = LogFactory.getLog(ServerImpl.class);
    private SocketWorker socketWorker;
    private List<ClientRequest> changeRequests = new LinkedList<ClientRequest>();
    // Maps a SocketChannel to a list of ByteBuffer instances
    private Map<SocketChannel, List<ByteBuffer>> pendingData =
            new HashMap<SocketChannel, List<ByteBuffer>>();

    @Required
    public void setPort(int port) {
        this.port = port;
    }

    public void setSocketWorker(SocketWorker socketWorker) {
        this.socketWorker = socketWorker;
    }

    public ServerImpl() throws IOException {
        this.hostAddress = null;


    }

    public void init() throws IOException {
        this.selector = this.initSelector();
    }

    private Selector initSelector() throws IOException {
        // Create a new selector
        Selector socketSelector = SelectorProvider.provider().openSelector();

        // Create a new non-blocking server socket channel
        this.serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);

        // Bind the server socket to the specified address and port
        InetSocketAddress isa = new InetSocketAddress("localhost", this.port);
        serverChannel.socket().bind(isa);

        // Register the server socket channel, indicating an interest in
        // accepting new connections
        serverChannel.register(socketSelector, SelectionKey.OP_ACCEPT);
        System.err.println(".. Starting server...at port:" + port);
        return socketSelector;
    }

    public void run() {
        while (true) {
            try {
                // Process any pending changes
                synchronized (this.changeRequests) {
                    Iterator changes = this.changeRequests.iterator();
                    while (changes.hasNext()) {
                        ClientRequest change = (ClientRequest) changes.next();
                        switch (change.getChangeRequest()) {
                            case CHANGEOPS:
                                SelectionKey key = change.getSocketChannel().keyFor(this.selector);
                                key.interestOps(change.getInterestedOp());
                        }
                    }
                    this.changeRequests.clear();
                }

                // Wait for an event one of the registered channels
                this.selector.select();

                // Iterate over the set of keys for which events are available
                Iterator selectedKeys = this.selector.selectedKeys().iterator();
                while (selectedKeys.hasNext()) {
                    SelectionKey key = (SelectionKey) selectedKeys.next();
                    selectedKeys.remove();

                    if (!key.isValid()) {
                        continue;
                    }

                    // Check what event is available and deal with it
                    if (key.isAcceptable()) {
                        this.accept(key);
                    } else if (key.isReadable()) {
                        this.handleRead(key);
                    } else if (key.isWritable()) {
                        this.handleWrite(key);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void accept(SelectionKey key) throws IOException {
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
            socketChannel.register(this.selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            LOGGER.error("IOException while accepting socket", e);
            key.channel().close();
            key.cancel();
        }
    }

    public void send(SocketChannel channel, byte[] data) {
        synchronized (this.changeRequests) {
            // Indicate we want the interest ops set changed
            this.changeRequests.add(new ClientRequestImpl(channel,
                    ChangeRequest.CHANGEOPS, SelectionKey.OP_WRITE));

            // And queue the data we want written
            synchronized (this.pendingData) {
                List<ByteBuffer> queue = this.pendingData.get(channel);
                if (queue == null) {
                    queue = new ArrayList<ByteBuffer>();
                    this.pendingData.put(channel, queue);
                }
                queue.add(ByteBuffer.wrap(data));
            }
        }

        // Finally, wake up our selecting thread so it can make the required changes
        this.selector.wakeup();
    }

    public void handleRead(SelectionKey selectionKey) {
        try {


            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            // Clear out our read buffer so it's ready for new data
            this.readBuffer.clear();

            // Attempt to read off the channel
            int numRead;
            try {
                numRead = socketChannel.read(this.readBuffer);
            } catch (IOException e) {
                // The remote forcibly closed the connection, cancel
                // the selection key and close the channel.
                selectionKey.cancel();
                socketChannel.close();
                return;
            }

            if (numRead == -1) {
                // Remote entity shut the socket down cleanly. Do the
                // same from our end and cancel the channel.
                selectionKey.channel().close();
                selectionKey.cancel();
                return;
            }

            // Hand the data off to our worker thread
            this.socketWorker.handleData(this, socketChannel,
                    this.readBuffer.array(), numRead);
        } catch (IOException ioe) {
            LOGGER.info("Error in reading from socket.", ioe);
            try {
                selectionKey.channel().close();
                selectionKey.cancel();
            } catch (IOException e) {
                LOGGER.error("Exception in closing channel", e);
            }
        }
    }

    public void startup()  {
        new Thread(socketWorker).start();
        new Thread(this).start();
    }

    public void handleWrite(SelectionKey selectionKey) {

        try {

            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

            synchronized (this.pendingData) {
                List queue = (List) this.pendingData.get(socketChannel);

                // Write until there's not more data ...
                while (!queue.isEmpty()) {
                    ByteBuffer buf = (ByteBuffer) queue.get(0);
                    socketChannel.write(buf);
                    if (buf.remaining() > 0) {
                        // ... or the socket's buffer fills up
                        break;
                    }
                    queue.remove(0);
                }

                if (queue.isEmpty()) {
                    // We wrote away all data, so we're no longer interested
                    // in writing on this socket. Switch back to waiting for
                    // data.
                    selectionKey.interestOps(SelectionKey.OP_READ);
                }
            }
        } catch (IOException ioe) {
            LOGGER.info("Error in reading from socket.", ioe);
            try {
                selectionKey.channel().close();
                selectionKey.cancel();
            } catch (IOException e) {
                LOGGER.error("Exception in closing channel", e);
            }
        }
    }
}


