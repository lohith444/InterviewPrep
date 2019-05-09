import java.util.*;
import java.io.*;

public class Solution {
    public static final int[] values = new int[]{
        1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
    };
    public static final String[] numerals = new String[]{
        "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
    };
    public static String intToRoman(int num) {
        StringBuilder numeral = new StringBuilder();
        int i = 0;
        
        while(num > 0) {
            if (num - values[i] >= 0) {
                numeral.append(numerals[i]);
                num -= values[i];
            } else {
                i++;
            }
        }
        return numeral.toString();
    }
    
    public static void main(String[] args) {
        int val = 49;
        System.out.println("val: " + val + ", roman: " + intToRoman(val));
    }
}
