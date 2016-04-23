/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix.session;

/**
 *
 * @author marco
 */
public enum SessionState {
    WAITING_FOR_LOGON,
    CONNECTED,
    WAITING_FOR_HEARBEAT,
    WAITING_FOR_LOGOFF,
    DISCONNECTED,
    LOGON_RECEIVED,
    DEAD
}
