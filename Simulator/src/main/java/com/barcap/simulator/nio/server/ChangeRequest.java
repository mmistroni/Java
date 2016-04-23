/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.nio.server;

/**
 *
 * @author marco
 */
public enum ChangeRequest {
    REGISTER(1),
    CHANGEOPS(2);

    private int operationType;
    ChangeRequest(int operationType) {
        this.operationType = operationType;
    }

    public int getOperationType() {
        return operationType;
    }
}
