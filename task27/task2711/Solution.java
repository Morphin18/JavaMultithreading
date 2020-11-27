package com.javarush.task.task27.task2711;

import java.util.concurrent.CountDownLatch;

/* 
CountDownLatch
*/

public class Solution {

    public CountDownLatch latch = new CountDownLatch(1);


    public static void main(String[] args) {

    }

    public void someMethod() throws InterruptedException {

        latch.countDown();
        latch.await();


        retrieveValue();


    }

    void retrieveValue() {
        System.out.println("Value retrieved.");
    }
}
