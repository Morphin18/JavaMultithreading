package com.javarush.task.task22.task2210;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(Arrays.asList(getTokens("level22.lesson13.task01", ".")));
    }

    public static String[] getTokens(String query, String delimiter) {
        StringTokenizer tokenizer = new StringTokenizer(query, delimiter);
        String[] token = new String[tokenizer.countTokens()];
        int i = 0;
        while (tokenizer.hasMoreTokens()) {
            token[i] = tokenizer.nextToken();
            i++;
        }
        return token;
    }
}
