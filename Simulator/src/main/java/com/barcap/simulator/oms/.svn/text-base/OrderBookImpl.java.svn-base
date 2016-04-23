/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barcap.simulator.oms;

import com.barcap.simulator.threading.LockingStrategy;
import com.barcap.simulator.util.CollectionUtil;
import com.barcap.simulator.util.ObservableImpl;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class OrderBookImpl extends ObservableImpl<Order>
        implements OrderBook {

    private String productId;
    private LockingStrategy lockingStrategy;
    private Queue<Order> buyOrders = new PriorityQueue<Order>(100,
            new BuyComparator());
    private Queue<Order> sellOrders =
            new PriorityQueue<Order>(100, new SellComparator());

    static class BuyComparator implements Comparator<Order> {

        public int compare(Order t, Order t1) {
            if (t.getPrice() < t1.getPrice()) {
                return -1;
            } else if (t.getPrice() > t1.getPrice()) {
                return 1;
            } else {
                if (t.getTimestamp().before(t1.getTimestamp())) {
                    return -1;
                } else if (t.getTimestamp().after(t1.getTimestamp())) {
                    return 1;
                }
                return 0;
            }
        }
    }

    static class SellComparator implements Comparator<Order> {

        public int compare(Order t, Order t1) {
            if (t.getPrice() < t1.getPrice()) {
                return 1;
            } else if (t.getPrice() > t1.getPrice()) {
                return -1;
            } else {
                if (t.getTimestamp().before(t1.getTimestamp())) {
                    return -1;
                } else if (t.getTimestamp().after(t1.getTimestamp())) {
                    return 1;
                }
                return 0;
            }
        }
    }

    public OrderBookImpl(String productId, LockingStrategy lockingStrategy) {
        this.productId = productId;
        this.lockingStrategy = lockingStrategy;
    }

    @Override
    public Order[] getBuyOrders() {
        return buyOrders.toArray(new Order[buyOrders.size()]);
    }

    @Override
    public Order[] getSellOrders() {
        return sellOrders.toArray(new Order[sellOrders.size()]);
    }

    @Override
    public void enterOrder(Order order) throws IllegalStateException {
        if (!lockingStrategy.isLocked()) {
            throw new IllegalStateException("OrderBook must be locked!.");
        }
        if (order.getOrderType().equals(OrderType.BUY)) {
            buyOrders.add(order);
        } else {
            sellOrders.add(order);
        }
        crossOrder(order);
    }

    @Override
    public boolean lockOrderBook() {
        return lockingStrategy.lock();
    }

    @Override
    public void unlockOrderBook() {
        lockingStrategy.unlock();
    }

    @Override
    public String getProductId() {
        return productId;
    }

    private void crossOrder(Order currentOrder) {
        if (currentOrder.getOrderType().equals(OrderType.BUY)) {
            crossWithSellOrders(currentOrder);
        } else {
            
        }
    }

    private void crossWithSellOrders(Order buyOrder) {
        long cumQty = buyOrder.getQty();
        List<Order> matchingOrders = CollectionUtil.newArrayList();
        for (Order targetOrder : sellOrders) {
            if (buyOrder.getPrice() == targetOrder.getPrice()) {
                // if price is right, amend cumQty to be initial - current target Qty
                if(cumQty == targetOrder.getQty()) {
                    // buyOrder fulfilled. targetOrder fully fill
                    buyOrder.setOrderStatus(OrderStatus.FILL);
                    targetOrder.setOrderStatus(OrderStatus.FILL);
                    matchingOrders.add(buyOrder);
                    matchingOrders.add(targetOrder);
                    buyOrders.remove(buyOrder);
                    sellOrders.remove(targetOrder);
                    break;
                } else if(cumQty < targetOrder.getQty()) {
                    // buyOrder fulfilled, targetOrder partially Filled
                    cumQty -= targetOrder.getQty();
                    buyOrder.setOrderStatus(OrderStatus.FILL);
                    targetOrder.setQty(targetOrder.getQty() - buyOrder.getQty());
                    Order newOrder =
                            createPartialFillOrder(targetOrder, buyOrder.getQty());
                    matchingOrders.add(buyOrder);
                    matchingOrders.add(newOrder);
                    buyOrders.remove(buyOrder);
                    break;
                }

            }

        }
        match(matchingOrders);
    }

    private void crossWithBuyOrder(Order sellOrder) {
    }

  

    private void match(List<Order> matchOrders) {
        for(Order order : matchOrders ) {
            notifyObservers(order);
        }
    }

    private Order createPartialFillOrder(Order targetOrder, long newQty) {
        Order order =
                new OrderImpl(targetOrder.getTimestamp(), newQty,
                    targetOrder.getSecurityID(),
                    targetOrder.getOrderType(),
                    targetOrder.getTouchCount(),
                    targetOrder.getOrderId(), targetOrder.getPrice());
        order.setOrderStatus(OrderStatus.PARTIAL_FILL);
        return order;
    }


}
