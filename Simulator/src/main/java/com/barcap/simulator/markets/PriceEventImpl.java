/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.markets;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class PriceEventImpl implements PriceEvent {

    private String priceEventString;

    public PriceEventImpl(String priceEventString) {
        this.priceEventString = priceEventString;
    }

    public String getPriceEvent() {
        return priceEventString;
    }


}
