/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barcap.simulator.fix.session;

import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.barcap.simulator.fix.SessionListener;
import com.barcap.simulator.fix.FixMessage;
import com.barcap.simulator.fix.FixTag;
import com.barcap.simulator.fix.validator.FixValidator;
import com.barcap.simulator.fix.validator.FixValidatorException;
import com.barcap.simulator.nio.server.config.SessionStaticData;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */

// TODO: IMPLEMENTS ALSO SESSIONMESSAGEHANDLER...


public class FixSessionImpl implements Session {

    private final static Log LOGGER = LogFactory.getLog(FixSessionImpl.class);
    private final FixValidator fixValidator;
    private final SessionStaticData sessionStaticData;
    private final SessionListener fixSessionListener;
    private final MessageStore fixMessageStore;
    private SessionState sessionState = SessionState.WAITING_FOR_LOGON;
    private boolean active = false;
    private Set<MessageHandler> messageHandlers;
    private Long sessionKey = null;


    public FixSessionImpl(FixValidator fixValidator,
            SessionStaticData sessionStaticData,
            SessionListener fixSessionListener,
            MessageStore fixMessageStore,
            Set<MessageHandler> messageHandlers,
            Long sessionKey) {
        this.fixValidator = fixValidator;
        this.sessionStaticData = sessionStaticData;
        this.fixSessionListener = fixSessionListener;
        this.fixMessageStore = fixMessageStore;
        this.sessionKey = sessionKey;
        this.messageHandlers = messageHandlers;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void sendMessage(FixMessage fixMessage) {
        addOutgoingMessage(fixMessage);
        addSessionInformation(fixMessage);
        fixSessionListener.onMessage(fixMessage);
    }


    @Override
    public void execute(FixMessage fixMessage) {
        processMessage(fixMessage);
    }

    private void processMessage(FixMessage fixMessage) {
        doPreProcess(fixMessage);
        process(fixMessage);
    }

    private void doPreProcess(FixMessage fixMessage) {
        addIncomingMessage(fixMessage);
        validate(fixMessage);
    }


    private void process(FixMessage fixMessage) {
        for(MessageHandler handler : messageHandlers) {
            if(handler.canHandle(fixMessage)) {
                handler.onFixMessage(fixMessage);
            }
        }
    }

    /*




        switch (fixMessage.getMessageType()) {
            case LOGON:
                handleLogon(fixMessage);
                break;
            case LOGOUT:
                handleLogout(fixMessage);
                break;
            case RESENDREQUEST:
                handleResend(fixMessage);
                break;
            case HEARTBEAT:
                handleHeartbeat(fixMessage);
                break;
            default :
                handOff(fixMessage);
        }
    }

     * 
     */
    
    private boolean checkSessionState(SessionState expectedState) {
        return sessionState.equals(expectedState);
    }

    private void resetSequenceNumbers() {
    }

    SessionState getSessionState() {
        return sessionState;
    }



    private void addOutgoingMessage(FixMessage fixMessage) {
        fixMessageStore.addSentMessage(fixMessage);
    }

    private void addIncomingMessage(FixMessage fixMessage) {
        LOGGER.info("£adding received message..");
        fixMessageStore.addReceivedMessage(fixMessage);
    }

    private void validate(FixMessage fixMessage) {
        try {
            fixValidator.validate(fixMessage, sessionStaticData);
        } catch(FixValidatorException e) {
            LOGGER.warn("ValidationException", e);
            createSessionRejectMessage();
            return;
        }
    }

    private void handOff(FixMessage fixMessage) {
        
    }

    private void createSessionRejectMessage() {
        
    }

    
    public SessionStaticData getSessionStaticData() {
        return sessionStaticData;
    }

    public MessageStore getMessageStore() {
        return fixMessageStore;
    }

    private void handleLogon(FixMessage fixMessage) {
        sessionState = SessionState.LOGON_RECEIVED;
        sendMessage(fixMessage);
        
    }

    private void handleLogout(FixMessage fixMessage) {
        sendMessage(fixMessage);
    }

    private void handleResend(FixMessage fixMessage) {

        
    }

    private void handleHeartbeat(FixMessage fixMessage) {
        sendMessage(fixMessage);
    }


    private void addSessionInformation(FixMessage fixMessage) {
        fixMessage.addTag(FixTag.SENDERCOMPID,
                sessionStaticData.getTargetCompId());
        fixMessage.addTag(FixTag.TARGETCOMPID,
                sessionStaticData.getSenderCompId());
        fixMessage.addTag(FixTag.MSGSEQNUM, String.valueOf(
                getMessageStore().getOutgoingSequenceNum()));
    }

    @Override
    public Long getSessionId() {
        return sessionKey;
    }

    public void initialize() {
        for(MessageHandler handler : messageHandlers) {
            handler.initialize(this);
        }
    }

    
}
