/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
@SuppressWarnings("unchecked")
public class ThreadServiceImpl implements ThreadService {

    private ExecutorService executorService =
            Executors.newFixedThreadPool(1);
    private ScheduledExecutorService schedulerService =
            Executors.newScheduledThreadPool(1);


    @Override
    public Future<Void> execute(Runnable runnable) {
        return (Future<Void>) executorService.submit(runnable);
    }

    @Override
    public Future<Void> schedule(Runnable runnable, long delayMs) {
        return (Future<Void>) schedulerService.schedule(runnable, delayMs, TimeUnit.MILLISECONDS);
    }

    @Override
    public Future<Void>  scheduleRepetitiveTask(Runnable runnable, long delayMs, long fixedRate) {
        return (Future<Void>) schedulerService.scheduleWithFixedDelay(runnable, delayMs,
                fixedRate, TimeUnit.MILLISECONDS);
    }


}
