/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.threading;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class SchedulerImpl implements Scheduler {

    private final ScheduledExecutorService schedulerExecutorService =
       Executors.newScheduledThreadPool(1);


    public void schedule(Runnable runnable, long interval) {
        schedulerExecutorService.scheduleWithFixedDelay(
                runnable, interval, interval, TimeUnit.SECONDS);
    }

    public void shutdown() {
        schedulerExecutorService.shutdown();
    }


}
