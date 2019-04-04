import java.io.*;
import java.util.*;

/*
Write a function that returns two missing numbers from the input array of sequence from 1 to n.

Example:
input = [1, 2, 5, 4, 7]
output = [3, 6]

Idea: Assumption: Sum does not overflow.
- Sum of missing numbers: 3+6 = 9
- Always one number will be less than 9/2 and the other will be greater than 9/2.
- Find the xor of all the numbers less than 9/2 from the given array and as well actual array.
  totalLeftXor = 1^2^3^4 (all numbers less than or equal to 4)
  arrLeftXor = 1^2^4
- Find the xor of all the numbers greater than 9/2 from the given array and as well actual array.
  totalRightXor = 5^6^7 (all numbers greater than 4)
  arrRightXor = 5^7
- Missing numbers: {totalLeftXor ^ arrLeftXor, totalRightXor ^ arrRightXor) = {3, 6} 
*/

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static int[] findTwoMissing(int[] arr) {
    int size = arr.length + 2; //two missing
    long totalArraySum = size * (size + 1) / 2;
    
    long arrSum = 0;
    for(int i : arr) arrSum += i;
    
    int pivot = (int) ((totalArraySum - arrSum) / 2);
    
    int totalLeftXor = 0;
    int arrLeftXor = 0;
    int totalRightXor = 0;
    int arrRightXor = 0;
    
    for(int i=1; i<=pivot; i++) totalLeftXor ^= i;
    for(int i=pivot+1; i <= size; i++) totalRightXor ^= i;
    
    for(int i: arr) {
      if (i <= pivot) arrLeftXor ^= i;
      else arrRightXor ^= i;
    }
    
    return new int[]{totalLeftXor ^ arrLeftXor, totalRightXor ^ arrRightXor};
  }
  
  public static void printArr(int[] arr) {
    System.out.print("[");
    for (int i=0; i<arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.print("]");
  }
  
  public static void main(String[] args) {
    int[] arr = new int[]{1, 2, 5, 4, 7};
    System.out.println("Input array: ");
    printArr(arr);
    
    System.out.println();
    
    int[] res = findTwoMissing(arr);
    System.out.println("Missing elements:");
    printArr(res);
  }
}
