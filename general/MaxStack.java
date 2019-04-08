import java.io.*;
import java.util.*;
import java.lang.*;
/*
Write a function to implement stack: push, pop, max in O(1) time.

push(1), push(2), push(3), push(2):
2->3->2->1
max = 3

pop():2, max=3
pop():3, max=2

Idea:
- Use linked list to implement stack.
- Keep track of oldMax in the linked list node.
- Also update max.

*/

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  private static class Stack {
    private static class Node {
      private int value;
      private Node next;
      private Node oldMax;
      Node(int val) {
        this.value = val;
        this.next = null;
        this.oldMax = null;
      }
    }
    
    private static Node stack = null;
    private static Node max = null;
    
    private static void push(int val) {
      Node n = new Node(val);
      if (stack != null) {
        n.next = stack;
      }
      stack = n;
      
      if (max == null || n.value > max.value) {
        n.oldMax = max;
        max = n;
      }
    }
    
    private static int pop() {
      if (stack == null) throw new NullPointerException();
      Node n = stack;
      stack = n.next;
      
      if (n.oldMax != null) max = n.oldMax;
      
      return n.value;
    }
    
    private static int top() {
      if (stack == null) throw new NullPointerException();
      return stack.value;
    }
    
    private static int max() {
      if (max == null) throw new NullPointerException();
      return max.value;
    }
    
    private static void print() {
      if (stack != null) {
        Node curr = stack;
        while (curr != null) {
          System.out.print(curr.value + " -> ");
          curr = curr.next;
        }
      }
      System.out.println("null");
    }
  }
  
  public static void main(String[] args) {
    Stack st = new Stack();
    st.push(1);
    st.push(2);
    st.push(3);
    st.push(2);
    
    System.out.println("After push (1,2,3,4): ");
    st.print();
    System.out.println("Max: " + st.max());
    System.out.println();
    
    System.out.println("After 1st pop: " + st.pop());
    st.print();
    System.out.println("Max: " + st.max());
    System.out.println();
    
    System.out.println("After 2nd pop: " + st.pop());
    st.print();
    System.out.println("Max: " + st.max());
    System.out.println();
    
    System.out.println("After 3rd pop: " + st.pop());
    st.print();
    System.out.println("Max: " + st.max());
    System.out.println();
    
    System.out.println("After 4th pop: " + st.pop());
    st.print();
    System.out.println("Max: " + st.max());
  }
}
