/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix;

import java.nio.channels.SocketChannel;

/**
 *
 * @author marco
 */
public interface FixAdapterFactory {

    public FixAdapter createFacade(SocketChannel socketChannel);

}
