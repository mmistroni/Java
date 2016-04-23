/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socketnio;

import com.barcap.simulator.markets.Market;
import com.barcap.simulator.markets.MarketListener;
import com.barcap.simulator.markets.PriceEvent;
import com.barcap.simulator.markets.SubscriptionEventImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.barcap.simulator.nio.server.Startable;
import com.barcap.simulator.threading.ThreadService;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author marco
 */
public class ServerStartup {

    /**
     * @param args the command line arguments
     */
    private BufferedReader reader;
    private PrintWriter writer;
    private MarketListener mdmListener = new MDMMarketListener();
    private Market market;
    private static final Log LOGGER = LogFactory.getLog(ServerStartup.class);
    private ThreadService threadService;
    private Future<Void> futureTask;
    private CountDownLatch latch = new CountDownLatch(1);



    public ServerStartup() throws Exception {
        initializeContext();
        initializeStreams();
        latch.await();
        startReader();
    }

    private void initializeContext() throws Exception {
        new Thread(new ApplicationContextThread()).start();
        //futureTask = threadService.execute(new ApplicationContextThread());
    }

    private void initializeStreams() throws Exception {
        reader = new BufferedReader(
                new InputStreamReader(System.in));
        writer = new PrintWriter(System.out, true);
    }

    private void startReader() throws Exception {
        String line = "";
        try {
            writer.println("FakeMdm ready for transmission....");
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(":")) {
                    market.addSubscriptionEvent(new SubscriptionEventImpl(line));
                } else{
                     printData(line);
                }
            }

        } catch (Exception e) {
            LOGGER.error("Exception in reading..", e);
        }
        
    }

    private void printData(String line) {
        try {
            writer.println("Invalid command:" + line);
        } catch(Exception e) {
            writer.close();
        }
    }


    public static void main(String[] args) throws Exception {
        ServerStartup mdmServer = new ServerStartup();


        /*
        ApplicationContext context = new ClassPathXmlApplicationContext(
        new String[]{"classpath:spring-config.xml",
        "classpath:spring-fix.xml",
        "classpath:spring-market.xml",
        "classpath:spring-thread.xml",
        "classpath:spring-jmx.xml"});
        //Startable server = (Startable) context.getBean("server");
        //server.startup();



        BufferedReader reader = new BufferedReader(
        new InputStreamReader(System.in));
        String line = "";
        PrintWriter printWriter = new PrintWriter(System.out, true);

        while ((line = reader.readLine()) != null) {


        printWriter.println("recv:" + line);
        }
        printWriter.close();
        System.exit(0);

         */
    }

    class ApplicationContextThread implements Runnable {
        public void run() {
            try {
                ApplicationContext context = new ClassPathXmlApplicationContext(
                    new String[]{"classpath:spring-config.xml",
                        "classpath:spring-fix.xml",
                        "classpath:spring-market.xml",
                        "classpath:spring-thread.xml",
                        "classpath:spring-jmx.xml"});
                    market = (Market) context.getBean("quoteMarket");
                    market.addMarketListener(mdmListener);
                    threadService = (ThreadService) context.getBean("threadService");
                    // countdown only when it's ready..
                    latch.countDown();
            } catch(Exception e) {
                LOGGER.warn("interrupted excepiton while running thread...");
            }
        }
    }


    class MDMMarketListener implements MarketListener {

        public void update(PriceEvent observableData) {
            try {
                writer.println(observableData.getPriceEvent());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class MdmReader implements Runnable {

        public void run() {
            try {
                latch.await();
                String line = "";
                try {
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith(":")) {
                            market.addSubscriptionEvent(new SubscriptionEventImpl(line));
                            writer.println("Subscribed to:" + line);
                        } else {
                            if(line.equalsIgnoreCase("bye")) {
                                futureTask.cancel(true);
                                System.exit(0);
                            }
                            
                        }
                    }
                    
                } catch (Exception e) {
                    LOGGER.error("Exception in reading..", e);
                }
            } catch(InterruptedException ex) {
                LOGGER.warn("Interrupted Exception..");
            }
        }
    }
}
