/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix.session;

import com.barcap.simulator.fix.SessionListener;

/**
 *
 * @author marco
 */
public interface SessionFactory {

    public Session createSession(SessionListener sessionListener);

}
