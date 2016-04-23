/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.threading;

/**
 *
 * @author marco
 */
public interface LockingStrategy {

    public boolean lock();
    
    public void unlock();
    
    public boolean isLocked();



}
