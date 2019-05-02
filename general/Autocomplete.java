import java.util.*;
import java.io.*;

public class Solution {
    public static class Autocomplete {
        private class Node {
            String prefix;
            HashMap<Character, Node> children;
            boolean isWord;
            
            Node(String prefix) {
                this.prefix = prefix;
                this.children = new HashMap<Character, Node>();
            }
        }
        
        private Node trie;
        
        private void insertWord(String s) {
            Node curr = trie;
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(!curr.children.containsKey(c)) {
                    curr.children.put(c, new Node(s.substring(0, i+1)));
                }
                curr = curr.children.get(c);
                if (i == s.length() - 1) curr.isWord = true;
            }
        }
        
        public Autocomplete(String[] dict) {
            trie = new Node("");
            for (String s : dict) insertWord(s);
        }
        
        private void findAllWordsForPrefix(Node n, List<String> results) {
            if (n.isWord) results.add(n.prefix);
            for (char c : n.children.keySet()) {
                findAllWordsForPrefix(n.children.get(c), results);
            }
        }
        
        public List<String> getWordsForPrefix(String pre) {
            Node curr = trie;
            List<String> results = new LinkedList<String>();
            for (char c : pre.toCharArray()) {
                if (curr.children.containsKey(c)) {
                    curr = curr.children.get(c);
                } else {
                    return results;
                }
            }
            
            findAllWordsForPrefix(curr, results);
            return results;
        }
    }

    public static void main(String[] args) {
        String[] dict = {"abc", "ade", "ab", "a", "def", "bc"};
        String prefix = "ab";
        Autocomplete ac = new Autocomplete(dict);
        List<String> results = ac.getWordsForPrefix(prefix);
        System.out.println("All the words with prefix " + prefix + " are:");
        for(String s : results) {
            System.out.println(s);
        }
    }
}
