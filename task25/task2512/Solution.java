package com.javarush.task.task25.task2512;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* 
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Throwable th = e;
        if (th != null) {
            uncaughtException(t, th.getCause());
            t.interrupt();
            System.out.println(th);
        }
    }

    public static void main(String[] args) {
        new Solution().uncaughtException(Thread.currentThread(), new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI"))));
    }
}
