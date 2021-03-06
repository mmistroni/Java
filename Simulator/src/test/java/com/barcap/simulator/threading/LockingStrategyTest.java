/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.threading;

import java.util.concurrent.CountDownLatch;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;



/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
@Ignore
public class LockingStrategyTest {

    @Test
    public void testLockingStrategy() throws Exception {
        CountDownLatch startGate = new CountDownLatch(1);
        CountDownLatch endGate = new CountDownLatch(2);
        LockingStrategy strategy = new AtomicLockingStrategy();

        MyTestThread t1 = new MyTestThread(strategy, startGate, endGate);
        MyTestThread t2 = new MyTestThread(strategy, startGate, endGate);

        Thread _t1 = new Thread(t1);
        Thread _t2 = new Thread(t2);
        _t1.start();
        _t2.start();

        startGate.countDown();
        endGate.await();
        Assert.assertTrue(t1.getCounter() == 1);
        Assert.assertTrue(t2.getCounter() == 0);

    }


    private class MyTestThread implements Runnable {
        private LockingStrategy lockingStrategy;
        private CountDownLatch endGate;
        private CountDownLatch startGate;
        private int counter = 0;

        MyTestThread(LockingStrategy lockingStrategy,
                CountDownLatch startGate,
                CountDownLatch endGate) {
            this.lockingStrategy = lockingStrategy;
            this.startGate = startGate;
            this.endGate = endGate;
        }

        public void run() {
            try {
                startGate.await();
                if(lockingStrategy.lock()) {
                    counter++;
                }
            } catch(InterruptedException ignored) {
                
            } finally {
                endGate.countDown();
            }
        }

        public int getCounter() {
            return counter;
        }

    }

}
