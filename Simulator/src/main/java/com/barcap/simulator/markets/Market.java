/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.markets;

import com.barcap.simulator.exchange.ExchangeEvent;

/**
 *
 * @author marco
 */
public interface Market {

    public void addMarketListener(MarketListener marketListener);

    public void removeMarketListenr(MarketListener marketListener);

    public void addSubscriptionEvent(SubscriptionEvent event);

    public void removeSubscriptionEvent(SubscriptionEvent event);

    public void handleEvent(ExchangeEvent  exchangeEvent);


}
