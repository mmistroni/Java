/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.oms;

/**
 *
 * @author marco
 */
public interface OrderCancelReject {

    public String getOrderID();

    public String getClOrdID();

    public OrderStatus getOrderStatus();

    public String getCxlRejReason();


}
