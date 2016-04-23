/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix;

import java.nio.channels.SocketChannel;
import org.springframework.beans.factory.annotation.Required;
import com.barcap.simulator.nio.server.config.SessionStaticDataManager;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class FixAdapterFactoryImpl implements FixAdapterFactory {

    private FixEngine fixService;

    @Required
    public void setFixEngine(FixEngine fixService) {
        this.fixService = fixService;
    }

    public FixAdapter createFacade(SocketChannel socketChannel) {
        String ipAddress = socketChannel.socket().getRemoteSocketAddress()
                .toString();
        return new FixAdapterImpl(fixService);
    }


}
