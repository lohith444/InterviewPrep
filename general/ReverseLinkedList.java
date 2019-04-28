import java.util.*;
import java.io.*;

public class Solution {
    public static class Node{
        int val;
        Node next;
        Node(int val){
            this.val = val;
            this.next = null;
        }
    }
    
    public static void printLinkedList(Node root){
        if(root == null) return;
        Node curr = root;
        while(curr.next != null){
            System.out.print(curr.val + " -> ");
            curr = curr.next;
        }
        System.out.println(curr.val);
    }
    
    public static Node reverseIterative(Node root){
        Node curr = root;
        Node next = null;
        Node prev = null;
        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        root = prev;
        return root;
    }
    
    public static Node reverseRecursive(Node root){
        Node first = root;
        if (first == null) return first;
        Node rest = first.next;
        if (rest == null) return first;
        rest = reverseRecursive(rest);
        first.next.next = first;
        first.next = null;
        return rest;
    }
  
    public static void main(String[] args) {
        Node n = new Node(1);
        n.next = new Node(2);
        n.next.next = new Node(3);
        n.next.next.next = new Node(4);
        
        System.out.println("Original list:");
        printLinkedList(n);
        System.out.println();
        
        n = reverseIterative(n);
        System.out.println("Reversed list:");
        printLinkedList(n);
        System.out.println();
        
        n = reverseRecursive(n);
        System.out.println("Reversed list:");
        printLinkedList(n);
        System.out.println();
    }
}
