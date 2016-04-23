/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package socketnio.client.util;

import java.util.Date;
import com.barcap.simulator.fix.FixMessage;
import com.barcap.simulator.fix.FixMessageFactory;
import com.barcap.simulator.fix.FixMessageType;
import com.barcap.simulator.fix.FixTag;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class FixMessageCreator {

    private final static String FIX_SEPARATOR = "\001";
    private FixMessageCreator() {}

    public static String createFixMessage() {
        StringBuilder builder = new StringBuilder();


        FixMessage fixMsg = FixMessageFactory.createMessage( FixMessageType.LOGON);
        fixMsg.addTag(FixTag.BEGINSTRING, "Fix4.2");
        fixMsg.addTag(FixTag.SENDERCOMPID, "TW");
        fixMsg.addTag(FixTag.TARGETCOMPID, "ISLD");
        fixMsg.addTag(FixTag.MSGSEQNUM, "1");
        fixMsg.addTag(FixTag.CHECKSUM, "140");
        return fixMsg.toString();
    }


    public static String createQuoteMessage() {
        StringBuilder builder = new StringBuilder();


        FixMessage fixMsg = FixMessageFactory.createMessage( FixMessageType.LOGON);
        fixMsg.addTag(FixTag.BEGINSTRING, "Fix4.2");
        fixMsg.addTag(FixTag.SENDERCOMPID, "TW");
        fixMsg.addTag(FixTag.TARGETCOMPID, "ISLD");
        fixMsg.addTag(FixTag.MSGSEQNUM, "1");
        fixMsg.addTag(FixTag.CHECKSUM, "14");
        return fixMsg.toString();
    }


    public static String createOrderMessage() {
        StringBuilder builder = new StringBuilder();


        FixMessage fixMsg = FixMessageFactory.createMessage( FixMessageType.NEWORDERSINGLE);
        fixMsg.addTag(FixTag.BEGINSTRING, "Fix4.1");
        fixMsg.addTag(FixTag.SENDERCOMPID, "ZZ");
        fixMsg.addTag(FixTag.TARGETCOMPID, "IS");
        fixMsg.addTag(FixTag.MSGSEQNUM, "44");
        fixMsg.addTag(FixTag.CHECKSUM, "140");
        return fixMsg.toString();
    }


}
