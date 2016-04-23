/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.util.ui;


/**
 *
 * @author marco
 */
public interface AbstractView<T> extends com.barcap.simulator.util.Observer<T>
{

    /**
     * initializes workspace
     */
    public void init();

    /**
     * displays gui
     */
    public void displayGUI();

    /**
     * hides gui
     */
    public void hide();

}
