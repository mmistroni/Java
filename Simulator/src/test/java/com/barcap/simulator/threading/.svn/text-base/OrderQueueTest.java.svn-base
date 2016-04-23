/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.threading;

import com.barcap.simulator.oms.Order;
import com.barcap.simulator.oms.OrderType;
import com.barcap.simulator.oms.OrderImpl;
import java.sql.Timestamp;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;



/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class OrderQueueTest {

    @Test
    public void testQueueOrder() {
        String securityId1 = "testSec";
        String securityId2 = "testSec2";

        Timestamp timestamp1 = new Timestamp(1l);
        Timestamp timestamp2 = new Timestamp(2l);
        Timestamp timestamp3 = new Timestamp(3l);

        Order order1 = new OrderImpl(timestamp1, 1, securityId1, 
                OrderType.BUY, 0, null, 3.0);
        Order order2 = new OrderImpl(timestamp2, 1, securityId2, 
                OrderType.SELL, 0, null, 4.0);
        Order order3 = new OrderImpl(timestamp3, 1, securityId1, 
                OrderType.SELL, 0, null, 5.0);

        OrderQueue queue = new OrderQueueImpl();
        // call
        queue.add(order1);
        queue.add(order2);
        queue.add(order3);

        Order[] orderArray = queue.getOrders();
        Assert.assertEquals(order1, orderArray[0]);
        Assert.assertEquals(order2, orderArray[1]);
        Assert.assertEquals(order3, orderArray[2]);
        
        Order peekOrder = queue.peek();
        Assert.assertEquals(order1, peekOrder);

        Order topOrder = queue.get();
        topOrder.touch();
        queue.add(topOrder);

        peekOrder = queue.get();
        Assert.assertEquals(order2, peekOrder);

        peekOrder = queue.get();
        Assert.assertEquals(order1, peekOrder);



        

    }

    @Test
    @Ignore
    public void testPeek() {
        String securityId1 = "testSec";
        String securityId2 = "testSec2";

        Timestamp timestamp1 = new Timestamp(1l);
        Timestamp timestamp2 = new Timestamp(2l);

        Order order1 = new OrderImpl(timestamp1, 1, 
                securityId1, OrderType.BUY, 1, null, 1.0);
        Order order2 = new OrderImpl(timestamp2, 1, 
                securityId2, OrderType.SELL,2, null, 2.0);

        OrderQueue queue = new OrderQueueImpl();
        // call
        queue.add(order1);
        queue.add(order2);

        Order order = queue.peek();
        Assert.assertEquals(order1, order);
        order.touch();

        order = queue.peek();
        Assert.assertEquals(order2, order);

    }



}
