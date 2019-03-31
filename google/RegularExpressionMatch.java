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
    
    if (p_len == 0) {
      return s_len == 0;
    }
    
    boolean[][] lookup = new boolean[s_len + 1][p_len + 1];
    
    for(int i = 0; i < s_len; i++) {
      Arrays.fill(lookup[i], false);
    }
    
    // empty string matches with empty pattern.
    lookup[0][0] = true;
    
    for(int j = 1; j < p_len; j++) {
      if (p.charAt(j-1) == '*') {
        lookup[0][j] = lookup[0][j-1];
      }
    }
    
    // 1. If current pattern is *,then 
    //    lookup[i][j] = lookup[i-1][j] || lookup[i][j-1]
    // 2. If current pattern is . or matches with current string,
    //    lookup[i][j] = lookup[i-1][j-1]
    // 3. If current pattern does not match with current string, then
    //    If next pattern is *, then
    //        lookup[i][j] = lookup[i-1][j-1]
    //    else
    //        lookup[i][j] = false
    for (int i = 1; i <= s_len; i++) {
      for (int j = 1; j <= p_len; j++) {
        if (p.charAt(j-1) == '*') {
          lookup[i][j] = lookup[i-1][j] || lookup[i][j-1];
        } else if ((p.charAt(j-1) == '.') || (s.charAt(i-1) == p.charAt(j-1))) {
          lookup[i][j] = lookup[i-1][j-1];
        } else {
          if (j < p_len && p.charAt(j) == '*') {
            lookup[i][j] = lookup[i-1][j-1];
          } else {
            lookup[i][j] = false;
          }
        }
      }
    }
    
    return lookup[s_len][p_len];
  }
  
  public static void main(String[] args) {
    String s = "aab";
    String p = "c*a*b";
    System.out.println("isMatch: " + isMatch(s,p));
  }
}
