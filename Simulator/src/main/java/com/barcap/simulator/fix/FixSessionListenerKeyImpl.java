/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class FixSessionListenerKeyImpl implements SessionListenerKey {

    private final String senderCompId;
    private final String targetCompId;
    private final String fixVersion;

    @Override
    public String toString() {
        return "FixHandlerKeyImpl [" + "fixVersion " + fixVersion + " " + "senderCompId " + senderCompId + " " + "targetCompId " + targetCompId + "]";
    }

    public FixSessionListenerKeyImpl(String senderCompId, String targetCompId,
            String fixVersion) {
        this.senderCompId = senderCompId;
        this.targetCompId = targetCompId;
        this.fixVersion = fixVersion;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FixSessionListenerKeyImpl other = (FixSessionListenerKeyImpl) obj;
        if ((this.senderCompId == null) ? (other.senderCompId != null) : !this.senderCompId.equals(other.senderCompId)) {
            return false;
        }
        if ((this.targetCompId == null) ? (other.targetCompId != null) : !this.targetCompId.equals(other.targetCompId)) {
            return false;
        }
        if ((this.fixVersion == null) ? (other.fixVersion != null) : !this.fixVersion.equals(other.fixVersion)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (this.senderCompId != null ? this.senderCompId.hashCode() : 0);
        hash = 29 * hash + (this.targetCompId != null ? this.targetCompId.hashCode() : 0);
        hash = 29 * hash + (this.fixVersion != null ? this.fixVersion.hashCode() : 0);
        return hash;
    }

    @Override
    public String getSenderCompId() {
        return senderCompId;
    }

    @Override
    public String getTargetCompId() {
        return targetCompId;
    }
    @Override
    public String getFixVersion() {
        return fixVersion;
    }



}
