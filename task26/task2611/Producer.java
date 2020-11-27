package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;

public class Producer implements Runnable {
    private ConcurrentHashMap<String, String> map;

    public Producer(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }

    public void run() {
        int i = 1;
        try {
            while (true) {
                map.put(String.valueOf(i), String.format("Some text for %d", i));
                Thread.sleep(500);
                i++;
            }
        } catch (InterruptedException e) {
            System.out.println(String.format("[%s] thread was terminated", Thread.currentThread().getName()));
        }
    }
}
