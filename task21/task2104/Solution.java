package com.javarush.task.task21.task2104;

import java.util.HashSet;
import java.util.Set;

/* 
Equals and HashCode
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Donald", "Duck"));
        System.out.println(s.contains(new Solution("Donald", "Duck")));
    }

    public boolean equals(Object obj) {
        if (obj instanceof Solution) {
            if (this == obj) {
                return true;
            }
            if (this == null || getClass() != obj.getClass()) {
                return false;
            }
            Solution other = (Solution) obj;
            if (first != null ? !first.equals(other.first) : other.first != null) return false;
            if (last != null ? !last.equals(other.last) : other.last!= null) return false;
        }
        return true;
    }

    public int hashCode() {
        int result = 1;
        if (first == null || last == null) {
            return 0;
        }
        result = 31 * result + first.hashCode();
        result = 31 * result + last.hashCode();
        return result;
    }
}
