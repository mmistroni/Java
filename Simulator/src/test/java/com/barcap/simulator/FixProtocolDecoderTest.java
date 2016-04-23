/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barcap.simulator;

import com.barcap.simulator.nio.server.ProtocolDecoder;
import com.barcap.simulator.fix.FixProtocolDecoder;
import java.util.Date;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import socketnio.client.util.FixMessageCreator;
import com.barcap.simulator.fix.FixMessage;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class FixProtocolDecoderTest {

    private final static String FIX_SEPARATOR = "\001";
    private ProtocolDecoder protocolDecoder;

    @Before
    public void setUp() throws Exception {
        protocolDecoder = new FixProtocolDecoder();
    }

    @Test
    @Ignore
    public void testEncodeWholeMessage() throws Exception {
        StringBuilder builder = new StringBuilder();
        /*
        builder.append("8=FIX4.2").append(FIX_SEPARATOR)
        .append("35=A").append(FIX_SEPARATOR)
        .append("34=1").append("49=TW").append(FIX_SEPARATOR)
        .append("52=" + new Date()).append(FIX_SEPARATOR)
        .append("56=ISLD").append(FIX_SEPARATOR)
        .append("108=30").append(FIX_SEPARATOR)
        .append("10=0").append(FIX_SEPARATOR);
         */
        String msg = FixMessageCreator.createFixMessage();
        builder.append(msg);



        byte[] clientBytes = builder.toString().getBytes();
        FixMessage[] fixMessages = protocolDecoder.decode(clientBytes);
        System.err.println(builder);
        Assert.assertEquals(1, fixMessages.length);

        System.err.println("---" + fixMessages[0].toString());

    }

    @Test
    @Ignore
    public void testEncodePartialMessage() throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("8=FIX4.2").append(FIX_SEPARATOR).append("35=A").append(FIX_SEPARATOR).append("34=1").append("49=TW").append(FIX_SEPARATOR).append("52=" + new Date()).append(FIX_SEPARATOR).append("56=ISLD");

        byte[] clientBytes = builder.toString().getBytes();
        FixMessage[] fixMessages = protocolDecoder.decode(clientBytes);
        Assert.assertEquals(0, fixMessages.length);
    }

    @Test
    @Ignore
    public void testEncodeMoreThanOneMessage() throws Exception {
        StringBuilder builder = new StringBuilder();
        // First Fix Message
        builder.append("8=FIX4.2").append(FIX_SEPARATOR).append("35=A").append(FIX_SEPARATOR).append("34=1").append("49=TW").append(FIX_SEPARATOR).append("52=" + new Date()).append(FIX_SEPARATOR).append("56=ISLD").append(FIX_SEPARATOR).append("108=30").append(FIX_SEPARATOR).append("10=0").append(FIX_SEPARATOR);
        // Second Fix Message
        builder.append("8=FIX4.2").append(FIX_SEPARATOR).append("35=A").append(FIX_SEPARATOR).append("34=1").append("49=TW").append(FIX_SEPARATOR).append("52=" + new Date()).append(FIX_SEPARATOR).append("56=ISLD");

        byte[] clientBytes = builder.toString().getBytes();
        FixMessage[] fixMessages = protocolDecoder.decode(clientBytes);
        Assert.assertEquals(1, fixMessages.length);
    }

    @Test
    @Ignore
    public void testEncodeTwoMessages() throws Exception {
        StringBuilder builder = new StringBuilder();
        // First Fix Message
        builder.append("8=FIX4.2").append(FIX_SEPARATOR).append("35=A").append(FIX_SEPARATOR).append("34=1").append("49=TW").append(FIX_SEPARATOR).append("52=" + new Date()).append(FIX_SEPARATOR).append("56=ISLD").append(FIX_SEPARATOR).append("108=30").append(FIX_SEPARATOR).append("10=0").append(FIX_SEPARATOR);
        // Second Fix Message
        builder.append("8=FIX4.2").append(FIX_SEPARATOR).append("35=A").append(FIX_SEPARATOR).append("34=1").append("49=TW").append(FIX_SEPARATOR).append("52=" + new Date()).append(FIX_SEPARATOR).append("56=ISLD").append(FIX_SEPARATOR).append("108=30").append(FIX_SEPARATOR).append("10=0").append(FIX_SEPARATOR);

        byte[] clientBytes = builder.toString().getBytes();
        FixMessage[] fixMessages = protocolDecoder.decode(clientBytes);
        Assert.assertEquals(2, fixMessages.length);
    }

    @Test
    public void testEncodeTwoSplittedMessages() throws Exception {
        StringBuilder builder = new StringBuilder();
        // First Fix Message
        builder.append("8=FIX4.2").append(FIX_SEPARATOR).append("35=A").append(FIX_SEPARATOR).append("34=1").append(FIX_SEPARATOR).append("49=TW").append(FIX_SEPARATOR).append("52=" + new Date()).append(FIX_SEPARATOR).append("56=ISLD").append(FIX_SEPARATOR).append("108=30").append(FIX_SEPARATOR).append("10=0").append(FIX_SEPARATOR);
        // Second Fix Message
        builder.append("8=FIX4.2").append(FIX_SEPARATOR).append("35=B");

        byte[] clientBytes = builder.toString().getBytes();

        FixMessage[] fixMessages = protocolDecoder.decode(clientBytes);
        Assert.assertEquals(1, fixMessages.length);

        // adding remainder..

        StringBuilder builder2 = new StringBuilder();
        builder2.append(FIX_SEPARATOR).append("34=12").append(FIX_SEPARATOR).append("49=XS").append(FIX_SEPARATOR).append("52=" + new Date()).append(FIX_SEPARATOR).append("56=IS").append(FIX_SEPARATOR).append("108=3").append(FIX_SEPARATOR).append("10=44").append(FIX_SEPARATOR);
        FixMessage[] remainderMessages = protocolDecoder.decode(
                builder2.toString().getBytes());

        Assert.assertEquals(1, remainderMessages.length);
    }
}
