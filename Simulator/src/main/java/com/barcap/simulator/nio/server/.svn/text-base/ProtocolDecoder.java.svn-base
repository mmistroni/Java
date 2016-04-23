/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.nio.server;

import com.barcap.simulator.fix.FixMessage;

/**
 *
 * @author marco
 */
public interface ProtocolDecoder {

    public FixMessage[] decode(byte[] inputChars) throws Exception;

    public byte[] encode(FixMessage fixMessage) throws Exception;

    public byte[] encodeError(FixMessage fixMessage, String error);


}
