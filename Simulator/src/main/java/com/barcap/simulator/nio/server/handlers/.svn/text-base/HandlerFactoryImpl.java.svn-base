/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.nio.server.handlers;

import java.nio.channels.SocketChannel;
import org.springframework.beans.factory.annotation.Required;
import com.barcap.simulator.fix.FixAdapterFactory;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
class HandlerFactoryImpl implements HandlerFactory{

    private FixAdapterFactory fixAdapterFactory;
    

    @Required
    public void setFixAdapterFactory(FixAdapterFactory fixFacadeFactory) {
        this.fixAdapterFactory = fixFacadeFactory;
    }




    public SocketHandler createHandler(SocketChannel socketChannel) {
        return new SocketHandlerImpl(socketChannel,
                fixAdapterFactory.createFacade(socketChannel));
    }






}
