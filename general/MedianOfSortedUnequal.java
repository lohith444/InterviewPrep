import java.io.*;
import java.util.*;

/*
Write a function that takes two unequal length sorted arrays and finds the median.

Example:
arr1 = [2 4 6]
arr2 = [1 3 5 7]

median = 4 { [1 2 3 4 5 6 7] => 4}

Brute force: Merge and find median. O(m+n) time and space.

O(log(min(m,n))) time solution.
1. start = 0, end = 3. => partitionX = (0+3)/2 = 1 => partitionY = (3+4+1)/2 - 1 = 3.
2. This means arr1 is partitioned such that 1 element on left and 2 elements on right, arr2 is partitioned such that 3 elements on left and 1 element on right. Both halves are equal (1 greater if odd)
   {2}   |  {4,6}      {x1, x2}   |  {x3 x4}
 {1,3,5} |   {7}     {y1, y2, y3} |  {y4, y5}
- Found: If (x2 <= y4) && (y3 <= x3)
- Else if maxLeftX > minRightY, move partition to left on arr1 by changing the end.
- Else move partition to right on arr1 by changing the start.
*/

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

public class Solution {
    public static double median(int[] arr1, int[] arr2){
        if (arr1.length > arr2.length) return median(arr2, arr1);
        
        int x = arr1.length;
        int y = arr2.length;
        
        int low = 0;
        int high = x;
        
        while(low <= high) {
           int partitionX = (low + high)/2;
           int partitionY = (x+y+1)/2 - partitionX;
           
           int leftX = (partitionX == 0) ? Integer.MIN_VALUE : arr1[partitionX - 1];
           int rightX = (partitionX == x) ? Integer.MAX_VALUE : arr1[partitionX];
           int leftY = (partitionY == 0) ? Integer.MIN_VALUE : arr2[partitionY - 1];
           int rightY = (partitionY == y) ? Integer.MAX_VALUE : arr2[partitionY];
           
           if(leftX <= rightY && leftY <= rightX){
               if((x+y)%2 == 0) return ((double)(Math.max(leftX, leftY) + Math.min(rightX, rightY)))/2;
               return (double)Math.max(leftX, leftY);
           } else if (leftX > rightY) {
               high = partitionX - 1;
           } else {
               low = partitionX + 1;
           }
        }
        
        throw new RuntimeException("unreachable");
    }
  
    public static void main(String[] args) {
        int[] arr1 = {2, 3, 7, 11};
        int[] arr2 = {1, 5, 6, 13};
        
        System.out.println("Median: " + median(arr1, arr2));
    }
}
