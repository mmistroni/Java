/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix.session;

import com.barcap.simulator.exchange.ExchangeEvent;
import com.barcap.simulator.fix.FixMessage;

/**
 *
 * @author marco
 */
public interface ApplicationMessageConverter {

    public ExchangeEvent toClientEvent(FixMessage fixMessage);
    
    public FixMessage fromExchangeEvent(ExchangeEvent exchangeEvent);

}
