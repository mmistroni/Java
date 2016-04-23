/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barcap.simulator.markets;

import com.barcap.simulator.exchange.ExchangeEvent;
import com.barcap.simulator.threading.ThreadService;
import com.barcap.simulator.util.CollectionUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * Copyright @ WorldcorpServices Ltd.
 * @author marco
 */
@ManagedResource(objectName = "services.markets:name=quoteMarketService",
description = "Quote Market Service")
public class QuoteMarketImpl implements QuoteMarket {

    private Set<MarketListener> marketListeners = CollectionUtil.newHashSet();
    private List<String> productList = CollectionUtil.newArrayList();
    private ThreadService threadService;
    private static final Log LOGGER = LogFactory.getLog(QuoteMarketImpl.class);
    private static final String PRICE_EVENT = "productId:%s;bid=%d;ask=%d;bidQty=%d;askQty=%d";
    private Random randomPx = new Random(100);
    private Random randomQty = new Random(30);
    private Map<String, Future<Void>> priceEventRunners =
            new HashMap<String, Future<Void>>();

    @Required
    public void setThreadService(ThreadService threadService) {
        this.threadService = threadService;
    }

    @Override
    public void addMarketListener(MarketListener marketListener) {
        marketListeners.add(marketListener);
    }

    @Override
    public void removeMarketListenr(MarketListener marketListener) {
        marketListeners.remove(marketListener);
    }

    @Override
    public void addSubscriptionEvent(SubscriptionEvent event) {
        productList.add(event.getProductId());
    }

    @Override
    public void removeSubscriptionEvent(SubscriptionEvent event) {
        productList.remove(event.getProductId());
    }

    @Override
    public void createQuoteRequest(String productId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void handleEvent(ExchangeEvent exchangeEvent) {
        LOGGER.info("Handling events... here will be only orders...");
    }

    @ManagedOperation(description = "notifySubscribers")
    public void manualNotifySubscribers() {
        for (String product : productList) {
            PriceEvent priceEvent =
                    generateRandomPriceEvent(product);
            notifySubscribers(priceEvent);
        }
    }

    private void notifySubscribers(PriceEvent priceEvent) {
        for (MarketListener listener : marketListeners) {
            listener.update(priceEvent);
        }
    }

    @ManagedOperation(description = "View All Products")
    public String viewQuotedProducts() {
        return productList.toString();
    }

    @ManagedOperation(description = "View Registered Listeners")
    public String viewRegisteredListener() {
        return marketListeners.toString();
    }

    @ManagedOperation(description = "start PriceEvent runner")
    @ManagedOperationParameters({
        @ManagedOperationParameter(name = "productId",
        description = "PriceEvent random generator")})
    public void startPriceEventWorker(String productId) throws IllegalArgumentException {
        if (!productList.contains(productId)) {
            throw new IllegalArgumentException("Invalid productId:" + productId);
        }
        Future<Void> runner = threadService.scheduleRepetitiveTask(
                new PriceEventRunner(productId), 1000, 10000);
        priceEventRunners.put(productId, runner);
    }

    @ManagedOperation(description = "killRunner")
    @ManagedOperationParameters({
        @ManagedOperationParameter(name = "productId",
        description = "Remove a random price event generator")})
    public void killRunner(String productId) {
        Future<Void> runner = priceEventRunners.get(productId);
        runner.cancel(true);
        priceEventRunners.remove(productId);
    }

    @ManagedOperation(description = "viewProductRunners")
    public String viewProductRunners() {
        return priceEventRunners.keySet().toString();
    }

    @Override
    public void startQuoteWorker(String productId, long qty, long delay) {
        LOGGER.info("Starting quote worker....");
    }

    private PriceEvent generateRandomPriceEvent(String productId) {
        int bidPx = randomPx.nextInt();
        int askPx = randomPx.nextInt();
        int askQty = randomQty.nextInt();
        int bidQty = randomQty.nextInt();

        return new PriceEventImpl(String.format(PRICE_EVENT,
                productId, bidPx, askPx, bidQty, askQty));
    }

    public List<String> getQuotedProducts() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private class PriceEventRunner implements Runnable {

        private String productId;

        PriceEventRunner(String productId) {
            this.productId = productId;
        }

        public void run() {
            PriceEvent event = generateRandomPriceEvent(productId);
            notifySubscribers(event);

        }
    }


}
