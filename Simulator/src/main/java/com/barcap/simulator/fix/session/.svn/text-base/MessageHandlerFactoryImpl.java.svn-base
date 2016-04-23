/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix.session;

import com.barcap.simulator.fix.router.FixParticipantRouter;
import java.util.Set;
import com.barcap.simulator.util.CollectionUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
class MessageHandlerFactoryImpl implements MessageHandlerFactory {

    private FixParticipantRouter participantRouter;
    private final static Log LOGGER = 
            LogFactory.getLog(MessageHandlerFactoryImpl.class);

    @Required
    public void setParticipantRouter(FixParticipantRouter participantRouter) {
        this.participantRouter = participantRouter;
    }
    
    public Set<MessageHandler> getMessageHandlers() {
        LOGGER.info("creating handlers...");
        Set<MessageHandler> handlers = CollectionUtil.newHashSet();
        handlers.add(new SessionMessageHandlerImpl());
        handlers.add(new ApplicationMessageHandlerImpl(
                participantRouter,
                new ApplicationMessageConverterImpl()));
        return handlers;
    }



}
