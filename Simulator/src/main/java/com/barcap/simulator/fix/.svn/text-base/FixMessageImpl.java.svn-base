/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class FixMessageImpl implements FixMessage {

    private final static String SEPARATOR = FixConstants.SOH;
    private final static String EQUAL = "=";
    private final Map<FixTag, Object> fixTagMap = new HashMap<FixTag, Object>();

    @Override
    public void addTag(FixTag fixTag, Object value) {
        fixTagMap.put(fixTag, value);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(FixTag.BEGINSTRING.getTag())
                .append(EQUAL)
                .append(fixTagMap.get(FixTag.BEGINSTRING))
                .append(SEPARATOR);
        for(FixTag tag  : fixTagMap.keySet()) {
            if( (!tag.equals(FixTag.CHECKSUM)) &&
            (!tag.equals(FixTag.BEGINSTRING))){
                builder.append(tag.getTag()).append(EQUAL)
                    .append(fixTagMap.get(tag)).append(SEPARATOR);
            }
        }
        builder.append(FixTag.CHECKSUM.getTag()).append(EQUAL)
                .append(fixTagMap.get(FixTag.CHECKSUM))
                .append(SEPARATOR);


        return builder.toString();
    }

    @Override
    public Map<FixTag, Object> getFixTagMap() {
        Map<FixTag, Object> copyMap = new HashMap<FixTag, Object>();
        copyMap.putAll(fixTagMap);
        return copyMap;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FixMessageImpl other = (FixMessageImpl) obj;
        if (this.fixTagMap != other.fixTagMap && (this.fixTagMap == null || !this.fixTagMap.equals(other.fixTagMap))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (this.fixTagMap != null ? this.fixTagMap.hashCode() : 0);
        return hash;
    }

    @Override
    public FixMessageType getMessageType() {
        String msgType = (String) this.getFixTagMap().get(FixTag.MSGTYPE);
        return FixMessageType.toFixMessageType(msgType);
    }

    @Override
    public Object getFixTag(FixTag fixTag) {
        return fixTagMap.get(fixTag);
    }

    
}
