/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.oms;

import java.sql.Timestamp;

/**
 *
 * @author marco
 */
public interface ExecutionReport {

    public OrderStatus getOrdStatus();

    public ExecType getExecType();

    public String getOrderId();

    public String getClOrdId();

    public ExecTransType getExecTransType();

    public long getOrderQty();

    public double getPrice();

    public String getCurrency();

    public long getCumQty();

    public Timestamp getTransactTime();

    public Timestamp getTradeDate();



}
