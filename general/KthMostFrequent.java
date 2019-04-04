import java.io.*;
import java.util.*;

/*
Write a function that given a list of strings, returns the kth most frequently recurring string.

Example:
input = ["sam", "lily", "sam", "adam"]
For k=2, output = "sam"

Using hashmap: O(N*logN) time, O(N) space
- Create a hashmap.
- Add items and count to the hashmap.
- Sort the hashmap by value.
- Return the hashmap key at k.
*/

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static String kthMostFreq(String[] strings, int k) {
    Map<String, Integer> map = new HashMap<String, Integer>();
    for(String s : strings) {
      Integer x = map.get(s);
      if (x == null) x = 0;
      map.put(s, ++x);
    }
    
    List<Map.Entry> list = new ArrayList(map.entrySet());
    Collections.sort(list, new Comparator() {
      public int compare(Object o1, Object o2) {
        Integer v1 = (Integer) ((Map.Entry)o1).getValue();
        Integer v2 = (Integer) ((Map.Entry)o2).getValue();
        return v1.compareTo(v2);
      }
    });
    
    if (list.size() >= k) return (String)((Map.Entry)list.get(k-1)).getKey();
    return null;
  }
  
  public static void main(String[] args) {
    String[] input = {"sam", "sam", "sam", "adam"};
    String res = kthMostFreq(input, 2);
    System.out.println("kth Most frequent: " + res);
  }
}
