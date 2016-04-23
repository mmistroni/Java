/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix.session;

import java.util.List;
import com.barcap.simulator.fix.FixMessage;
import com.barcap.simulator.util.CollectionUtil;


/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class MemoryMessageStore implements MessageStore {

    private final List<FixMessage> incomingMessagesList =
            CollectionUtil.newArrayList();
    private final List<FixMessage> outgoingMessagesList =
            CollectionUtil.newArrayList();

    private int incomingSequenceNum = 1;
    private int outgoingSequenceNum = 0;



    public void addReceivedMessage(FixMessage fixMessage) {
        incomingMessagesList.add(fixMessage);
        incomingSequenceNum++;

    }

    public void addSentMessage(FixMessage fixMessage) {
        outgoingMessagesList.add(fixMessage);
        outgoingSequenceNum++;
    }

    public List<FixMessage> getSentMessages() {
        return outgoingMessagesList;
    }

    public List<FixMessage> getReceivedMessages() {
        return incomingMessagesList;
    }

    public List<FixMessage> getSentMessages(int startNum, int endNum) {
        List<FixMessage> copyList =
                CollectionUtil.newArrayList();
        for(int idx = startNum; idx <= endNum; idx++) {
            copyList.add(incomingMessagesList.get(idx));
        }
        return copyList;
    }

    public int getIncomingSequenceNum() {
        return incomingSequenceNum;
    }

    public int getOutgoingSequenceNum() {
        return outgoingSequenceNum;
    }

    public void resetSequences() {
        incomingSequenceNum = 1;
        outgoingSequenceNum = 0;
    }


}
