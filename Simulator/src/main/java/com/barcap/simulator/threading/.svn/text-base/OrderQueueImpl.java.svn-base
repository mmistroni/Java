/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.threading;

import com.barcap.simulator.oms.Order;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;


/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class OrderQueueImpl implements OrderQueue {

    // One way to implement this is to use 2 flags:
    // - touchedFlag, means the order has been retrieved but unable
    //   to proceed coz OrderBook was locked
    // - processedFlag, means the order has been processed, and it should be
    //   removed at next iteration
    
    // processed by thread,  we set a processedFlag.
    // Algorithm should be:
    // - order by Timestamp & processedFlag


    // Alternative implementation:
    /*
     * Maintain two queues: one for order that havent been touched, and one
     * for order that have been touched.
     * When requesting next item, we look first at orders that have been touched,
     * and then to order that haven't been touched.
     * In the 'touchedOrder' queue we order by timestamp
     *
     * This class, by having a look at OrderBook, should be in charge of giving
     * back an element so that the queue never hangs
     *
     *
     *
     */




    private Queue<Order> orderQueue = new PriorityQueue<Order>(100, 
            new OrderComparator());

    public synchronized void add(Order order) {
        orderQueue.add(order);
    }

    public synchronized Order[] getOrders() {
        return orderQueue.toArray(new Order[orderQueue.size()]);
    }

    public Order get() {
        return orderQueue.remove();
    }

    public Order peek() {
        return orderQueue.peek();
    }


    class OrderComparator implements Comparator<Order> {

        public int compare(Order o1, Order o2) {
            if(o1.getSecurityID().equals(o2.getSecurityID())) {
                if(o1.getTouchCount() > o2.getTouchCount()) {
                    return -1;
                } else if(o1.getTouchCount() < o2.getTouchCount()) {
                    return 1;
                } else {
                    return orderByTimestamp(o1.getTimestamp(),
                            o2.getTimestamp());
                }
            }
            if(orderByTouchCount(o1, o2) == 0) {
                return orderByTimestamp(o1.getTimestamp(),
                        o2.getTimestamp());
            }
            return orderByTouchCount(o1, o2);
         }

        private int orderByTouchCount(Order o1, Order o2) {
            
            if(o1.getTouchCount() < o2.getTouchCount()) {
                return -1;
            } else if(o1.getTouchCount() > o2.getTouchCount()) {
                return 1;
            }
            return 0;
        }


        private int orderByTimestamp(Timestamp t1, Timestamp t2) {

            if(t1.before(t2)) {
                return -1;
            }
            return 1;
        }

    }






}
