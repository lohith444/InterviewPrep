import java.io.*;
import java.util.*;

/*
 * Write a function that takes two strings 
 * as arguments, s and p and returns a boolean
 * denoting whether s matches p.
 *
 * p is a sequence of any number of the following.
 * 1. a-z - which stands for itself
 * 2. . - which matches any character
 * 3. * - which matches 0 or more occurences of the
 *       previous single character
 *
 * Examples:
 * s = 'aba', p = 'ab' => false
 * s = 'aa', p = 'a*' => true
 * s = 'ab', p = '.*' => true
 * s = 'ab', p = '.' => false
 * s = 'aab', p = 'c*a*b' => true
 * s = 'aaa', p = 'a*.' => true
 *
 *
 * Case 1: If pattern is *, then there are 2 cases.
 *        - We can ignore and move to the next character in the previous pattern.
 *        - * matches with one or more characters. Move to the previous character in the string. 
 * Case 2: If pattern is ., then any match. Move both pattern and string to previous characters.
 * Case 3: If current char matches with the current pattern, move both pattern and string to previous characters. Else, return false.
 */

class Solution {
  public static boolean isMatch(String s, String p) {
    int s_len = s.length();
        int p_len = p.length();
        
        boolean[][] T = new boolean[s_len + 1][p_len + 1];
        T[0][0] = true;
        
        // Considered string and pattern index starts from 1 in the table. 
        // Hence i-1 and j-1 gives the current character to compare. 
        for(int j = 1; j < T[0].length; j++) {
            if(p.charAt(j-1) == '*') T[0][j] = T[0][j-2];
        }
        
        for(int i = 1; i < T.length; i++) {
            for(int j = 1; j < T[0].length; j++) {
                char s_c = s.charAt(i - 1);
                char p_c = p.charAt(j - 1);
                /*             |1. T[i-1][j]                                            if p[j-1] == '.' || s[i-1] == p[j-1] 
                 * T[i][j] =   |2. T[i][j-2]                                            if p[j-1] == '*'
                 *             |    || T[i-1][j] if s[i-1] == p[j-2] || p[j-2] == '.'
                 *             |3. false                                                otherwise
                 */
                if ((s_c == p_c) || (p_c == '.')) {
                    T[i][j] = T[i-1][j-1];
                } else if (p_c == '*') {
                    if (T[i][j-2]) {
                        T[i][j] = true;
                    } else if (j > 1 && ((p.charAt(j - 2) == '.') || (p.charAt(j-2) == s_c))) {
                        T[i][j] = T[i-1][j];
                    } else {
                        T[i][j] = false;
                    }
                } else {
                    T[i][j] = false;
                }
            }
        }
        
        return T[s_len][p_len];
  }
  
  public static void main(String[] args) {
    String s = "aab";
    String p = "c*a*b";
    System.out.println("isMatch: " + isMatch(s,p));
  }
}
