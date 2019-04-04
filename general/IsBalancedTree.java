import java.io.*;
import java.util.*;

/*
Write a function to check if the given binary tree is balanced.

Binary tree is balanced if the 
- Left tree is balanced.
- Right tree is balanced.
- Height differece is not more than 1.
Example:
        2
      /   \
      3    4
     / \  / 
    6   3 6
    
Do a depth first traverse recursively and find the heights of each sub-tree. Calculate the difference and return the max height.
*/

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  private static class Node {
    private int value;
    private Node left;
    private Node right;
    Node(int value) {
      this.value = value;
      left = null;
      right = null;
    }
  }
  
  public static Boolean isBalanced(Node root) {
    if (balancedHeight(root) > -1) return Boolean.TRUE;
    return Boolean.FALSE;
  }
  
  private static int balancedHeight(Node n) {
    if (n == null) return 0;
    int h1 = balancedHeight(n.left);
    int h2 = balancedHeight(n.right);
    
    if (h1==-1 || h2==-1) return -1;
    if (Math.abs(h1-h2) > 1) return -1;
    return h1 >= h2 ? h1+1 : h2+1;
  }
  
  public static void main(String[] args) {
    Node input = new Node(2);
    input.left = new Node(1);
    input.right = new Node(2);
    input.left.left = new Node(4);
    input.right.right = new Node(5);
    input.left.left.left = new Node(10);
    
    System.out.println(isBalanced(input));
  }
}
