package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/* 
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
        Integer[] a = new Integer[]{13, 8, 15, 5, 17};
        sort(a);
    }

    public static Integer[] sort(Integer[] array) {
        Arrays.sort(array);
        double median;
        if (array.length % 2 == 0) {
            median = ((double) array[array.length / 2] + (double) array[array.length / 2 - 1]) / 2;
        } else {
            median = (double) array[array.length / 2];
        }
        Comparator<Integer> comparator = new Comparator<Integer>() {

            @Override

            public int compare(Integer o1, Integer o2) {

                double a = Math.abs(median - o1);  //2

                double b = Math.abs(median - o2);  //3

                if (a > b) {

                    return 1;

                } else if (a < b) {

                    return -1;

                } else {

                    return o1<=o2 ? -1 : 1;

                }

            }

        };
        Arrays.sort(array,comparator);
        return array;
    }
}
