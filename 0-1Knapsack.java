import java.io.*;
import java.util.*;
import java.lang.*;
/*
Write a function that given a list of items with values and weights, as well as max weight, find the maximum value you can generate from items where the sum of the weights is less than or equal to max.

Example:
weights = [1, 2, 3]
values  = [6, 10, 12]
max wieght = 5

Return: 10 + 12 = 22

Using recursion: Time: O(2^n)
- We can either include the item or not. Check the possible values by including and not including and get the max. Do this in recursion.
               
Using bottom-up Dynamic programming: O(n * W) time and space.
- Define the table with size length of weights rows and max weights columns.
- Update each value in the table, based on the max value it can make for the current max weight.
- Return the last value in the table.

Table:
items\max-weight    0    1    2    3    4    5
      0             0    0    0    0    0    0
      1             0    6    6    6    6    6 (one item atmost)
      2             0    6   10   16   16   16 (2 items atmost)
      3             0    6   10   16   18   22 (3 items atmost)
*/

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static int knapsack(int W, int[] weights, int[] vals) {
    int[][] cache = new int[weights.length + 1][W+1];
    for (int i=1; i<=weights.length; i++) {
      for (int j=1; j<=W; j++) {
        if (weights[i-1] > j)
          cache[i][j] = cache[i-1][j];
        else
          cache[i][j] = Math.max(cache[i-1][j], cache[i-1][j-weights[i-1]] + vals[i-1]);
      }
    }
    return cache[weights.length][W];
  }
  
  public static void main(String[] args) {
    int[] weights = new int[]{1,2,3};
    int[] vals = new int[]{6,10,12};
    int max_weight = 5;
    System.out.println("Max value: " + knapsack(max_weight, weights, vals));
  }
}
