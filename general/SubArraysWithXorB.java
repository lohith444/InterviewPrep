public class Solution {
    public static int subarraysWithSumK(int []a, int b) {
        // Write your code here
        // Intuition
        // If we perform XOR of subarray ending at index i with 'b', we get value 'X'
        // The idea is to find if we have subarray prefix with value 'X' is found before
        // In order to do this, we keep a map that stores the subarray prefix found so far and their count
        // Time Complexity: O(N), Space: O(N)
        // This is the optimal solution. The other better way is to generate subarrays (which takes O(N*N)) and compute XOR
        int xr = 0;
        int cnt = 0;
        Map<Integer, Integer> frontXrCnt = new HashMap<>();
        frontXrCnt.add(0, 1); // empty element xor is 0 and we put initially value of 1
        for (int i = 0; i < a.length; i++) {
            xr ^= a[i];
            if (frontXrCnt.containsKey(xr ^ b)) {
                cnt += frontXrCnt.get(xr ^ b);
            }
            frontXrCnt.put(xr, frontXrCnt.getOrDefault(xr, 0) + 1);
        }
  
        return cnt;
    }
}
