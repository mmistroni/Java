/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class FixParserFactoryImpl implements FixParserFactory {
    private final static FixParserFactory fixFactory = 
            new FixParserFactoryImpl();

    public static FixParserFactory getInstance() {
        return fixFactory;
    }


    public FixParser getFixParser() {
        return new SimpleFixParser();
    }



}
