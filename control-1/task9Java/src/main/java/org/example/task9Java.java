package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

final class Stock {
    private final String name;
    private final AtomicInteger price;

    public Stock(String name, int price) {
        this.name = name;
        this.price = new AtomicInteger(price);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price.get();
    }

    public void increasePrice() {
        price.incrementAndGet();
    }

    public void decreasePrice() {
        price.decrementAndGet();
    }
}

final  class Broker implements Runnable {
    private Stock stock;
    private Semaphore semaphore;
    private Random random = new Random();

    public Broker(Stock stock, Semaphore semaphore) {
        this.stock = stock;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            if (random.nextBoolean()) {
                buyStock();
            } else {
                sellStock();
            }
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void buyStock() {
        int price = stock.getPrice();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Куплено акції " + stock.getName() + " за ціною " + price);
        stock.increasePrice();
    }

    private void sellStock() {
        int price = stock.getPrice();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Продано акції " + stock.getName() + " за ціною " + price);
        stock.decreasePrice();
    }

    public Stock getStock() {
        return stock;
    }
}


final class Exchange {
    private final List<Broker> brokers = new ArrayList<>();
    private final CyclicBarrier barrier;
    private final AtomicInteger exchangeIndex = new AtomicInteger(0);

    // Порогове значення індексу біржі
    private final int THRESHOLD = 500;

    public Exchange(List<Stock> stocks) {
        Semaphore semaphore = new Semaphore(stocks.size());
        for (Stock stock : stocks) {
            brokers.add(new Broker(stock, semaphore));
        }
        barrier = new CyclicBarrier(brokers.size(), this::calculateExchangeIndex);
    }

    public void startTrading() throws InterruptedException {
        do {
            ExecutorService executorService = Executors.newFixedThreadPool(brokers.size());
            for (Broker broker : brokers) {
                executorService.submit(broker);
            }
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.DAYS);

            this.calculateExchangeIndex();
        }
        while (exchangeIndex.get() > THRESHOLD);
        System.out.println("Торги призупинено, індекс біржі: " + exchangeIndex);
    }

    private void calculateExchangeIndex() {
        int sum = 0;
        for (Broker broker : brokers) {
            sum += broker.getStock().getPrice();
        }
        exchangeIndex.set(sum / brokers.size());
        System.out.println("Індекс біржі: " + exchangeIndex);
    }


    public static void main(String[] args) throws InterruptedException {
        List<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock("Google", 100));
        stocks.add(new Stock("Apple", 200));
        stocks.add(new Stock("Microsoft", 300));
        stocks.add(new Stock("Amazon", 400));
        stocks.add(new Stock("Facebook", 500));
        stocks.add(new Stock("Tesla", 600));
        stocks.add(new Stock("Intel", 700));
        stocks.add(new Stock("Oracle", 800));
        stocks.add(new Stock("IBM", 900));
        stocks.add(new Stock("HP", 1000));

        Exchange exchange = new Exchange(stocks);
        exchange.startTrading();
    }
}

