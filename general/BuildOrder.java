import java.io.*;
import java.util.*;

/*
Given a list of packages that needs to be built and the dependencies for each package, determine the valid order in which to build the packages.

Example:
0:
1: 0
2: 0
3: 1, 2
4: 3

In the above example, there are 5 packages (0-4), 0 has no dependency, 1 has dependency on 0, 2 has dependency on 0, and so on.
The valid order is: 0, 1, 2, 3, 4

If there are any cycles, throw error.

           --1<--
          /      \
         /        \
    0 <--          --3<--4
         \        /
          \      /
           --2<--

[{}, {0}, {0}, {1,2}, {3}]
Will denote package and dependency using 2-D array.
Idea:
- Use the idea of DFS traversal of a graph
- Choose any random package.
  - Go through the list deeper (recursion). As going through use temporary markers for the visited package.
  - When all the dependencies are traversed, add the last package to result and use permanent marker, reset the temporary marker.
- If temporary marker is found more than once, then there are cycles.
- If permanent marker is found, skip to next package.
- Return the results.
*/

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static List<Integer> buildOrder(List<int[]> packages) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> permMarks = new HashSet<>();
        Set<Integer> tempMarks = new HashSet<>();
        for(int i=0; i < packages.size(); i++) {
            if (!permMarks.contains(i)) {
                visit(packages, i, permMarks, tempMarks, result);
            }
        }
        return result;
    }
    
    private static void visit(List<int[]> packages, int i, Set<Integer> permMarks, Set<Integer> tempMarks, List<Integer> result) {
        if (tempMarks.contains(i)) throw new RuntimeException("cycles found");
        
        if (!permMarks.contains(i)) {
            tempMarks.add(i);
            for (int p : packages.get(i)) {
                visit(packages, p, permMarks, tempMarks, result);
            }
            tempMarks.remove(i);
            permMarks.add(i);
            result.add(i);
        }
    }
    
    public static void main(String[] args) {
        List<int[]> packages = new ArrayList<int[]>();
        packages.add(new int[]{});
        packages.add(new int[]{0});
        packages.add(new int[]{0});
        packages.add(new int[]{1,2});
        packages.add(new int[]{3});
        List<Integer> result = buildOrder(packages);
        System.out.println("Build order:");
        for(int i : result) System.out.println(i);
    }
}
