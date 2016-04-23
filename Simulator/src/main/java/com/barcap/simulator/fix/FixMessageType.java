/*
 * To change this template), choose Tools | Templates
 * and open the template in the editor.
 */
package com.barcap.simulator.fix;

import com.google.common.collect.ImmutableMap;
import java.util.Map;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public enum FixMessageType {

    HEARTBEAT("0"),
    TESTREQUEST("1"),
    RESENDREQUEST("2"),
    REJECT("3"),
    SEQUENCERESET("4"),
    LOGOUT("5"),
    IOI("6"),
    ADVERTISEMENT("7"),
    EXECUTIONREPORT("8"),
    ORDERCANCELREJECT("9"),
    LOGON("A"),
    DERIVATIVESECURITYLIST("AA"),
    NEWORDERMULTILEG("AB"),
    MULTILEGORDERCANCELREPLACE("AC"),
    TRADECAPTUREREPORTREQUEST("AD"),
    TRADECAPTUREREPORT("AE"),
    ORDERMASSSTATUSREQUEST("AF"),
    QUOTEREQUESTREJECT("AG"),
    RFQREQUEST("AH"),
    QUOTESTATUSREPORT("AI"),
    QUOTERESPONSE("AJ"),
    CONFIRMATION("AK"),
    POSITIONMAINTENANCEREQUEST("AL"),
    POSITIONMAINTENANCEREPORT("AM"),
    REQUESTFORPOSITIONS("AN"),
    REQUESTSFORPOSITIONACK("AO"),
    POSITIONREPORT("AP"),
    TRADECAPTUREREPORTREQUESTACK("AQ"),
    TRADECAPTUREREPORTACK("AR"),
    ALLOCATIONREPORT("AS"),
    ALLOCATIONREPORTACK("AT"),
    CONFIRMATION_ACK("AU"),
    SETTLEMENTINSTRUCTIONREQUEST("AV"),
    ASSIGNMENTREPORT("AW"),
    COLLATERALREQUEST("AX"),
    COLLATERALASSIGNMENT("AY"),
    COLLATERALRESPONSE("AZ"),
    NEWS("B"),
    COLLATERALREPORT("BA"),
    COLLATERALINQUIRY("BB"),
    NETWORKCOUNTERPARTYSYSTEMSTATUSREQUEST("BC"),
    NETWORKCOUNTERPARTYSYSTEMSTATUSRESPONSE("BD"),
    USERREQUEST("BE"),
    USERRESPONSE("BF"),
    COLLATERALINQUIRYACK("BG"),
    CONFIRMATIONREQUEST("BH"),
    TRADINGSESSIONLISTREQUEST("BI"),
    TRADINGSESSIONLIST("BJ"),
    SECURITYLISTUPDATEREPORT("BK"),
    ADJUSTEDPOSITIONREPORT("BL"),
    ALLOCATIONINSTRUCTIONALERT("BM"),
    EXECUTIONACKNOWLEDGEMENT("BN"),
    CONTRARYINTENTIONREPORT("BO"),
    SECURITYDEFINITIONUPDATEREPORT("BP"),
    SETTLEMENTOBLIGATIONREPORT("BQ"),
    DERIVATIVESECURITYLISTUPDATEREPORT("BR"),
    TRADINGSESSIONLISTUPDATEREPORT("BS"),
    MARKETDEFINITIONREQUEST("BT"),
    MARKETDEFINITION("BU"),
    MARKETDEFINITIONUPDATEREPORT("BV"),
    APPLICATIONMESSAGEREQUEST("BW"),
    APPLICATIONMESSAGEREQUESTACK("BX"),
    APPLICATIONMESSAGEREPORT("BY"),
    ORDERMASSACTIONREPORT("BZ"),
    EMAIL("C"),
    ORDERMASSACTIONREQUEST("CA"),
    USERNOTIFICATION("CB"),
    STREAMASSIGNMENTREQUEST("CC"),
    STREAMASSIGNMENTREPORT("CD"),
    STREAMASSIGNMENTREPORTACK("CE"),
    PARTYDETAILSLISTREQUEST("CF"),
    PARTYDETAILSLISTREPORT("CG"),
    NEWORDERSINGLE("D"),
    NEWORDERLIST("E"),
    ORDERCANCELREQUEST("F"),
    ORDERCANCELREPLACEREQUEST("G"),
    ORDERSTATUSREQUEST("H"),
    ALLOCATIONINSTRUCTION("J"),
    LISTCANCELREQUEST("K"),
    LISTEXECUTE("L"),
    LISTSTATUSREQUEST("M"),
    LISTSTATUS("N"),
    ALLOCATIONINSTRUCTIONACK("P"),
    DONTKNOWTRADEDK("Q"),
    QUOTEREQUEST("R"),
    QUOTE("S"),
    SETTLEMENTINSTRUCTION("T"),
    MARKETDATAREQUEST("V"),
    MARKETDATASNAPSHOTFULLREFRESH("W"),
    MARKETDATAINCREMENTALREFRESH("X"),
    MARKETDATAREQUESTREJECT("Y"),
    QUOTECANCEL("Z"),
    QUOTESTATUSREQUEST("a"),
    MASSQUOTEACKNOWLEDGEMENT("b"),
    SECURITYDEFINITIONREQUEST("c"),
    SECURITYDEFINITION("d"),
    SECURITYSTATUSREQUEST("e"),
    SECURITYSTATUS("f"),
    TRADINGSESSIONSTATUSREQUEST("g"),
    TRADINGSESSIONSTATUS("h"),
    MASSQUOTE("i"),
    BUSINESSMESSAGEREJECT("j"),
    BIDREQUEST("k"),
    BIDRESPONSE("l"),
    LISTSTRIKEPRICE("m"),
    XML_non_FIX("n"),
    REGISTRATIONINSTRUCTION("o"),
    REGISTRATIONINSTRUCTIONRESPONSE("p"),
    ORDERMASSCANCELREQUEST("q"),
    ORDERMASSCANCELREPORT("r"),
    NEWORDERCROSS("s"),
    CROSSORDERCANCELREPLACEREQUEST("t"),
    CROSSORDERCANCELREQUEST("u"),
    SECURITYTYPEREQUEST("v"),
    SECURITYTYPES("w"),
    SECURITYLISTREQUEST("x"),
    SECURITYLIST("y"),
    DERIVATIVESECURITYLISTREQUEST("z");
    private final static Map<String, FixMessageType> MSG_TYPES_MAP;

    static {
        ImmutableMap.Builder<String, FixMessageType> builder =
                new ImmutableMap.Builder<String, FixMessageType>();
        for (FixMessageType fixMessageType : values()) {
            builder.put(fixMessageType.toMessageTypeStr(), fixMessageType);
        }
        MSG_TYPES_MAP = builder.build();
    }
    private String tagValue;

    FixMessageType(String tagValue) {
        this.tagValue = tagValue;
    }

    public String toMessageTypeStr() {
        return tagValue;
    }

    public static FixMessageType toFixMessageType(String typeStr) {
        return MSG_TYPES_MAP.get(typeStr);

    }
}