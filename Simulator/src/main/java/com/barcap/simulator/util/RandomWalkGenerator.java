/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barcap.simulator.util;

import java.util.Random;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class RandomWalkGenerator {

    private double bidPx = 132.22;
    private double askPx = 132.22;
    private double move = 0.02;
    private int bidQty = 100;
    private int askQty = 100;
    private double lastPx;
    private int lastQty;

    public double getLastPx() {
        return lastPx;
    }

    public int getLastQty() {
        return lastQty;
    }
    private final Random randomGen = new Random();
    private final static Log LOGGER = LogFactory.getLog(RandomWalkGenerator.class);
    private final static String MDM_STR =
            "bidPx:%f;bidQty:%d;askPx:%f;askQty:%d;lastPx:%f;lastQty:%d";

    public double getAskPx() {
        return askPx;
    }

    public int getAskQty() {
        return askQty;
    }

    public double getBidPx() {
        return bidPx;
    }

    public int getBidQty() {
        return bidQty;
    }

    public void generateRandomWalk() {
        LOGGER.info("Starting random walk..");
        double midPx = ((askQty * askPx) + (bidQty * bidPx)) / (askQty + bidQty);

        double price = 0;
        int randomQty = randomGen.nextInt(bidQty);

        randomQty = randomQty > 0 ? randomQty : 100;

        double randomDbl = randomGen.nextDouble();

        System.err.println("midPx:" + midPx);
        System.err.println("randomQty:" + randomQty);

        double change = ((randomDbl - 0.5) * move);

        price = midPx * (1.0 + change);

        if (change > 0) {
            askPx = price;
            askQty = randomQty;
        } else {
            bidPx = price;
            bidQty = randomQty;
        }

        lastPx = price;
        lastQty = randomQty;


    }
    public static void main(String... args) throws Exception {
        RandomWalkGenerator gen = new RandomWalkGenerator();

        for (int i = 0; i
                < 20; i++) {
            gen.generateRandomWalk();
            System.err.println(String.format(
                    MDM_STR,
                    gen.getBidPx(), gen.getBidQty(), gen.getAskPx(),
                    gen.getAskQty(), gen.getLastPx(), gen.getLastQty()));


        }
    }
}
