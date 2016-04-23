/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix.session;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
@Ignore
public class SessionMessageHandlerTest {
    private SessionMessageHandlerImpl sessionMessageHandlerImpl =
            new SessionMessageHandlerImpl();

    @Test
    public void testGetSupportedTypes() throws Exception {
        Assert.assertEquals(6,
                sessionMessageHandlerImpl.getSupportedTypes().size());
    }

    @Test
    public void testOnFixMessage() throws Exception {
        Assert.fail("to be implemented..");
    }







}
