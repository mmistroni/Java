/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.threading;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class AtomicLockingStrategy implements LockingStrategy {

    private AtomicBoolean lock = new AtomicBoolean(false);

    public boolean lock() {
        return lock.compareAndSet(false, true);
    }

    public void unlock() {
        lock.set(false);
    }

    public boolean isLocked() {
        return lock.get();
    }


}
