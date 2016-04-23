/*
 * To chfange this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socketnio.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import socketnio.client.util.FixMessageCreator;
import com.barcap.simulator.nio.server.ChangeRequest;
import com.barcap.simulator.nio.server.ClientRequest;
import com.barcap.simulator.nio.server.ClientRequestImpl;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class Client implements Runnable {
// The host:port combination to connect to

    private InetAddress hostAddress;
    private int port;
    // The selector we'll be monitoring
    private Selector selector;
    // The buffer into which we'll read data when it's available
    private ByteBuffer readBuffer = ByteBuffer.allocate(8192);
    // A list of PendingChange instances
    private List<ClientRequest> pendingChanges =
            new LinkedList<ClientRequest>();
    // Maps a SocketChannel to a list of ByteBuffer instances
    private Map<SocketChannel, List<ByteBuffer>> pendingData =
            new HashMap<SocketChannel, List<ByteBuffer>>();

    ;
    // Maps a SocketChannel to a RspHandler
    private Map<SocketChannel, RspHandler> rspHandlers =
            new ConcurrentHashMap<SocketChannel, RspHandler>();
    private final static Log LOGGER = LogFactory.getLog(Client.class);

    public Client(InetAddress hostAddress, int port) throws IOException {
        this.hostAddress = hostAddress;
        this.port = port;
        this.selector = this.initSelector();
    }

    public void send(byte[] data, RspHandler handler) throws IOException {
        // Start a new connection
        SocketChannel socket = this.initiateConnection();

        // Register the response handler
        this.rspHandlers.put(socket, handler);

        // And queue the data we want written
        synchronized (this.pendingData) {
            List<ByteBuffer> queue = this.pendingData.get(socket);
            if (queue == null) {
                queue = new ArrayList<ByteBuffer>();
                this.pendingData.put(socket, queue);
            }
            queue.add(ByteBuffer.wrap(data));
        }

        // Finally, wake up our selecting thread so it can make the required changes
        this.selector.wakeup();
    }

    public void run() {
        while (true) {
            try {
                // Process any pending changes
                synchronized (this.pendingChanges) {
                    Iterator changes = this.pendingChanges.iterator();
                    while (changes.hasNext()) {
                        ClientRequest change = (ClientRequest) changes.next();
                        switch (change.getChangeRequest()) {
                            case CHANGEOPS:
                                SelectionKey key = change.getSocketChannel().keyFor(this.selector);
                                key.interestOps(change.getInterestedOp());
                                break;
                            case REGISTER:
                                change.getSocketChannel().register(
                                        this.selector,
                                        change.getInterestedOp());
                                break;
                        }
                    }
                    this.pendingChanges.clear();
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
                    if (key.isConnectable()) {
                        this.finishConnection(key);
                    } else if (key.isReadable()) {
                        this.read(key);
                    } else if (key.isWritable()) {
                        this.write(key);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();

        // Clear out our read buffer so it's ready for new data
        this.readBuffer.clear();

        // Attempt to read off the channel
        int numRead;
        try {
            numRead = socketChannel.read(this.readBuffer);
        } catch (IOException e) {
            // The remote forcibly closed the connection, cancel
            // the selection key and close the channel.
            key.cancel();
            socketChannel.close();
            return;
        }

        if (numRead == -1) {
            // Remote entity shut the socket down cleanly. Do the
            // same from our end and cancel the channel.
            key.channel().close();
            key.cancel();
            return;
        }

        // Handle the response
        this.handleResponse(socketChannel, this.readBuffer.array(), numRead);
    }

    private void handleResponse(SocketChannel socketChannel, byte[] data, int numRead) throws IOException {
        // Make a correctly sized copy of the data before handing it
        // to the client
        byte[] rspData = new byte[numRead];
        System.arraycopy(data, 0, rspData, 0, numRead);

        // Look up the handler for this channel
        RspHandler handler = this.rspHandlers.get(socketChannel);

        // And pass the response to it
        if (handler.handleResponse(rspData)) {
            // The handler has seen enough, close the connection
            LOGGER.info("exiting.");
            socketChannel.close();
            socketChannel.keyFor(this.selector).cancel();
        }
    }

    private void write(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();

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
                key.interestOps(SelectionKey.OP_READ);
            }
        }
    }

    private void finishConnection(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();

        // Finish the connection. If the connection operation failed
        // this will raise an IOException.
        try {
            socketChannel.finishConnect();
        } catch (IOException e) {
            // Cancel the channel's registration with our selector
            System.out.println(e);
            key.cancel();
            return;
        }

        // Register an interest in writing on this channel
        key.interestOps(SelectionKey.OP_WRITE);
    }

    private SocketChannel initiateConnection() throws IOException {
        // Create a non-blocking socket channel
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        // Kick off connection establishment
        socketChannel.connect(new InetSocketAddress(this.hostAddress, this.port));

        // Queue a channel registration since the caller is not the
        // selecting thread. As part of the registration we'll register
        // an interest in connection events. These are raised when a channel
        // is ready to complete connection establishment.
        synchronized (this.pendingChanges) {
            this.pendingChanges.add(new ClientRequestImpl(
                    socketChannel, ChangeRequest.REGISTER, SelectionKey.OP_CONNECT));
        }

        return socketChannel;
    }

    private Selector initSelector() throws IOException {
        // Create a new selector
        return SelectorProvider.provider().openSelector();
    }

    public static void main(String[] args) {
        try {
            System.err.println("Starting client...");
            Client client = new Client(
                    InetAddress.getByName("localhost"), 4444);
            Thread t = new Thread(client);
            t.setDaemon(true);
            t.start();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            String line = "";
            RspHandler handler = new RspHandlerImpl();

            System.err.println("befor eloop..");

            String msg = FixMessageCreator.createFixMessage();
            LOGGER.info("sending: fix instead:\n" + msg);

            client.send(msg.getBytes(), handler);
            handler.waitForResponse();

            /*


            while((line = reader.readLine()) != null)
            {
            if(line.equalsIgnoreCase("bye")) {
            LOGGER.info("exiting..");
            System.exit(1);
            }

            System.err.println("in the loop..");

            String msg = FixMessageCreator.createFixMessage();
            LOGGER.info("sending: fix instead:\n" + msg);

            client.send(msg.getBytes(), handler);
            handler.waitForResponse();
            }
             */


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



