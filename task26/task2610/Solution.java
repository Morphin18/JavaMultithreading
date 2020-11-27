package com.javarush.task.task26.task2610;

import com.javarush.task.task30.task3003.ShareItem;

import java.util.concurrent.*;

/* 
Мир скучен для скучных людей
*/

public class Solution {

    public static void main(String[] args) throws Exception {

        TransferQueue<ShareItem> queue = new LinkedTransferQueue<>();

        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));
        producer.start();
        consumer.start();

        Thread.sleep(1500);

        producer.interrupt();
        consumer.interrupt();

    }
}
