/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package socketnio.client;

import com.barcap.simulator.util.ui.AbstractView;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import socketnio.client.util.FixMessageCreator;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
public class ClientControllerImpl implements ClientController {
    private AbstractView<String> view;
    private SocketModel model;
    private static final Log LOGGER =
            LogFactory.getLog(ClientControllerImpl.class);

    public void init() throws Exception {
        view = new ClientFrame(this);
        model = new SocketModel();
        model.addObserver(view);
       
    }



    public void sendQuote() {
        LOGGER.info("GUI sent:");
        String quoteMsg = FixMessageCreator.createQuoteMessage();

        //model.setDataFromServer("FromServer:" + message);
    }

    public void start() {
         view.displayGUI();
    }

    public void sendOrder() {
        String quoteMsg = FixMessageCreator.createOrderMessage();
    }

    public void login() {
        String loginMessage = FixMessageCreator.createFixMessage();
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void logout() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
