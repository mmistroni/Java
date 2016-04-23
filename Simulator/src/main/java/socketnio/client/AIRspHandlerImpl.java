/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socketnio.client;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
class AIRspHandlerImpl implements RspHandler, Runnable {

    private BlockingQueue<byte[]> queue = new ArrayBlockingQueue<byte[]>(1);
    private final static Log LOGGER = LogFactory.getLog(AIRspHandlerImpl.class);
    private Sender sender;



    AIRspHandlerImpl(Sender sender) {
        this.sender = sender;
    }


    public boolean handleResponse(byte[] rsp) {
        try {
            LOGGER.info("--handling..");
            queue.put(rsp);
            return true;
        } catch (InterruptedException ie) {
            LOGGER.info("InterruptedException while reading", ie);
            return false;
        }
    }

    public void waitForResponse() {
        try {
            byte[] data = queue.take();
            System.out.println(new String(data));
        } catch (InterruptedException ie) {
            LOGGER.error("Exceptionwhile waiting", ie);
        }
    }

    public void run() {
            int i=0;
            try {
                byte[] data = queue.take();
                String str = new String(data);
                String response = str + " " + i;
                System.out.println(
                        String.format("Client.REceived:%s, Sending:%s",
                        str, response));
                sender.send(response.getBytes());
                i++;
            } catch (InterruptedException ie) {
                LOGGER.error("Exceptionwhile waiting", ie);
            } catch (IOException ioe) {
                LOGGER.error("IOException", ioe);
            }
        
    }
}
