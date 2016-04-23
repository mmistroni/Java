/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
class SimpleFixParser implements FixParser {

    private static final Log LOGGER = LogFactory.getLog(SimpleFixParser.class);
    
    public FixMessage parseFixMessage(String fixMessageStr) {
        return createFixMessage(fixMessageStr);
    }

    private FixMessage createFixMessage(String fixString) {
        String[] tags = fixString.split(FixConstants.SOH);
        FixMessage fixMessage = new FixMessageImpl();
        for(String tag : tags) {
            String[] pair = tag.split(FixConstants.SEPARATOR);
            fixMessage.addTag(FixTag.getFixTag(pair[0]), pair[1]);
        }
        return fixMessage;
    }



}
