import java.io.*;
import java.util.*;

/*
Write a function to find all the permutations.

Example:
arr = {1 2 3}
output = [{1 2 3}, {1 3 2}, {2 1 3}, {2 3 1}, {3 1 2}, {3 2 1}]

str = "abc"
output = ["abc", "acb", "bac", "bca", "cab", cba"]
*/

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  private static void permutation_helper(String prefix, String suffix, List<String> result) {
    if (suffix.length() == 0) {
      result.add(prefix);
    } else {
      for(int i=0; i<suffix.length(); i++) {
        permutation_helper(prefix + suffix.charAt(i), suffix.substring(0,i) + suffix.substring(i+1, suffix.length()), result);
      }
    }
  }
  
  public static List<String> permutations(String str) {
    List<String> result = new ArrayList<>();
    permutation_helper("", str, result);
    return result;
  }
  
  public static void main(String[] args) {
    String str = "abc";
    List<String> res = permutations(str);
    
    for(int i=0; i<res.size(); i++)
      System.out.println(res.get(i));
  }
}
