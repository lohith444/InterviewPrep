import java.io.*;
import java.util.*;

/*
Write a function that takes the input matrix of 1's and 0's. Return the max square that can be formed by 1.

Approach: O(n*n) time and space
- Consider each element as right bottom corner of the 2x2 matrix,
- Based on the values of the adjacent elements, the current matrix is formed.
*/

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static int maxSquareMatrix(Integer[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    Integer[][] table = new Integer[m][n];
    int max = 0;
    for(int i=0; i<m; i++) {
      for(int j=0; j<n; j++) {
        if (i==0 || j==0) {
          table[i][j] = matrix[i][j];
        } else {
          if (matrix[i][j] == 0) {
            table[i][j] = 0;
            continue;
          }
          int left = table[i][j-1];
          int up = table[i-1][j];
          int left_corner =table[i-1][j-1];
          
          int min = Math.min(left, up);
          min = Math.min(min, left_corner);
          
          if (min == 0) {
            table[i][j] = 1;
          } else {
            table[i][j] = min + 1;
          }
          
          if (max < table[i][j]) max = table[i][j];
        }
      }
    }
    
    return max;
  }
  
  public static void main(String[] args) {
    Integer[][] matrix = {
      {1, 1, 0, 1},
      {1, 1, 1, 1},
      {0, 1, 0, 0},
      {1, 1, 1, 1}
    };
    
    System.out.println("Max square matrix: " + maxSquareMatrix(matrix));
  }
}
