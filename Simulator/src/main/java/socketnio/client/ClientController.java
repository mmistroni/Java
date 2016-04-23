/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package socketnio.client;

/**
 *
 * @author marco
 */
public interface ClientController {

    public abstract void sendQuote();

    public abstract void sendOrder();
    
    public abstract void login();

    public abstract void logout();




    void start();

}
