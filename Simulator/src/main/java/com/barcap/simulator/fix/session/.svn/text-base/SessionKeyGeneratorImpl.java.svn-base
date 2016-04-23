/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix.session;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class SessionKeyGeneratorImpl implements SessionKeyGenerator {

    private final AtomicLong idGenerator = new AtomicLong();


    public Long generateSessionKey() {
        return idGenerator.getAndIncrement();
    }


}
