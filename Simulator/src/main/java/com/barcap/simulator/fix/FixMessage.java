/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barcap.simulator.fix;

import java.util.Map;

/**
 *
 * @author marco
 */
public interface FixMessage {

    public Map<FixTag,Object> getFixTagMap();

    public void addTag(FixTag fixTag, Object value);

    public FixMessageType getMessageType();

    public Object getFixTag(FixTag fixTag);


}
