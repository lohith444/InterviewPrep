import java.util.*;
import java.io.*;

public class Solution {
    public static class Tuple<X, Y> { 
        public final X v1; 
        public final Y v2; 
        public Tuple(X v1, Y v2) {
            this.v1 = v1;
            this.v2 = v2; 
        } 
    } 

    private static class Node {
        int val;
        Node left;
        Node right;
        Node(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
    
    public static void printPreOrder(Node root){
        if(root == null) return;
        printPreOrder(root.left);
        printPreOrder(root.right);
        System.out.print(root.val + " ");
    }
    
    public static void printInOrder(Node root){
        if(root == null) return;
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }
    
    public static void printPostOrder(Node root){
        if(root == null) return;
        System.out.print(root.val + " ");
        printPostOrder(root.left);
        printPostOrder(root.right);
    }
    
    private static int height(Node root) {
        if (root == null) return 0;
        int lh = height(root.left);
        int rh = height(root.right);
        return lh >= rh ? lh+1:rh+1;
    }
    
    private static void printGivenLevel(Node root, int level) {
        if(root == null) return;
        if(level == 1) System.out.print(root.val + " ");
        else {
            printGivenLevel(root.left, level-1);
            printGivenLevel(root.right, level-1);
        }
    }
    
    public static void printLevelOrder(Node root){
        int h = height(root);
        for(int i=1; i<=h; i++) {
            System.out.print("Level" + i + ": ");
            printGivenLevel(root, i);
            System.out.println();
        }
    }
    
    public static void printLevelOrder2(Node root){
        if (root == null) return;
        Queue<Tuple<Node, Integer>> que = new LinkedList<Tuple<Node, Integer>>();
        int level = 1;
        que.add(new Tuple(root, level));
        System.out.print("Level1: ");
        while(!que.isEmpty()){
            Tuple<Node, Integer> temp = que.remove();
            Node curr = temp.v1;
            int currLevel = temp.v2;
            if(currLevel != level){
                System.out.println();
                level = currLevel;
                System.out.print("Level" + level + ": ");
            }
            System.out.print(curr.val + " ");
            if(curr.left != null) que.add(new Tuple(curr.left, currLevel+1));
            if(curr.right != null) que.add(new Tuple(curr.right, currLevel+1));
        }
    }
  
    public static void main(String[] args) {
        Node root = new Node(2);
        root.left = new Node(3);
        root.right = new Node(4);
        root.left.left = new Node(5);
        root.left.right = new Node(9);
        root.right.left = new Node(6);
        root.left.left.left = new Node(2);
        System.out.println("Pre order traversal: ");
        printPreOrder(root);
        System.out.println();
        System.out.println();
        
        System.out.println("In order traversal: ");
        printInOrder(root);
        System.out.println();
        System.out.println();
        
        System.out.println("Post order traversal: ");
        printPostOrder(root);
        System.out.println();
        System.out.println();
        
        printLevelOrder(root);
        System.out.println();
        System.out.println();
        
        printLevelOrder2(root);
        System.out.println();
        System.out.println();
    }
}
