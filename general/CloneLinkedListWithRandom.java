import java.io.*;
import java.util.*;

/*
Given a linked list where each node has two pointers,
one to the next node and one to the random node in the list. Write a function to clone the linked list.

Example:
list = 1 -> 5 -> 10 -> 25
       |    |     |     |
       v    v     v     v
      10    25    5     1
      
output = clone of list.

Idea:
- Go through the list one by one, make a copy of each node and make current point to next. (Break the next link)
  1 -> 1 -> 5 -> 5 -> 10 -> 10 -> 25 -> 25
  |         |          |           |
  v         v          v           v
 10        25          5           1
- Update random pointer on the copy by going over each node.
  1 -> 1 -> 5 -> 5 -> 10 -> 10 -> 25 -> 25
  |    |    |    |     |     |     |     |
  v    v    v    v     v     v     v     v
 10   10   25   25     5     5     1     1
- Break the link from original node to copy node and add the link to next.
*/

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  private static class Node {
    int value;
    Node next;
    Node random;
    Node(int value) {
      this.value = value;
      this.next = null;
      this.random = null;
    }
  }
  
  public static Node cloneLinkedList(Node list) {
    Node curr = list;
    while (curr != null) {
      Node temp = new Node(curr.value);
      temp.next = curr.next;
      curr.next = temp;
      curr = temp.next;
    }
    
    curr = list;
    while (curr != null) {
      Node temp = curr.next;
      temp.random = curr.random.next;
      curr = temp.next;
    }
    
    curr = list;
    Node res = curr.next;
    while (curr.next != null) {
      Node temp = curr.next;
      curr.next = curr.next.next;
      curr = temp;
    }
    
    return res;
  }
  
  public static void printNode(Node n) {
    Node curr = n;
    while (curr != null) {
      System.out.println("Node: " + curr.value + ", next: " + (curr.next == null ? null : curr.next.value) + ", random: " + (curr.random == null ? null : curr.random.value));
      curr = curr.next;
    }
  }
  
  public static void main(String[] args) {
    Node n = new Node(1);
    n.next = new Node(2);
    n.next.next = new Node(3);
    n.next.next.next = new Node(4);
    
    n.random = n.next.next;
    n.next.random = n;
    n.next.next.random = n.next;
    n.next.next.next.random = n.next.next.next;
    
    System.out.println("Input:");
    printNode(n);
    
    Node clone = cloneLinkedList(n);
    
    System.out.println("Clone:");
    printNode(clone);
  }
}
