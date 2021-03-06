/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.oms;

import com.barcap.simulator.threading.LockingStrategy;
import com.barcap.simulator.util.Observer;
import java.sql.Timestamp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.easymock.EasyMock.*;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
@Ignore
@SuppressWarnings("unchecked")
public class OrderBookTest {
    private OrderBook orderBook;
    private LockingStrategy lockingStrategy;
    private String productId = "productId";
    private Observer<Order> orderObserver;



    @Before
    public void setUp() throws Exception {
        lockingStrategy = createMock(LockingStrategy.class);
        orderBook = new OrderBookImpl(productId, lockingStrategy);
        orderObserver = createMock(Observer.class);
        reset(lockingStrategy);
    }

    @Test
    public void testLockOrderBook() throws Exception {
        // expectations
        expect(lockingStrategy.lock()).andReturn(Boolean.TRUE);
        replay(lockingStrategy);
        // call
        orderBook.lockOrderBook();
        // verify
        verify(lockingStrategy);
    }

    @Test
    public void testUnockOrderBook() throws Exception {
        // expectations
        lockingStrategy.unlock();
        expectLastCall().once();
        replay(lockingStrategy);
        // call
        orderBook.unlockOrderBook();
        // verify
        verify(lockingStrategy);
    }

    @Test(expected=java.lang.IllegalStateException.class)
    public void enterOrderThrowingException() throws Exception{
        // if we enter an order withouth locking,
        // it should throw exception
        Order order = createMock(Order.class);
        replay(order);
        expect(lockingStrategy.isLocked()).andReturn(Boolean.FALSE);
        replay(lockingStrategy);
        // call
        orderBook.enterOrder(order);
        // verify
        verify(lockingStrategy, order);

    }

    @Test
    public void enterBuyOrder() throws Exception{
        // if we enter an order withouth locking,
        // it should throw exception
        Order order = createMock(Order.class);
        expect(order.getOrderType()).andReturn(OrderType.BUY);
        replay(order);

        expect(lockingStrategy.isLocked()).andReturn(Boolean.TRUE);
        replay(lockingStrategy);
        // call
        orderBook.enterOrder(order);
        // verify
        verify(lockingStrategy, order);
        Assert.assertTrue(orderBook.getBuyOrders().length == 1);
        Assert.assertTrue(orderBook.getSellOrders().length == 0);

    }

    @Test
    public void enterMultipleBuyOrder() throws Exception{
        // if we enter an order withouth locking,
        // it should throw exception
        Order order = createMock(Order.class);
        expect(order.getOrderType()).andReturn(OrderType.BUY);
        expect(order.getPrice()).andReturn(2.0);
        replay(order);
        Order order2 = createMock(Order.class);
        expect(order2.getOrderType()).andReturn(OrderType.BUY);
        expect(order2.getPrice()).andReturn(1.0);
        replay(order2);

        expect(lockingStrategy.isLocked()).andReturn(Boolean.TRUE).times(2);
        replay(lockingStrategy);
        // call
        orderBook.enterOrder(order);
        orderBook.enterOrder(order2);
        // verify
        verify(lockingStrategy, order);
        Assert.assertTrue(orderBook.getBuyOrders().length == 2);
        Assert.assertTrue(orderBook.getSellOrders().length == 0);
        Order[] orderArray = orderBook.getBuyOrders();
    }

    @Test
    public void enterSellOrder() throws Exception{
        // if we enter an order withouth locking,
        // it should throw exception
        Order order = createMock(Order.class);
        expect(order.getOrderType()).andReturn(OrderType.SELL);
        replay(order);

        expect(lockingStrategy.isLocked()).andReturn(Boolean.TRUE);
        replay(lockingStrategy);
        // call
        orderBook.enterOrder(order);
        // verify
        verify(lockingStrategy, order);
        Assert.assertTrue(orderBook.getBuyOrders().length == 0);
        Assert.assertTrue(orderBook.getSellOrders().length == 1);

    }



    @Test
    public void enterMultipleSellOrder() throws Exception{
        // if we enter an order withouth locking,
        // it should throw exception
        Order order = createMock(Order.class);
        expect(order.getOrderType()).andReturn(OrderType.SELL);
        expect(order.getPrice()).andReturn(2.0);
        replay(order);
        Order order2 = createMock(Order.class);
        expect(order2.getOrderType()).andReturn(OrderType.SELL);
        expect(order2.getPrice()).andReturn(1.0);
        replay(order2);

        expect(lockingStrategy.isLocked()).andReturn(Boolean.TRUE).times(2);
        replay(lockingStrategy);
        // call
        orderBook.enterOrder(order);
        orderBook.enterOrder(order2);
        // verify
        verify(lockingStrategy, order);
        Assert.assertTrue(orderBook.getBuyOrders().length == 0);
        Assert.assertTrue(orderBook.getSellOrders().length == 2);
    }


    @Test
    public void enterCrossOrderSameQuantity() throws Exception{
        Order order = createMock(Order.class);
        expect(order.getOrderType()).andReturn(OrderType.BUY);
        expect(order.getQty()).andReturn(1l);
        expect(order.getPrice()).andReturn(1.0);
        replay(order);
        Order order2 = createMock(Order.class);
        expect(order2.getOrderType()).andReturn(OrderType.SELL);
        expect(order2.getPrice()).andReturn(1.0);
        expect(order2.getQty()).andReturn(1l);
        replay(order2);
        expect(lockingStrategy.isLocked()).andReturn(Boolean.TRUE).times(2);
        replay(lockingStrategy);
        orderObserver.update(isA(Order.class));
        expectLastCall().times(2);
        replay(orderObserver);

        // call
        orderBook.enterOrder(order);
        orderBook.enterOrder(order2);
        // verify
        verify(lockingStrategy, order, orderObserver);
        Assert.assertTrue(orderBook.getBuyOrders().length == 0);
        Assert.assertTrue(orderBook.getSellOrders().length == 0);
    }












}
