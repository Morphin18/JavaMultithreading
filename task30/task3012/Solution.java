package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
    }

    public void createExpression(int number) {
        //напишите тут ваш код
        StringBuilder result = getNumber(new StringBuilder(), number, 0);

        System.out.println(number + " = " + result);
    }
    public StringBuilder getNumber(StringBuilder result, int number, int pow) {
        if (number == 1)
            return result.append("+ ").append((int)Math.pow(3, pow));

        int remains = number % 3;
        int division = number/3;

        if (remains == 1) {
            result.append("+ ").append((int)Math.pow(3, pow)).append(" ");
            return getNumber(result, division, ++pow);
        }

        if (remains == 2) {
            result.append("- ").append((int)Math.pow(3, pow)).append(" ");
            return getNumber(result, ++division, ++pow);
        }

        if (remains == 0)
            return getNumber(result, division, ++pow);

        throw new IllegalArgumentException();
    }
}