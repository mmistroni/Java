/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix.session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import com.barcap.simulator.fix.SessionListener;
import com.barcap.simulator.fix.SessionListenerKey;
import com.barcap.simulator.fix.FixMessage;
import com.barcap.simulator.fix.FixUtil;
import com.barcap.simulator.nio.server.config.SessionException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class FixSessionManagerImpl implements SessionManager {

    private final static Log LOGGER = LogFactory.getLog(FixSessionManagerImpl.class);
    
    private Map<SessionListenerKey, Session> sessionMap =
            new ConcurrentHashMap<SessionListenerKey, Session>();
    private SessionFactory sessionFactory;

    private BlockingQueue<FixMessage> fixBlockingQueue =
            new ArrayBlockingQueue<FixMessage>(20);


    
    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void createSession(SessionListener fixSessionListener) throws SessionException {
        SessionListenerKey fixHandlerKey = fixSessionListener.getFixHandlerKey();
        Session session = sessionFactory.createSession(fixSessionListener);
        session.initialize();
        sessionMap.put(fixHandlerKey, session);
    }

    @Override
    public void execute(FixMessage fixMessage) throws SessionException {
        try {
            fixBlockingQueue.put(fixMessage);
        } catch(Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new SessionException(e);
        }
    }

    @Override
    public void removeSession(SessionListener fixHandler) {
        sessionMap.remove(fixHandler.getFixHandlerKey());
    }

    public Session getSession(SessionListenerKey key) {
        return sessionMap.get(key);
    }


    class FixRunner implements Runnable {

        public void run() {
            while(true) {
                try {
                    FixMessage fixMessage = fixBlockingQueue.take();
                    SessionListenerKey key = FixUtil.getFixKey(fixMessage);
                    sessionMap.get(key).execute(fixMessage);
                } catch(InterruptedException ex) {
                    
                }

            }



        }

    }


}
