import java.util.*;

public class MyClass {
    // Build longest prefix matching some suffix in pattern
    private static int[] buildLPS(String pattern) {
        int n = pattern.length();
        int[] lps = new int[n];
        int index = 0;
        
        for(int i=1; i<n;) {
            if(pattern.charAt(i) == pattern.charAt(index)) {
                lps[i] = index +1;
                index++;
                i++;
            } else {
                if(index != 0) {
                    index = lps[index-1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
    
    public static boolean kmpPatternMatching(String str, String pattern) {
        int[] lps = buildLPS(pattern);
        
        int i = 0;
        int j = 0;
        
        while(i < str.length() && j < pattern.length()) {
            if(str.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                if(j != 0) {
                    j = lps[j-1];
                } else {
                    i++;
                }
            }
        }
        
        if (j == pattern.length()) return true;
        return false;
    }
    
    public static void main(String args[]) {
       String str = "abcxabcdabcdabcy";
       String pattern = "abcdy";
       
       if(kmpPatternMatching(str, pattern)) {
           System.out.println("Pattern matched");
       } else {
           System.out.println("Pattern did not match");
       }
    }
}
