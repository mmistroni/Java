/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socketnio.client;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
class RspHandlerImpl implements RspHandler {

    private BlockingQueue<byte[]> queue = new ArrayBlockingQueue<byte[]>(1);
    private final static Log LOGGER = LogFactory.getLog(RspHandlerImpl.class);

    public boolean handleResponse(byte[] rsp) {
        try {
            LOGGER.info("--handling..:" + new String(rsp));
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
            System.out.println("From SErver:" + new String(data));
        } catch (InterruptedException ie) {
            LOGGER.error("Exceptionwhile waiting", ie);
        }
    }


    
}
