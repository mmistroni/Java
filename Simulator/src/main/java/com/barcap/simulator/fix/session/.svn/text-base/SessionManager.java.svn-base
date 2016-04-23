/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix.session;

import com.barcap.simulator.fix.SessionListener;
import com.barcap.simulator.fix.FixMessage;
import com.barcap.simulator.nio.server.config.SessionException;

/**
 *
 * @author marco
 */
public interface SessionManager {

    void createSession(SessionListener fixHandler) throws SessionException;

    void execute(FixMessage fixMessage) throws SessionException;

    void removeSession(SessionListener fixHandler);

}
