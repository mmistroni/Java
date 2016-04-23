/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.nio.server.handlers;

import java.nio.channels.SocketChannel;

/**
 *
 * @author marco
 */
public interface HandlerFactory {

    public SocketHandler createHandler(SocketChannel socketChannel);

}
