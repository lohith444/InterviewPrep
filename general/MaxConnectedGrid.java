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
    
    private static boolean isSafe(int i, int j, boolean[][] visited) {
        int rows = visited.length;
        int cols = visited[0].length;
        return (i >= 0 && i < rows && j >= 0 && j < cols && !visited[i][j]);
    }
    public static void traverse(int[][] grid, int key, int row, int col, List<Point> result, boolean[][] visited) {
        int[] rv = {-1,  0, 1, 0};
        int[] cv = { 0, -1, 0, 1};
        visited[row][col] = true;
        result.add(new Point(row, col));
        
        for(int i = 0; i < 4; i++) {
            int curr_row = row + rv[i];
            int curr_col = col + cv[i];
            
            if (isSafe(curr_row, curr_col, visited) && grid[curr_row][curr_col] == key) {
                traverse(grid, key, curr_row, curr_col, result, visited);
            }
        }
        visited[row][col] = false;
    }
    
    public static List<Point> maxConnectedGrid(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        List<Point> result = new ArrayList<Point>();
        int max = 0;
        
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                if(!visited[row][col]) {
                    List<Point> temp = new ArrayList<Point>();
                    traverse(grid, grid[row][col], row, col, temp, visited);
                    
                    if(max < temp.size()) {
                        max = temp.size();
                        result.clear();
                        result.addAll(temp);
                    }
                }
            }
        }
        return result;
    }
    
    public static void printPoints(List<Point> points) {
        for(Point p : points) {
            System.out.println("Point: " + p.x +", " + p.y);
        }
    }
    public static void main(String args[]) {
        int[][] grid = {
            {1, 2, 3},
            {1, 1, 2},
            {2, 1, 1}
        };
        
        List<Point> result = maxConnectedGrid(grid);
        printPoints(result);
    }
}
