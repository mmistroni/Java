/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.oms;

/**
 *
 * @author marco
 */
public interface OrderBook extends com.barcap.simulator.util.Observable<Order> {


    public boolean lockOrderBook();

    public void enterOrder(Order order) throws IllegalStateException;

    public void unlockOrderBook();

    public String getProductId();

    public Order[] getBuyOrders();
    
    public Order[] getSellOrders();

}
