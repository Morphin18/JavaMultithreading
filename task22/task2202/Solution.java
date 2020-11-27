package com.javarush.task.task22.task2202;

import java.util.TooManyListenersException;

/*
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) throws Exception {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null || string.isEmpty()) throw new TooShortStringException();
        String[] strings = string.split(" ");
        StringBuilder sb = new StringBuilder();
        if (strings.length >= 5) {
            for (int i = 1; i < 5; i++) {
                sb.append(strings[i] + " ");
            }
        } else throw new TooShortStringException();

        return sb.toString().trim();
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
