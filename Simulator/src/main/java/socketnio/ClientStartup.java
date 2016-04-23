/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package socketnio;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import socketnio.client.ClientController;

/**
 *
 * @author marco
 */
public class ClientStartup {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"classpath:spring-client.xml"});
        
        ClientController controller = (ClientController) context.getBean("controller");
        controller.start();
    }

}
