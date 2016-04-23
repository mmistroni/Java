/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix.session;

import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import com.barcap.simulator.fix.SessionListener;
import com.barcap.simulator.fix.SessionListenerKey;
import com.barcap.simulator.fix.validator.FixValidator;
import com.barcap.simulator.nio.server.config.SessionStaticData;
import com.barcap.simulator.nio.server.config.SessionStaticDataManager;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class FixSessionFactoryImpl implements SessionFactory {

    private SessionStaticDataManager sessionStaticDataManager;
    private FixValidator fixValidator;
    private MessageStoreFactory messageStoreFactory;
    private final static Log LOGGER = 
            LogFactory.getLog(FixSessionFactoryImpl.class);
    private MessageHandlerFactory messageHandlerFactory;
    private SessionKeyGenerator sessionKeyGenerator;

    @Required
    public void setSessionKeyGenerator(SessionKeyGenerator sessionKeyGenerator) {
        this.sessionKeyGenerator = sessionKeyGenerator;
    }

    @Required
    public void setMessageHandlerFactory(MessageHandlerFactory messageHandlerFactory) {
        this.messageHandlerFactory = messageHandlerFactory;
    }




    @Required
    public void setFixValidator(FixValidator fixValidator) {
        this.fixValidator = fixValidator;
    }

    @Required
    public void setMessageStoreFactory(MessageStoreFactory messageStoreFactory) {
        this.messageStoreFactory = messageStoreFactory;
    }

    @Required
    public void setSessionStaticDataManager(SessionStaticDataManager sessionStaticDataManager) {
        this.sessionStaticDataManager = sessionStaticDataManager;
    }

    @Override
    public Session createSession(SessionListener sessionListener) {
        Session session = null;
        try {
            SessionListenerKey key = sessionListener.getFixHandlerKey();

            LOGGER.info("Creatin gkey..");


            SessionStaticData sessionStaticData =
                    getSessionStaticData(key);
            MessageStore messageStore =
                    getMessageStore(key);
            LOGGER.info("getting message store..");

            Set<MessageHandler> handlers =
                    messageHandlerFactory.getMessageHandlers();

            LOGGER.info("After creating handlers..");
            session =  new FixSessionImpl(fixValidator, sessionStaticData,
                sessionListener, messageStore, handlers,
                sessionKeyGenerator.generateSessionKey());
        } catch(Exception e) {
            LOGGER.warn("Exception in creating session...");
        }
        return session;
        
    }


    private SessionStaticData getSessionStaticData(SessionListenerKey key) {
        return sessionStaticDataManager.getConfigData(
                key.getSenderCompId(), key.getTargetCompId(), 
                key.getFixVersion());
    }

    private MessageStore getMessageStore(SessionListenerKey key) throws Exception {
        return messageStoreFactory.getMessageStore(key);
    }

}
