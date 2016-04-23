/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix.session;

import com.barcap.simulator.exchange.ExchangeEvent;

/**
 *
 * @author marco
 */
public interface ApplicationMessageHandler extends MessageHandler {

    public void processExchangeEvent(ExchangeEvent exchangeEvent);

}
