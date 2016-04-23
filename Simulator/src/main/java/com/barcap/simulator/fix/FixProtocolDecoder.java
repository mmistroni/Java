/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.barcap.simulator.nio.server.ProtocolDecoder;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class FixProtocolDecoder implements ProtocolDecoder {

    private StringBuffer internalBuffer = new StringBuffer();
    private List<String> fixMessagesString = new ArrayList<String>();
    private final static Log LOGGER =
            LogFactory.getLog(FixProtocolDecoder.class);
    private final static FixParser fixParser =
            FixParserFactoryImpl.getInstance().getFixParser();

    


    public FixMessage[] decode(byte[] inputChars) throws Exception {

        LOGGER.info("decoding inputs..");
        // clearing internal Data
        fixMessagesString.clear();
        // add client data
        addToExistingBuffer(inputChars);
        // parse it
        List<String> fixMsgStrings = parseFixString();


        return buildFixMessages(fixMsgStrings);
    }

    public byte[] encode(FixMessage fixMessage) throws Exception {
        StringBuilder builder = new StringBuilder();
        Map<FixTag, Object> fixTagMap = fixMessage.getFixTagMap();

        String beginString = (String) fixTagMap.get(FixTag.BEGINSTRING);
        builder.append(FixTag.BEGINSTRING.getTag()).append(FixConstants.SEPARATOR)
                .append(beginString).append(FixConstants.SOH);
        for(FixTag fixTag : fixTagMap.keySet()) {
            if(!fixTag.equals(FixTag.BEGINSTRING) &&
                    !fixTag.equals(FixTag.CHECKSUM)) {
                builder.append(fixTag.getTag()).append(FixConstants.SEPARATOR)
                    .append(fixTagMap.get(fixTag)).append(FixConstants.SOH);
            }
        }
        int length = builder.length();
        builder.append(FixTag.CHECKSUM.getTag()).append(FixConstants.SEPARATOR).append(length)
                .append(FixConstants.SOH);
        return builder.toString().getBytes();
    }


    private void addToExistingBuffer(byte[] clientData) {
        char[] charArray = new char[clientData.length];
        for(int idx = 0; idx < clientData.length; idx++) {
            charArray[idx] = (char) clientData[idx];
        }
        // appending everything to buffer
        internalBuffer.append(charArray);
        LOGGER.info("internalBuffer is:" + internalBuffer);
    }


    private List<String> parseFixString() throws Exception {
        // 1. getting string representation of buffer
        String internalBufferStr = internalBuffer.toString();
        // 2. splitting based on SOH
        String[] fixPair = internalBufferStr.split(FixConstants.SOH);
        List<String> fixMessages = new ArrayList<String>();

        StringBuffer currentMsg = null;
        for(String pair : fixPair) {
            int separatorIndex = pair.indexOf(FixConstants.SEPARATOR);
            LOGGER.info("pair:" + pair + "!");
            if(separatorIndex < 0) {
                LOGGER.info("cannot find separator. partial data.");
                continue;
            }
            String tagId = pair.substring(0, separatorIndex);
            if(tagId.equals(FixTag.BEGINSTRING.getTag()))
            {
                currentMsg = new StringBuffer();
            }
            // find if this is the last tag
            if(tagId.equals(FixTag.CHECKSUM.getTag())) {
                // if it is the end tag, pack everything
                // and add it to list of fix messages
                currentMsg.append(pair).append(FixConstants.SOH);
                fixMessages.add(currentMsg.toString());
                // recreate buffer and continue
                LOGGER.info("Resetting currentMSG...");
                currentMsg = new StringBuffer();
            } else {
                currentMsg.append(pair);
                currentMsg.append(FixConstants.SOH);
            }
        }
        if(currentMsg.length() > 0) {
            // currentMsg will be the new internal Buffer
            // However we need to keep track of SOH
            if(!internalBufferStr.endsWith(FixConstants.SOH)) {
                int length = currentMsg.length();
                currentMsg.deleteCharAt(length-1);
            }
            internalBuffer = currentMsg;
            
            LOGGER.info("REsetting interalBuffer to:" + internalBuffer);
        }
        return fixMessages;
    }


    private FixMessage[] buildFixMessages(List<String> fixMessages) {
        FixMessage[] fixMsgArray = new FixMessage[fixMessages.size()];
        for(int idx = 0; idx < fixMessages.size(); idx++) {
            fixMsgArray[idx] = fixParser.parseFixMessage(
                    fixMessages.get(idx));
        }
        return fixMsgArray;
    }

    public byte[] encodeError(FixMessage fixMessage, String error) {
        fixMessage.getFixTagMap().put(FixTag.TEXT, error);
        byte[] response = null;

        try {
            response = encode(fixMessage);
        } catch(Exception e) {
            LOGGER.error("error processing fix error msg");
        }

        return response;
    }



}
