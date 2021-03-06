import java.io.*;
import java.util.*;

/*
Write a function to make change for the given input with minimum number of coins.

Example:
coins = {1, 5, 10, 25}
x = 32
output = 4 (one 25 coin, one 5 coin, two 1 coins)
*/

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  // Recursive O(2^n) time
  public static int minCoins(int x, int[] coins) {
    if (x == 0) return 0;
    int min = x;
    for (int coin : coins) {
      if (x-coin >=0 ) {
        int c = minCoins(x-coin, coins);
        if (min > c+1) min = c+1;
      }
    }
    return min;
  }
  
  private static int minCoinsDp(int x, int[] coins, int[] cache) {
    if (x == 0) return 0;
    int min = x;
    for (int coin : coins) {
      if (x-coin >=0) {
        int c;
        if (cache[x-coin] >=0) c = cache[x-coin];
        else {
          c = minCoinsDp(x-coin, coins, cache);
          cache[x-coin] = c;
        }
        if (min > c+1) min = c+1;
      }
    }
    return min;
  }
  
  //DP: O(N*N) time and O(N) space.
  public static int minCoinsDp(int x, int[] coins) {
    int[] cache = new int[x];
    for (int i=1; i<x; i++) cache[i] = -1;
    return minCoinsDp(x, coins, cache);
  }
  
  public static int minCoinsDp2(int x, int[] coins) {
        int n = coins.length;
        int[][] table = new int[n+1][x+1];
        
        for(int col=1; col<=x; col++) table[1][col] = col;
        
        for(int row=2; row<=n; row++) {
            for(int col=1; col<=x; col++) {
                int val = col - coins[row-1];
                if (val < 0) {
                    table[row][col] = table[row-1][col];
                } else {
                    table[row][col] = Math.min(table[row][val] + 1, table[row-1][col]);
                }
            }
        }
        return table[n][x];
  }
  
  public static void main(String[] args) {
    int[] coins = {1, 5, 10, 25};
    System.out.println("Min coins: " + minCoins(32, coins));
    System.out.println("Min coins: " + minCoinsDp(32, coins));
    System.out.println("Min coins: " + minCoinsDp2(32, coins));
  }
}
