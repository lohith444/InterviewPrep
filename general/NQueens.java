/*
 * This a trick question. The below solution is exponential time complexity.
 * This can be solved in O(1) constant time if you see the trick of mathematical logic.
 * Example:
 * N = 5     
 * [O O X O O       [O O X O
 *  X O O O O        X O O O
 *  O O O O X        O O O X
 *  O X O O O        O X O O]
 *  O O O X O]
 * We can see that the 
 *  - first row can be placed at x=N/2 position. Initialize i = N/2;
 *  - second row can be placed at x-i
 *  - third row can be placed at x+i for odd x+(i-1) for even.
 *  - Decrement i and Repeat for rest rows: 
 *  - fourth row can be placed at x-i. 
 *  - fifthe row can be placed at x+i for odd x+(i-1) for even.
 */
import java.util.*;

public class MyClass {
    public static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public static int N = 5;
    
    private static boolean isSafe(int x, int y, Stack<Point> queens) {
        for(int i = 0; i < queens.size() - 1; i++) {
            Point p = queens.get(i);
            if(x == p.x || y == p.y) return false;
            if(Math.abs(x - p.x) == Math.abs(y - p.y)) return false;
        }
        return true;
    }
    
    private static boolean placeQueen(int col, Stack<Point> queens) {
        if(col >= N) return true;
        
        for(int row=0; row<N; row++) {
            queens.push(new Point(row, col));
            if(isSafe(row, col, queens) && placeQueen(col+1, queens)) return true;
            queens.pop();
        }
        
        return false;
    }
    
    public static Stack<Point> nQueens() {
        Stack<Point> result = new Stack<Point>();
        placeQueen(0, result);
        
        return result;
    }
    
    public static void main(String args[]) {
        Stack<Point> results = nQueens();
        
        for(Point p : results) System.out.println("Point: [" + p.x + ", " + p.y + "]");
    }
}
