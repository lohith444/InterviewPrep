import java.util.*;
import java.io.*;

public class Solution {
    private static int updateMax(int[] hist, Stack<Integer> st, int max, int marker) {
        int currIndex = st.pop();
        int currMax = hist[currIndex] * (st.isEmpty() ? (marker-1) : (marker-1-st.peek()));
        if (currMax > max) max = currMax;
        return max;
    }
    
    public static int maxAreaHistogram(int[] hist) {
        Stack<Integer> st = new Stack<Integer>();
        int i = 0;
        Integer max = 0;
        while(i < hist.length) {
            if (st.isEmpty() || hist[st.peek()] <= hist[i]) {
                st.push(i++);
            } else {
                max = updateMax(hist, st, max, i);
            }
        }
        
        while(!st.isEmpty()) {
            max = updateMax(hist, st, max, i);
        }
        return max;
    }
  
    public static void main(String[] args) {
        int[] hist = {2, 2, 3, 4, 5, 3, 3, 1};
        System.out.println("Max Area: " + maxAreaHistogram(hist));
    }
}
