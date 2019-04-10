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
  public static List<Integer> buildOrder(ArrayList<ArrayList<Integer>> processes) {
    Set<Integer> tempMarks = new HashSet<Integer>();
    Set<Integer> permMarks = new HashSet<Integer>();
    List<Integer> results = new ArrayList<Integer>();
    
    for (int i=0; i<processes.size(); i++) {
      if (!permMarks.contains(i)) {
        visit(i, processes, tempMarks, permMarks, results);
      }
    }
    
    return results;
  }
  
  private static void visit(int i, ArrayList<ArrayList<Integer>> processes, Set<Integer> tempMarks, Set<Integer> permMarks, List<Integer> results) {
    if (tempMarks.contains(i)) throw new RuntimeException("cycles found");
    
    if (!permMarks.contains(i)) {
      tempMarks.add(i);
      for (int p : processes.get(i)) {
        visit(p, processes, tempMarks, permMarks, results);
      }
      tempMarks.remove(i);
      permMarks.add(i);
      results.add(i);
    }
  }
  
  public static void main(String[] args) {
    ArrayList<Integer> zeroth_deps = new ArrayList<Integer>();
    ArrayList<Integer> first_deps = new ArrayList<Integer>() {
      {
        add(0);
      }
    };
    ArrayList<Integer> second_deps = new ArrayList<Integer>() {
      {
        add(0);
      }
    };
    ArrayList<Integer> third_deps = new ArrayList<Integer>() {
      {
        add(1);
        add(2);
      }
    };
    ArrayList<Integer> fourth_deps = new ArrayList<Integer>() {
      {
        add(3);
      }
    };
    
    ArrayList<ArrayList<Integer>> pckgs = new ArrayList<ArrayList<Integer>>() {
      {
        add(zeroth_deps);
        add(first_deps);
        add(second_deps);
        add(third_deps);
        add(fourth_deps);
      }
    };
    
    List<Integer> results = buildOrder(pckgs);
    
    for (Integer i : results) {
      System.out.print(i + " ");
    }
  }
}
