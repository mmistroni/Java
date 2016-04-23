/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix.session;

import com.barcap.simulator.fix.FixMessage;

/**
 *
 * @author marco
 */
public interface SessionMessageHandler extends MessageHandler {

    public void onLogon(FixMessage fixMessage);

    public void onLogoff(FixMessage fixMessage);

    public void onTestRequest(FixMessage fixMessage);

    public void onReject(FixMessage fixMessage);

    public void onSequenceReset(FixMessage fixMessage);

    public void onResend(FixMessage fixMessage);

    public void onHeartbeat(FixMessage fixMessage);

}
