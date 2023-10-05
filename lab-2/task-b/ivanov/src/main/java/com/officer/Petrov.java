package com.officer;

import java.util.concurrent.BlockingQueue;

public class Petrov implements Runnable {
        private BlockingQueue<Integer> storage;

    public Petrov(BlockingQueue<Integer> storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int item = storage.take(); // Take item from the storage, and block if storage is empty
                System.out.println("Petrov takes item " + item + " from the storage");
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
