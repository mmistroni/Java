/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix.validator;

import com.barcap.simulator.fix.FixMessage;
import com.barcap.simulator.fix.FixMessageType;
import com.barcap.simulator.nio.server.config.SessionStaticData;

/**
 *
 * @author marco
 */
public interface FixValidator {

    public void validate(FixMessage msg, SessionStaticData configData)
            throws FixValidatorException;

    public void validateContent(FixMessageType msgType, FixMessage fixMessage)
            throws FixValidatorException;

}
