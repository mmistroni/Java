/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.markets;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
@ManagedResource(objectName = "services.markets:name=quoteMarketServiceJmx",
description = "Quote Market Service Jmx")
public class QuoteMarketJmx {

    private QuoteMarket quoteMarket;
    
    @Required
    public void setQuoteMarket(QuoteMarket quoteMarket) {
        this.quoteMarket = quoteMarket;
    }
    
    
    @ManagedOperation(description="View All Products")
    public String viewAllListedProducts() {
        return quoteMarket.getQuotedProducts().toString();
    }

    public void enterRandomManualQuote(String productId,
            long qty) {
        
    }



}
