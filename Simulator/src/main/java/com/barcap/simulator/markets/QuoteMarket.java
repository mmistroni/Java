/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.markets;

import java.util.List;

/**
 *
 * @author marco
 */
public interface QuoteMarket extends Market{

    public void createQuoteRequest(String productId);

    public List<String> getQuotedProducts();

    public void startQuoteWorker(String productId, long qty, long delay);


}
