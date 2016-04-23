/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import com.barcap.simulator.fix.session.SessionManager;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class FixEngineImpl implements FixEngine {

    private final static Log LOGGER = LogFactory.getLog(FixEngineImpl.class);
    private SessionManager sessionManager;

    @Required
    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }


    public void initialize() throws Exception {

    }

    @Override
    public void processMessage(FixMessage fixMessage) throws Exception {
        sessionManager.execute(fixMessage);
    }

    @Override
    public void unregister(SessionListener fixHandler) {
        sessionManager.removeSession(fixHandler);
    }

    @Override
    public void register(SessionListener fixHandler) throws Exception {
        sessionManager.createSession(fixHandler);
    }

    
}
