import java.io.*;
import java.util.*;

/*
Write a function that takes array as input and returns
contiguous sub-array that sum upto zero.

Example:
input = [-1, 2, 5, -3, -4, 2]
output = [2, 5, -3, -4]

Approach:
 - Keep summing with the previous items and store in hashmap.
   input = [-1,   2,    5,  -3,   -4,   2]
             0  0+-1  -1+2  1+5  6+-3  3+-4 -1+2 
   sums  = [0:0,-1:1,  1:2, 6:3, 3:4,  -1]
 - When we find the same key in the sums hashmap, return sub-array from the found index to the current index. 
*/

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static Integer[] zeroSum(Integer[] arr) {
    Map<Integer, Integer> sums = new HashMap<Integer, Integer>();
    Integer sum = 0;
    for (Integer i=0; i <= arr.length; i++) {
      Integer old_index = sums.get(sum);
      if (old_index == null) {
        if (i == arr.length) {
          return new Integer[0];
        }
        sums.put(sum, i);
        sum += arr[i];
      } else {
        return Arrays.copyOfRange(arr, old_index, i);
      }
    }
    return new Integer[0];
  }
  
  public static void printArr(Integer[] arr) {
    System.out.print("[");
    for (Integer i=0; i<arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.print("]");
  }
  
  public static void main(String[] args) {
    Integer[] arr = new Integer[]{-1, 2, 5, -3, -6, 2};
    System.out.println("Input array: ");
    printArr(arr);
    
    System.out.println();
    
    Integer[] res = zeroSum(arr);
    System.out.println("Zero sum sub-array");
    printArr(res);
  }
}
