/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix;

/**
 *
 * @author marco
 */
public interface SessionListenerKey {

    public String getSenderCompId();

    public String getTargetCompId();

    public String getFixVersion();

}
