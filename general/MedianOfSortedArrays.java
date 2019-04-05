import java.io.*;
import java.util.*;

/*
Write a function that takes two equal length sorted arrays and finds the median.

Example:
arr1 = [2 4 6]
arr2 = [1 3 5]

median = 3.5 { [1 2 3 4 5 6] => (3+4)/2 => 3.5}

Brute force:
- Merge two arrays and then find the median. O(N)

Better way:
- Find the middle of arr1 (m1) and arr2 (m2).
  - If m1 and m2 are equal, then median is m1/m2.
  - If m1 > m2, then the median lies between first half of arr1 and second half of arr2.
  - If m1 < m2, then the median lies between first half of arr2 and second half of arr1.
  
Base case:
- if there are 2 elementes in each array.
 median = (max(arr1[0], arr2[0]) + min(arr1[1], arr2[1]))/2;
*/

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static Double medianSorted(Integer[] arr1, Integer[] arr2) {
    if (arr1.length != arr2.length) return null;
    if (arr1.length == 0) return null;
    if (arr1.length == 1) return Double.valueOf(arr1[0] + arr2[0])/2;
    if (arr2.length == 2) return Double.valueOf((Math.max(arr1[0], arr2[0]) + Math.min(arr1[1], arr2[1])))/2;
    
    int n = arr1.length;
    Double m1 = Double.valueOf(arr1[n/2] + arr1[n/2-1])/2;
    Double m2 = Double.valueOf(arr2[n/2] + arr2[n/2-1])/2;
    
    if (m1 == m2) return m2;
    if (m1 > m2) return medianSorted(Arrays.copyOfRange(arr1, 0, n/2+1), Arrays.copyOfRange(arr2, n/2, n));
    return medianSorted(Arrays.copyOfRange(arr2, 0, n/2+1), Arrays.copyOfRange(arr1, n/2, n));
  }
  
  public static void main(String[] args) {
    Integer[] arr1 = {2, 4, 6};
    Integer[] arr2 = {1, 3, 5};
    System.out.println("Median of arr1 and arr2: " + medianSorted(arr1, arr2));
  }
}
