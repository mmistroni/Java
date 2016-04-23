/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.barcap.simulator.nio.server.ProtocolDecoder;
import com.barcap.simulator.nio.server.config.SessionStaticDataManager;
import com.barcap.simulator.nio.server.handlers.SocketHandler;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
class FixAdapterImpl implements FixAdapter {

    private final static Log LOGGER = LogFactory.getLog(FixAdapterImpl.class);
    private final ProtocolDecoder protocolDecoder = new FixProtocolDecoder();
    private SocketHandler socketHandler;
    private final FixEngine fixService;
    private boolean isInitialized = false;
    private SessionListenerKey fixHandlerKey;

    FixAdapterImpl(FixEngine fixService) {
        this.fixService = fixService;
    }


    public void subscribe(SocketHandler fixHandler) {
        this.socketHandler = fixHandler;
    }

    public void unsubscribe(SocketHandler fixHandler) {
        // do someting with it
    }

    public void shutDown() {
        LOGGER.info("Shutting down.....");
    }

    public void processMessage(byte[] message) throws FixAdapterException {
        LOGGER.info("converting to char...");
        try {
            FixMessage[] fixMessages = protocolDecoder.decode(message);
            
            // can only initialize when first message comes in
            if(!isInitialized && fixMessages.length > 0)
            {
                initializeAndRegister(fixMessages[0]);
            }
            for(FixMessage msg : fixMessages) {


                fixService.processMessage(msg);
            }
        } catch(Exception e) {
            LOGGER.error("Exception in parsing fix message", e);
            throw new FixAdapterException(e);
        }
    }


    private void initializeAndRegister(FixMessage fixMessage) throws Exception {
        String senderCompId = 
                (String) fixMessage.getFixTagMap().get(FixTag.SENDERCOMPID);
        String targetCompId =
                (String) fixMessage.getFixTagMap().get(FixTag.TARGETCOMPID);
        String fixVersion =
                (String) fixMessage.getFixTagMap().get(FixTag.BEGINSTRING);
        fixHandlerKey = new FixSessionListenerKeyImpl(senderCompId, targetCompId, fixVersion);
        fixService.register(this);
        isInitialized = true;
    }

    private void processErrorMessage(FixMessage fixMessage) {
        
    }

    public SessionListenerKey getFixHandlerKey() {
        return fixHandlerKey;
    }

    public void onMessage(FixMessage fixMessage) {
        try {
            byte[] msg = protocolDecoder.encode(fixMessage);
            socketHandler.writeBack(msg);
        } catch(Exception e) {
            LOGGER.warn("exception in decoding..", e);
        }
    }

}
