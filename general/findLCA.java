import java.util.*;
import java.io.*;

public class Solution {
    public static class Node{
        int val;
        Node left;
        Node right;
        Node(int val){
            this.val = val;
        }
    }
    
    private static boolean findPath(Node node, Stack<Integer> path, int n){
        if(node == null) return false;
        path.add(node.val);
        if(node.val == n) return true;
        if(findPath(node.left, path, n) || findPath(node.right, path, n)) return true;
        path.pop();
        return false;
    }
    
    public static int findLCA(Node root, int n1, int n2) {
        Stack<Integer> path1 = new Stack<Integer>();
        Stack<Integer> path2 = new Stack<Integer>();
        if (!findPath(root, path1, n1) || !findPath(root, path2, n2)) return -1;
        
        int i;
        for(i=0; i<path1.size() && i<path2.size(); i++){
            if(path1.get(i) != path2.get(i)) break;
        }
        return path1.get(i-1);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        System.out.println("Common ancestor: " + findLCA(root, 2, 3));
    }
}
