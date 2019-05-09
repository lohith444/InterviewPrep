import java.util.*;
import java.io.*;

/**
 * Given two strings str1 and str2, and below operations, find minimum
 * number of edits(operations) required to convert str1 to str2.
 * 
 * Example:
 * Input:   str1 = "sunday", str2 = "saturday"
 * Output:  3
 * Last three and first characters are same.  We basically
 * need to convert "un" to "atur".  This can be done using
 * below three operations. 
 * Replace 'n' with 'r', insert t, insert a
 * */
public class Solution {
    public static int editDist(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m+1][n+1];
        
        for(int i=0; i <= m; i++){
            for(int j=0; j <= n; j++) {
                if(i == 0) dp[i][j] = j;
                else if(j == 0) dp[i][j] = i;
                else if(str1.charAt(i-1) == str2.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
                else dp[i][j] = 1 + Math.min(Math.min(dp[i][j-1],
                                             dp[i-1][j]),
                                             dp[i-1][j-1]);
            }
        }
        
        return dp[m][n];
    }
    
    public static void main(String[] args) {
        String str1 = "sunday";
        String str2 = "saturday";
        System.out.println("output: " + editDist(str1, str2));
    }
}
