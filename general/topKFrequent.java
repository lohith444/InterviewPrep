import java.io.*;
import java.util.*;

/*
Write a function that given a list of strings, returns the k most frequently recurring string.
If there are equal frequent words, return the string with lower alphabet first.

Assumption:
- k is always valid (1 <= k < words.length)
- All the words are in lowercase.

Example:
input = ["sam", "lily", "sam", "adam", "lily"]
For k=2, output = "lily", "sam"

Using hashmap: O(N*logN) time, O(N) space
- Create a hashmap.
- Add items and count to the hashmap.
- Sort the hashmap by value.
- Return the hashmap key till k.
*/

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

public class Solution {
    public static List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> wordCountMap = new HashMap<String, Integer>();
        for (String word : words) {
            Integer count = wordCountMap.get(word);
            if (count == null) count = 0;
            wordCountMap.put(word, ++count);
        }
        
        List<Map.Entry> wordMapEntry = new ArrayList<Map.Entry>(wordCountMap.entrySet());
        Collections.sort(wordMapEntry, new Comparator() {
            public int compare(Object o1, Object o2) {
                Map.Entry mp1 = (Map.Entry)o1;
                Map.Entry mp2 = (Map.Entry)o2;
                Integer v1 = (Integer)(mp1.getValue());
                Integer v2 = (Integer)(mp2.getValue());
                if(v2.compareTo(v1) == 0) return ((String)mp1.getKey()).compareTo((String)mp2.getKey());
                return v2.compareTo(v1);
            }
        });
        
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < k; i++) {
            Map.Entry map = wordMapEntry.get(i);
            result.add((String)(map.getKey()));
        }
        return result;
    }
    
    public static void main(String[] args) {
        String[] words = {"sam", "lily", "sam", "adam", "lily"};
        List<String> freqWords = topKFrequent(words, 2);
        System.out.println(Arrays.toString(freqWords.toArray()));
    }
}
