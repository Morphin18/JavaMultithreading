package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.StandardCharsets;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream(args[0]);
             FileOutputStream fos = new FileOutputStream(args[1])) {
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            String s = new String(buffer, "Windows-1251");
            buffer = s.getBytes(StandardCharsets.UTF_8);
            fos.write(buffer);
        } catch (IOException ignored) {

        }
    }
}
