package com.javarush.task.task22.task2208;

import java.util.LinkedHashMap;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
        Map<String, String> data = new LinkedHashMap<>();
        data.put("name", "Ivanov");
        data.put("country", "Ukraine");
        data.put("city", "Kiev");
        data.put("age", "10");
        System.out.println(getQuery(data));
    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry <String, String> entry : params.entrySet()){
            if ((entry.getKey() != null) && (entry.getValue() != null) ){
                if (sb.toString().equals("")){
                    sb.append(entry.getKey());
                    sb.append(" = ");
                    sb.append("'" + entry.getValue() + "'");
                } else {
                    sb.append(" and ");
                    sb.append(entry.getKey());
                    sb.append(" = ");
                    sb.append("'" + entry.getValue() + "'");

                }
            } else {
                sb.append("");
            }
        }

        return sb.toString();
    }
}
