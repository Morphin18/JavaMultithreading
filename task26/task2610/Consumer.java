package com.javarush.task.task26.task2610;

import com.javarush.task.task30.task3003.ShareItem;

import java.util.concurrent.TransferQueue;

public class Consumer implements Runnable {
    private TransferQueue<ShareItem
            > queue;

    public Consumer(TransferQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            Thread.sleep(450);
            while (true) {
                ShareItem take = queue.take();
                System.out.format("Processing " + take.toString() + "%n");
            }
        } catch (InterruptedException e) {

        }
    }
}
