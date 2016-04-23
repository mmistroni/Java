/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.exchange;

/**
 *
 * @author marco
 */
public enum ClientEventType {
    // Pre-Trade Requests
    QUOTE_REQUEST,
    QUOTE_CANCEL,
    QUOTE_STATUS_REQUEST,
    MASS_QUOTE,
    ORDER_REPLACE_REQUEST,
    ORDER_STATUS_REQUEST,
    NEW_ORDER_REQUEST,
    NEW_MASS_ORDER_REQUEST
}
