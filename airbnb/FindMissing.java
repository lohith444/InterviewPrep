import java.io.*;
import java.util.*;
import java.math.BigInteger;

/*
 * Write a function that takes two parameters, 
 * as arguments, bigger and smaller arrays
 * and returns a missing element.
 *
 * Example:
 * bigger=[4, 3, 12, 8], smaller=[3, 4, 8]
 * return 12.
 *
 * Naive approach:
 *  For each element in bigger array, find if element 
 *  is present in smaller array. T:O(n*n) S:O(1)
 *
 * Use hash-map:
 *  Put element of smaller array to hash-map.
 *  For each element in bigger, check if key exists.
 *  The key which does not exist is the answer. T:O(n) S:O(n)
 *
 * Using sum:
 *  Add all the elements of bigger (sum1).
 *  Add all the elements of smaller (sum2).
 *  Subtract: result = sum1 - sum2.
 *  In most cases, this will be T:O(n) S:O(1)
 *  For larger integers, sum will overflow and need big_int
 *  in which case, S: O(n);
 *
 * Using xor:
 *  Result is XOR of all the elements. T:O(n) S:O(1)  
 */

class Solution {
  public static Integer findMissing1(int[] bigger, int[] smaller) {
    Map<Integer, Integer> mp = new HashMap<>();
    for(int i=0; i<smaller.length; i++) {
      Integer elem = smaller[i];
      mp.put(elem, 1);
    }
    
    for(int i=0; i<bigger.length; i++) {
      Integer elem = bigger[i];
      if (!mp.containsKey(elem)) {
        return elem;
      }
    }
    return Integer.MIN_VALUE;
  }
  
  public static Integer findMissing2(int[] bigger, int[] smaller) {
    int xor_val = bigger[0];
    for(int i=1; i<bigger.length; i++) {
      xor_val ^= bigger[i];
    }
    for(int j=0; j<smaller.length; j++) {
      xor_val ^= smaller[j];
    }
    return xor_val;
  }
  
  public static void main(String[] args) {
    int[] bigger = {4, 3, 12, 8};
    int[] smaller = {3, 4, 8};
    System.out.println("findMissing using Map: " + findMissing1(bigger, smaller));
    System.out.println("findMissing using XOR: " + findMissing2(bigger, smaller));
  }
}
