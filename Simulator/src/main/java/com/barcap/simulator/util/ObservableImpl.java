/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.util;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author marco
 */
public class ObservableImpl<T> implements Observable<T>
{
    private List<Observer<T>> observerList = CollectionUtil.newArrayList();
    private static final Log LOGGER = LogFactory.getLog(ObservableImpl.class);

    @Override
    public void addObserver(Observer<T> observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer<T> observer) {
        observerList.remove(observer);
    }

    public final void notifyObservers() {
        notifyObservers(null);
    }

    public final void notifyObservers(final T observerData) {
        for(Observer<T> observer: observerList) {
            observer.update(observerData);
        }
    }
    

}
