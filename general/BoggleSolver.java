import java.util.*;

public class MyClass {
    public static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isWord;
        
        TrieNode() {
            children = new HashMap<Character, TrieNode>();
            isWord = false;
        }
    }
    
    public static void insert(TrieNode root, String key) {
        TrieNode curr = root;
        for(char ch : key.toCharArray()) {
            TrieNode node = curr.children.get(ch);
            if(node == null) {
                curr.children.put(ch, new TrieNode());
            }
            curr = curr.children.get(ch);
        }
        curr.isWord = true;
    }
    
    private static boolean isSafe(int i, int j, boolean[][] visited) {
        int m = visited.length;
        int n = visited[0].length;
        return (i >= 0 && i < m && j >= 0 && j < n && !visited[i][j]);
    }
    
    private static void searchWord(TrieNode root, char[][] boggle, int i, int j, boolean[][] visited, String str) {
        if(root.isWord == true) System.out.println(str);
        
        if(isSafe(i, j, visited)) {
            visited[i][j] = true;
            
            for (Map.Entry mapElement : root.children.entrySet()) {
                TrieNode node = (TrieNode) mapElement.getValue();
                
                if (node != null) {
                    char ch = (char) mapElement.getKey();
                    
                    if(isSafe(i+1, j+1, visited) && boggle[i+1][j+1] == ch) searchWord(node, boggle, i+1, j+1, visited, str+ch);
                    if(isSafe(i, j+1, visited) && boggle[i][j+1] == ch) searchWord(node, boggle, i, j+1, visited, str+ch);
                    if(isSafe(i+1, j, visited) && boggle[i+1][j] == ch) searchWord(node, boggle, i+1, j, visited, str+ch);
                    if(isSafe(i-1, j+1, visited) && boggle[i-1][j+1] == ch) searchWord(node, boggle, i-1, j+1, visited, str+ch);
                    if(isSafe(i+1, j-1, visited) && boggle[i+1][j-1] == ch) searchWord(node, boggle, i+1, j-1, visited, str+ch);
                    if(isSafe(i-1, j, visited) && boggle[i-1][j] == ch) searchWord(node, boggle, i-1, j, visited, str+ch);
                    if(isSafe(i, j-1, visited) && boggle[i][j-1] == ch) searchWord(node, boggle, i, j-1, visited, str+ch);
                    if(isSafe(i-1, j-1, visited) && boggle[i-1][j-1] == ch) searchWord(node, boggle, i-1, j-1, visited, str+ch);
                }
            }
        }
        visited[i][j] = false;
    }
    
    public static void findWords(char[][] boggle, TrieNode root) {
        int m = boggle.length;
        int n = boggle[0].length;
        boolean[][] visited = new boolean[m][n];
        String str = "";
        
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                char ch = boggle[i][j];
                TrieNode node = root.children.get(ch);
                if(node != null) {
                    str = str + ch;
                    searchWord(node, boggle, i, j, visited, str);
                    str = "";
                }
            }
        }
    }
    
    public static void main(String args[]) {
        TrieNode root = new TrieNode();
        insert(root, "geeks");
        insert(root, "g0");
        insert(root, "quiz");
        insert(root, "for");
        
        char[][] boggle = {
            {'g', 'i', 'z'},
            {'u', 'e', 'k'},
            {'q', 's', 'e'}
        };
        
        findWords(boggle, root);
    }
}
