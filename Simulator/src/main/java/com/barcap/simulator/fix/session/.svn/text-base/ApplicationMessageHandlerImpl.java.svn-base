/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix.session;

import com.barcap.simulator.exchange.ExchangeEvent;
import com.barcap.simulator.oms.ExecutionReport;
import java.util.EnumSet;
import java.util.Set;
import com.barcap.simulator.fix.FixMessage;
import com.barcap.simulator.fix.FixMessageType;
import com.barcap.simulator.fix.router.FixParticipantRouter;
import com.barcap.simulator.oms.OrderCancelReject;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
class ApplicationMessageHandlerImpl
        extends AbstractMessageHandler
        implements ApplicationMessageHandler {

    private final FixParticipantRouter participantRouter;
    private final ApplicationMessageConverter applicationMessageConverter;




    private Set<FixMessageType> supportedTypes =
            EnumSet.complementOf(
            EnumSet.of(FixMessageType.LOGON,
                FixMessageType.LOGOUT,
                FixMessageType.TESTREQUEST,
                FixMessageType.HEARTBEAT,
                FixMessageType.RESENDREQUEST,
                FixMessageType.SEQUENCERESET));


    ApplicationMessageHandlerImpl(FixParticipantRouter participantRouter,
            ApplicationMessageConverter applicationMessageConverter) {
        this.participantRouter = participantRouter;
        this.applicationMessageConverter = applicationMessageConverter;
    }

    @Override
    public void initialize(Session session) {
        participantRouter.registerSession(session);
    }

    @Override
    public void onFixMessage(FixMessage incomingMessage) {
        // convert it to an order msg and send it off
        // most of messages will be orders...
        //participantRouter.routeMessage(null);
    }

    
    
    @Override
    Set<FixMessageType> getSupportedTypes() {
        return supportedTypes;
    }


    public void processExchangeEvent(ExchangeEvent exchangeEvent) {
        FixMessage fixMessage =
                applicationMessageConverter.fromExchangeEvent(exchangeEvent);
        sendBack(fixMessage);
    }

    

    


}
