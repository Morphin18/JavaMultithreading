package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        String s2="";
        if(string != null &&  string.indexOf('\t') != -1 && string.indexOf('\t', (string.indexOf('\t')+1)) != -1 ){
            s2 = string.substring((string.indexOf('\t')+1), (string.indexOf('\t', (string.indexOf('\t')+1))));
        }else{
            throw new TooShortStringException();
        }
        return s2;
    }
    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }

    public static class TooShortStringException extends Exception {
    }
}
