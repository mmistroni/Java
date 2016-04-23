/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.oms;

import java.sql.Timestamp;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class OrderImpl implements Order {

    private final Timestamp timestamp;
    private long qty;
    private final String securityId;
    private final OrderType orderDirection;
    private int touchCount = 0;
    private final OrderId orderId;
    private final double price;
    private OrderStatus orderStatus = OrderStatus.NEW;


    public OrderImpl(Timestamp timestamp, long qty, String securityId,
            OrderType orderDirection, int touchCount,
            OrderId orderId, double price) {
        this.orderDirection = orderDirection;
        this.qty =  qty;
        this.securityId = securityId;
        this.timestamp =  timestamp;
        this.touchCount = touchCount;
        this.orderId = orderId;
        this.price = price;
    }

    @Override
    public Timestamp getTimestamp() {
        return timestamp;
    }

    @Override
    public long getQty() {
        return qty;
    }

    @Override
    public String getSecurityID() {
        return securityId;
    }

    @Override
    public OrderType getOrderType() {
        return orderDirection;
    }

    @Override
    public void touch() {
        touchCount++;        
    }

    @Override
    public int getTouchCount() {
        return touchCount;
    }

    
    @Override
    public OrderId getOrderId() {
        return orderId;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderImpl other = (OrderImpl) obj;
        if (this.timestamp != other.timestamp && (this.timestamp == null || !this.timestamp.equals(other.timestamp))) {
            return false;
        }
        if (this.qty != other.qty) {
            return false;
        }
        if ((this.securityId == null) ? (other.securityId != null) : !this.securityId.equals(other.securityId)) {
            return false;
        }
        if (this.orderDirection != other.orderDirection && (this.orderDirection == null || !this.orderDirection.equals(other.orderDirection))) {
            return false;
        }
        if (this.touchCount != other.touchCount) {
            return false;
        }
        if (this.orderId != other.orderId && (this.orderId == null || !this.orderId.equals(other.orderId))) {
            return false;
        }
        if (this.price != other.price) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.timestamp != null ? this.timestamp.hashCode() : 0);
        hash = 47 * hash + (int) (this.qty ^ (this.qty >>> 32));
        hash = 47 * hash + (this.securityId != null ? this.securityId.hashCode() : 0);
        hash = 47 * hash + (this.orderDirection != null ? this.orderDirection.hashCode() : 0);
        hash = 47 * hash + this.touchCount;
        hash = 47 * hash + (this.orderId != null ? this.orderId.hashCode() : 0);
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return "OrderImpl [" + "orderDirection " + orderDirection + " " + "orderId " + orderId + " " + "price " + price + " " + "qty " + qty + " " + "securityId " + securityId + " " + "timestamp " + timestamp + " " + "touchCount " + touchCount + "]";
    }

    public void setTimestamp(Timestamp timestamp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setQty(long qty) {
        this.qty = qty;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    


}
