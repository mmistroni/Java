/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.markets;

import com.barcap.simulator.oms.Order;
import com.barcap.simulator.util.Observable;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public interface SingleOrderBook extends Observable<PriceEvent>{

    public void addOrder(Order order);

}
