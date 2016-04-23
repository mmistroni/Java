/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix.session;

import com.barcap.simulator.exchange.ClientEvent;
import com.barcap.simulator.oms.Order;

/**
 *
 * @author marco
 */
public interface FixOrderEvent extends  ClientEvent{

    public Order getOrder();
    
    

    

}
