import java.io.*;
import java.util.*;

/*
Write a function that returns the duplicate elements in an array which contains integers in range 1 <= x <= arr.length.

Example:
input = [2, 1, 1, 2]
output = [1, 2]

Brute force: O(N*N) time. O(1) space
- For each index, find if there is a duplicate and add it to result array if duplicate found.

Using map: O(N) time, O(N) space.
- For each item, add to hashmap if does not exist.
- If found in hashmap, it is a duplicate, add it to result array.

Using sorting: O(N*logN) time, O(1) space.
- Sort the elements of the array.
- For each index, check the previous value and see if duplicate and add to result array.

Using encoding: O(N) time, O(1) space.
- For each item, find the corresponding index and set the value to negative.
  Example: If num: 2 is found, goto index = 2-1 = 1, and negate the value.
- If the number is already negative, add the number to result array.
 [2, 1, 1, 2] => (set - at index:1) [2, -1, 1, 2] => (set - at index:|-1|-1 = 0) [-2, -1, 1, 2] => (at index: 1-1=0, num is -, add to res) [-2, -1, 1, 2] res=[1] => (at index: 2-1=1, num is -, add to res) [-2, -1, 1, 2] res=[1,2]
*/

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static int[] findDup(int[] arr) {
    int[] res = new int[arr.length];
    int start = 0;
    for (int i=0; i<arr.length; i++) {
      int val = Math.abs(arr[i]);
      int index = val - 1;
      if(arr[index] >= 0) {
        arr[index] = -arr[index];
      } else {
        res[start++] = val; 
      }
    }
    return Arrays.copyOfRange(res, 0, start);
  }
  
  public static void printArr(int[] arr) {
    System.out.print("[");
    for (int i=0; i<arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.print("]");
  }
  
  public static void main(String[] args) {
    int[] arr = new int[]{1, 2, 1, 2};
    System.out.println("Input array: ");
    printArr(arr);
    
    System.out.println();
    
    int[] res = findDup(arr);
    System.out.println("Duplicate elements:");
    printArr(res);
  }
}
