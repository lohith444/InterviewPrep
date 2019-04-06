import java.io.*;
import java.util.*;

/*
Write a function that takes a string and dictionary, and finds the minimum number of characters to be removed from input string to match a string in the dictionary.

Example:
dict = ["a", "aa", "aaa", "def"]
str = "abc"
output: 2 (min of 2 chars to be removed for a valid string)


str = "defg"
output: 1

Brute force: For each char, find all the possible strings and update the maximum char.

Using BFS. O(n!) time
             abc
          /   |   \
         ab   ac  bc
        / \  / \  / \
       a  b a  c b   c
1. Do a BFS by going level by level.
2. When the string is found, output is (original string length - found string length)
*/

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static int deleteChars(String str, HashSet<String> dict) {
    Queue<String> queue = new LinkedList<String>();
    Set<String> queueSet = new HashSet<String>();
    
    queue.add(str);
    queueSet.add(str);
    
    while(!queue.isEmpty()) {
      String s = queue.remove();
      queueSet.remove(s);
      if(dict.contains(s)) return str.length() - s.length();
      for(int i=0; i<s.length(); i++) {
        String sub = s.substring(0, i) + s.substring(i+1, s.length());
        if(!queueSet.contains(sub) && sub.length() > 0) {
          queue.add(sub);
          queueSet.add(sub);
        }
      }
    }
    return -1;
  }
  
  public static void main(String[] args) {
    HashSet<String> dict = new HashSet<String>();
    dict.add("a");
    dict.add("aa");
    dict.add("aaa");
    String str = "abc";
    System.out.println("Number of min chars to be removed: " + deleteChars(str, dict));
  }
}
