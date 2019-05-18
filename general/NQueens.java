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
