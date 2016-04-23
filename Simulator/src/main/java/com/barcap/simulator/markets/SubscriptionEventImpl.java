/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.markets;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class SubscriptionEventImpl implements SubscriptionEvent {

    private String productId;

    public SubscriptionEventImpl(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }


}
