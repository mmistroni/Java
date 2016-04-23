/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix.session;

/**
 *
 * @author marco
 */
public class SessionKey {

    private String value = null;

    
    
    public SessionKey(String value) {
        this.value = value;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SessionKey other = (SessionKey) obj;
        if ((this.value == null) ? (other.value != null) : !this.value.equals(other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }


}
