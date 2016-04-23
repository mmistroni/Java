/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.threading;

/**
 *
 * @author marco
 */
public interface Scheduler {

    public void schedule(Runnable runnable, long interval);

    public void shutdown();


}
