/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.nio.server.config;

/**
 *
 * @author marco
 */
public interface SessionStaticData {

    public String getTargetCompId();

    public String getSenderCompId();

    public String getFixVersion();

    public String getHost();

    public long getHeartbeatInterval();


}
