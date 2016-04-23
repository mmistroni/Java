/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix.session;

import java.util.List;
import com.barcap.simulator.fix.FixMessage;

/**
 *
 * @author marco
 */
public interface MessageStore {

    public void addReceivedMessage(FixMessage fixMessage);

    public void addSentMessage(FixMessage fixMessage);

    public List<FixMessage> getSentMessages();

    public List<FixMessage> getReceivedMessages();

    public List<FixMessage> getSentMessages(int startNum, int endNum);

    public int getIncomingSequenceNum();

    public int getOutgoingSequenceNum();

    public void resetSequences();

    

}
