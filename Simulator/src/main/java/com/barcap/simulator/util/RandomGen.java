/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.util;

import java.util.Random;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class RandomGen {

    public static void main(String... args) {
        Random randGen = new Random();
        for(int i = 0; i < 10; i++) {
                System.err.println(randGen.nextDouble());
        }
    }


}
