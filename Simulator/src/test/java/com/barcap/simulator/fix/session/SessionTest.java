/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix.session;

import com.barcap.simulator.fix.FixMessage;
import com.barcap.simulator.fix.SessionListener;
import com.barcap.simulator.fix.validator.FixValidator;
import com.barcap.simulator.nio.server.config.SessionStaticData;
import com.barcap.simulator.util.CollectionUtil;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.*;


/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class SessionTest {
    private FixValidator validator;
    private SessionStaticData sessionStaticData;
    private SessionListener sessionListener;
    private MessageStore messageStore;
    private MessageHandler sessionMessageHandler;
    private MessageHandler applicationMessageHandler;

    @Before
    public void setUp() throws Exception
    {
        validator = createMock(FixValidator.class);
        sessionStaticData  = createMock(SessionStaticData.class);
        sessionListener = createMock(SessionListener.class);
        messageStore = createMock(MessageStore.class);
        sessionMessageHandler = createMock(MessageHandler.class);
        applicationMessageHandler = createMock(MessageHandler.class);

        reset(validator, sessionStaticData, sessionListener,
                sessionMessageHandler, messageStore, applicationMessageHandler);


    }

    @Test
    public void testInitialize() throws Exception {
        Set<MessageHandler> handlers =
                CollectionUtil.newHashSet();
        handlers.add(sessionMessageHandler);
        handlers.add(applicationMessageHandler);


        Session session = new FixSessionImpl(validator, sessionStaticData,
                sessionListener, messageStore, handlers, 1l);
        // expect
        sessionMessageHandler.initialize(eq(session));
        expectLastCall().once();
        replay(sessionMessageHandler);
        applicationMessageHandler.initialize(eq(session));
        expectLastCall().once();
        replay(applicationMessageHandler);
        // call
        session.initialize();
        // verify
        verify(sessionMessageHandler, applicationMessageHandler);
    }

    @Test
    public void testHandleMessageSession() throws Exception {
        Set<MessageHandler> handlers =
                CollectionUtil.newHashSet();
        handlers.add(sessionMessageHandler);
        handlers.add(applicationMessageHandler);

        FixMessage fixMessage = createMock(FixMessage.class);

        Session session = new FixSessionImpl(validator, sessionStaticData,
                sessionListener, messageStore, handlers, 1l);
        // expect
        validator.validate(eq(fixMessage), eq(sessionStaticData));
        expectLastCall().once();
        replay(validator);
        messageStore.addReceivedMessage(eq(fixMessage));
        expectLastCall().once();
        replay(messageStore);
        applicationMessageHandler.initialize(eq(session));
        expect(applicationMessageHandler.canHandle(eq(fixMessage)))
                .andReturn(Boolean.FALSE);
        replay(applicationMessageHandler);
        sessionMessageHandler.initialize(eq(session));
        expectLastCall().once();
        expect(sessionMessageHandler.canHandle(eq(fixMessage)))
                .andReturn(Boolean.TRUE);
        sessionMessageHandler.onFixMessage(fixMessage);
        expectLastCall().once();
        replay(sessionMessageHandler);
        // call
        session.initialize();
        session.execute(fixMessage);
        // verify
        verify(sessionMessageHandler, applicationMessageHandler,
                messageStore, validator);
    }


    @Test
    public void testHandleMessageApplication() throws Exception {
        Set<MessageHandler> handlers =
                CollectionUtil.newHashSet();
        handlers.add(sessionMessageHandler);
        handlers.add(applicationMessageHandler);

        FixMessage fixMessage = createMock(FixMessage.class);

        Session session = new FixSessionImpl(validator, sessionStaticData,
                sessionListener, messageStore, handlers, 1l);
        // expect
        validator.validate(eq(fixMessage), eq(sessionStaticData));
        expectLastCall().once();
        replay(validator);
        messageStore.addReceivedMessage(eq(fixMessage));
        expectLastCall().once();
        replay(messageStore);
        applicationMessageHandler.initialize(eq(session));
        expect(applicationMessageHandler.canHandle(eq(fixMessage)))
                .andReturn(Boolean.TRUE);
        applicationMessageHandler.onFixMessage(eq(fixMessage));
        replay(applicationMessageHandler);
        sessionMessageHandler.initialize(eq(session));
        expectLastCall().once();
        expect(sessionMessageHandler.canHandle(eq(fixMessage)))
                .andReturn(Boolean.FALSE);
        replay(sessionMessageHandler);
        // call
        session.initialize();
        session.execute(fixMessage);
        // verify
        verify(sessionMessageHandler, applicationMessageHandler,
                messageStore, validator);
    }








}
