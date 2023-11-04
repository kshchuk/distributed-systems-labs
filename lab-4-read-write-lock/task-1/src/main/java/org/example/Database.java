package org.example;


import org.example.lock.ReadWriteLock;

import java.util.HashMap;
import java.util.Map;

public class Database {
    private final Map<String, String> data = new HashMap<>();
    private final ReadWriteLock lock = new ReadWriteLock();

    public String read(String name) throws InterruptedException {
        lock.lockRead();
        try {
            return data.get(name);
        } finally {
            lock.unlockRead();
        }
    }

    public String readByPhone(String phone) throws InterruptedException {
        lock.lockRead();
        try {
            return data.entrySet().stream()
                    .filter(entry -> phone.equals(entry.getValue()))
                    .map(Map.Entry::getKey)
                    .findFirst().orElse(null);
        } finally {
            lock.unlockRead();
        }
    }

    public void write(String name, String phone) throws InterruptedException {
        lock.lockWrite();
        try {
            data.put(name, phone);
        } finally {
            lock.unlockWrite();
        }
    }

    public void delete(String name) throws InterruptedException {
        lock.lockWrite();
        try {
            data.remove(name);
        } finally {
            lock.unlockWrite();
        }
    }
}