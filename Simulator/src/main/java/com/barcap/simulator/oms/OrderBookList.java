/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.oms;

/**
 *
 * @author marco
 */
public interface OrderBookList {

    public void addOrder(Order order);

    public Order getOrder();

}
