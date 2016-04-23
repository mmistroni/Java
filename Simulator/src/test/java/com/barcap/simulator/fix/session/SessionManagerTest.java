/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix.session;

import com.barcap.simulator.fix.SessionListener;
import com.barcap.simulator.fix.SessionListenerKey;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.easymock.EasyMock.*;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
@Ignore
public class SessionManagerTest {
    private SessionFactory sessionFactory;
    private FixSessionManagerImpl fixSessionManager;
    
    @Before
    public void setUp() throws Exception {
        sessionFactory = createMock(SessionFactory.class);
        fixSessionManager = new FixSessionManagerImpl();
        fixSessionManager.setSessionFactory(sessionFactory);
        reset(sessionFactory);
    }

    @Test
    public void testCreateSession() throws Exception {
        SessionListener sessionListener = createMock(SessionListener.class);
        Session session = createMock(Session.class);
        SessionListenerKey sessionListenerKey =
                createMock(SessionListenerKey.class);
        // expectations
        expect(sessionListener.getFixHandlerKey()).andReturn(sessionListenerKey);
        replay(sessionListener);
        expect(sessionFactory.createSession(eq(sessionListener))).andReturn(session);
        replay(sessionFactory);
        session.initialize();
        expectLastCall().once();
        replay(session);
        // call
        fixSessionManager.createSession(sessionListener);
        // verify
        verify(sessionListener, sessionFactory, session);

        Session testSession = fixSessionManager.getSession(sessionListenerKey);
        Assert.assertEquals(session, testSession);


    }

    @Test
    private void testExecute() throws Exception {
       Assert.fail("to be implemented..");
    }


}
