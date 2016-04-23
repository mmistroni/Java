/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix.session;

import com.barcap.simulator.fix.FixMessageType;
import com.google.common.collect.ImmutableMap;
import java.util.Map;

/**
 *
 * @author marco
 */
public enum ApplicationMessageType {
    INSERT_ORDER(FixMessageType.NEWORDERCROSS),
    CANCEL_ORDER(FixMessageType.ORDERCANCELREQUEST),
    UPDATE_ORDER(FixMessageType.ORDERCANCELREPLACEREQUEST),
    EXECUTION_REPORT(FixMessageType.EXECUTIONREPORT),
    EXECUTION_ACKNOWLEDGEMENT(FixMessageType.EXECUTIONACKNOWLEDGEMENT);



    private final static Map<FixMessageType, ApplicationMessageType> MSG_TYPES_MAP;

    static {
        ImmutableMap.Builder<FixMessageType, ApplicationMessageType> builder =
                new ImmutableMap.Builder<FixMessageType, ApplicationMessageType>();
        for (ApplicationMessageType appMessageType : values()) {
            builder.put(appMessageType.toFixMessageType(), appMessageType);
        }
        MSG_TYPES_MAP = builder.build();
    }
    
    
    
    
    private FixMessageType fixMessageType;
    
    ApplicationMessageType(FixMessageType type) {
        this.fixMessageType = type;
    }

    public FixMessageType toFixMessageType() {
        return fixMessageType;
    }

    public static ApplicationMessageType toApplicationMessageType(
            FixMessageType fixType) {
        return MSG_TYPES_MAP.get(fixType);
    }
    


}
