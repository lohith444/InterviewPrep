//{ Driver Code Starts
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList; // Import ArrayList
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            String input = br.readLine();
            String[] inputs = input.split(" ");
            int[] arr = new int[inputs.length];

            for (int i = 0; i < inputs.length; i++) {
                arr[i] = Integer.parseInt(inputs[i]);
            }

            // Get the result as an ArrayList<Integer>
            ArrayList<Integer> ans = new Solution().findTwoElement(arr);
            System.out.println(ans.get(0) + " " + ans.get(1));
            System.out.println("~");
        }
    }
}

// } Driver Code Ends


// User function Template for Java

class Solution {
    // Function to find two elements in array
    ArrayList<Integer> findTwoElement(int arr[]) {
        // Approach using Math sum
        /* ArrayList<Integer> result = new ArrayList<>();
        int n = arr.length;
        double s1 = 0.0; // sum of all nums in arr
        double s2 = 0.0; // sum of squares of all nums in arr
        double sn1 = (n * (n + 1)) / 2; // sum of nums from 1 to n
        double sn2 = (n * (n + 1) * (2*n + 1)) / 6; // sum of squares of nums from 1 to n
        for (int num : arr) {
            s1 += num;
            s2 += num * num;
        }
        double eq1 = (s1 - sn1); // x - y  (x => repeating, y => missing)
        double eq2 = (s2 - sn2); // x^2 - y^2
        eq2 = eq2 / eq1; // x + y
        double repeating = (eq1 + eq2) / 2; // 2x = (eq1 + eq2)
        double missing = eq2 - repeating; // y = eq2 (x + y) - x
        result.add((int)repeating);
        result.add((int)missing);
        return result; */
        
        // Approach using XOR
        int n = arr.length;
        int xr = 0;
        for (int i = 0; i < n; i++) {
            xr ^= arr[i]; // XOR with numbers in the arr
            xr ^= (i + 1); // XOR with nums from 1 to n
        }
        
        // Since XOR will cancel even occurances, we will be left with (repeating ^ missing) in xr
        // Find the bit number position which is different in repeating and missing
        int bitNo = 0;
        while ((xr & (1 << bitNo)) == 0) {
            bitNo++;
        }
        
        // Group the numbers in arr and nums(1 to n) to zero and one club
        int zero = 0, one = 0;
        for (int i = 0; i < n; i++) {
            if ((arr[i] & (1 << bitNo)) != 0) {
                // Part of one club
                one ^= arr[i];
            } else {
                // Part of zero club
                zero ^= arr[i];
            }
            
            if (((i + 1) & (1 << bitNo)) != 0) {
                one ^= (i + 1); 
            } else {
                zero ^= (i + 1);
            }
        }
        
        // There will be missing and repeating numbers in one and zero
        // Next find out which one is missing and which one is repeating
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == one) {
                cnt++;
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        if (cnt == 2) {
            result.add(one);
            result.add(zero);
        } else {
            result.add(zero);
            result.add(one);
        }
        
        return result;
    }
}
