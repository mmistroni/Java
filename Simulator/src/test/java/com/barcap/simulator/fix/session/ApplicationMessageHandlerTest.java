/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix.session;

import com.barcap.simulator.fix.router.FixParticipantRouter;
import org.junit.Before;
import org.junit.Test;
import static org.easymock.EasyMock.*;


/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class ApplicationMessageHandlerTest {
    private ApplicationMessageHandler applicationMessageHandler;
    private FixParticipantRouter participantRouter;
    private ApplicationMessageConverter applicationMessageConverter;

    @Before
    public void setUp() {
        participantRouter = createMock(FixParticipantRouter.class);
        applicationMessageConverter =
                createMock(ApplicationMessageConverter.class);
        applicationMessageHandler =
                new ApplicationMessageHandlerImpl(participantRouter,
                applicationMessageConverter);
        reset(participantRouter, applicationMessageConverter);
    }

    @Test
    public void testInitialize() throws Exception {
        long sessionKey  = 1l;
        Session session = createMock(Session.class);
        // expect
        participantRouter.registerSession(eq(session));
        expectLastCall().once();
        replay(participantRouter);
        // call
        applicationMessageHandler.initialize(session);
        // verify
        verify(participantRouter);
    }



}
