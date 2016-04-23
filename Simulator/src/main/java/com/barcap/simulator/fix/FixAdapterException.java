/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class FixAdapterException extends Exception {

    public FixAdapterException(String msg) {
        super(msg);
    }

    public FixAdapterException(String msg, Throwable t) {
        super(msg, t);
    }

    public FixAdapterException(Throwable t) {
        super(t);
    }

}
