package com.javarush.task.task25.task2511;

import java.util.Arrays;
import java.util.TimerTask;

/* 
Вооружаемся до зубов!
*/
public class Solution extends TimerTask {
    protected final Thread.UncaughtExceptionHandler handler;
    protected TimerTask original;

    public Solution(TimerTask original) {
        if (original == null) {
            throw new NullPointerException();
        }
        this.original = original;
        this.handler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                String mask = "";
                for (int i = 0; i < t.getName().length(); i++) {
                    mask += "*";
                }
                System.out.println(String.format("%s",e.getMessage().replaceAll(t.getName(),mask)));
            }
        };    //init handler here
    }

    public static void main(String[] args) {
        Solution solution = new Solution(new TimerTask() {
            @Override
            public void run() {
                throw new ArithmeticException();
            }
        });
        Thread thread = new Thread(solution);
        thread.start();
    }

    public void run() {
        try {
            original.run();
        } catch (Throwable cause) {
            Thread currentThread = Thread.currentThread();
            handler.uncaughtException(currentThread, new Exception("Blah " + currentThread.getName()  + " blah-blah-blah", cause));
        }
    }

    public long scheduledExecutionTime() {
        return original.scheduledExecutionTime();
    }

    public boolean cancel() {
        return original.cancel();
    }
}