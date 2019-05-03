import java.util.*;
import java.io.*;
/**
 * Write a function to find pow(A, B) % C for large numbers
 * 
 * e.g. 
 * input: pow(2, 3) % 5 
 * output: 3
 * 2^3 % 5 = 8 % 5 = 3
 * 
 * Idea:
 * (ab) mod c = ((a mod c) * (b mod c)) mod c
 * 
 * Time: O(logB)
 * */
public class Solution {
    public static int power(int a, int b, int c) {
        int x = a % c;
        int y = b;
        int res = 1;
        
        while(y > 0) {
            // if n is odd, multiply result.
            if ((y & 1) == 1) res = (res * x) % c;
            
            // shift n and update x.
            y = y >> 1;
            x = (x * x) % c;
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        System.out.println(power(50, 100, 13));
    }
}
