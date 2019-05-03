import java.util.*;
import java.io.*;
/**
 * Rotate the given 2-D array 90 degree clockwise without using extra space.
 * 
 * e.g.
 * input[][] = [[1,  2,  3,  4 ],
 *              [5,  6,  7,  8 ],
 *              [9,  10, 11, 12],
 *              [13, 14, 15, 16]]
 * output[][] = [[13,  9,  5,  1],
 *               [14,  10, 6,  2],
 *               [15,  11, 7,  3],
 *               [16,  12, 8,  4]]
 * 
 * Idea:
 * - We can see a pattern where the below numbers can be rotated.
 *   1 -> 4 -> 16 -> 13 -> 1
 *   2 -> 8 -> 15 -> 9 -> 2 and so on.
 * For (i, j) = (0, 1)
 *   (0, 1) = (i, j) = 2
 *   (1, 3) = (j, n-1-i) = 8
 *   (3, 2) = (n-1-i, n-1-j) = 15
 *   (2, 0) = (n-1-j, i) = 9
 * - This will rotate the first square. (outer most)
 * - Similarly we can go to the next square and do the same. 
 * - There will be n/2 = 4/2 = 2 squares.
 * - If the square is odd length, then we can round it off to smaller number of n/2.
 *   
 * */
public class Solution {
    public static void rotate(int[][] arr) {
        int n = arr.length;
        int x = n/2;
        int y = n - 1;
        
        for(int i = 0; i <= x; i++) {
            for(int j = i; j < y - i; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[y - j][i];
                arr[y - j][i] = arr[y - i][y - j];
                arr[y - i][y - j] = arr[j][y - i];
                arr[j][y - i] = temp;
            }
        }
    }
    
    public static void printArr(int[][] arr) {
        for (int[] a : arr) {
            for(int i : a) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        int N = inp.nextInt();
        
        int input[][] = new int[N][N];
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                input[i][j] = inp.nextInt();
            }
        }
        
        /* int input[][] = {{ 1,  2,  3,  4},
                         { 5,  6,  7,  8},
                         { 9, 10, 11, 12},
                         {13, 14, 15, 16}}; */
        System.out.println("Input array: ");
        //System.out.println(Arrays.deepToString(input));
        printArr(input);
        
        rotate(input);
        System.out.println("Rotated array: ");
        //System.out.println(Arrays.deepToString(input));
        printArr(input);
    }
}
