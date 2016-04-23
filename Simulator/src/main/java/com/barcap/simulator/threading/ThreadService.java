/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.threading;

import java.util.concurrent.Future;

/**
 *
 * @author marco
 */
public interface ThreadService {

    public Future<Void> execute(Runnable runnable);

    public Future<Void> schedule(Runnable runnable, long delayMs);

    public Future<Void> scheduleRepetitiveTask(Runnable runnable, long delayMs,
            long fixedRate);


}
