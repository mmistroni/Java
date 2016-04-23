/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package socketnio.client;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class SocketModel extends com.barcap.simulator.util.ui.AbstractModel<String>
{

    private static final Log LOGGER = LogFactory.getLog(SocketModel.class);

    public void setDataFromServer(String dataFromServer)
    {
        LOGGER.info("NOtifying observer with:" + dataFromServer);
        notifyObservers(dataFromServer);
    }


    public String getServerData() {
        return null;
    }


}
