/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix;

/**
 *
 * @author marco
 */
public interface FixEngine {

    public void register(SessionListener fixHandler) throws Exception;

    public void unregister(SessionListener fixHandler);

    public void processMessage(FixMessage fixMessage) throws Exception;


}
