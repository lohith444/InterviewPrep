import java.io.*;
import java.util.*;
import java.math.BigInteger;

/*
 * Write a function that takes three parameters, 
 * as arguments, Points[], vertex and k
 * and returns k closest Points.
 *
 * Example:
 * points[] = [{1,2}, {3,-1}, {1,4}]
 * vertex = {2,2}
 * k = 2
 * Return k closest points from points[]
 * 
 * Naive approach: T:O(N*lgN) S:O(N)
 *  - For each point in points:
 *    - calculate distance b/w point and vertex.
 *    - store in result array
 *  - Sort the result array w.r.t distance.
 *  - Return first k elements.
 *
 * Better approach: T:O(N*lgK) S:O(K)
 *  - Create a MaxHeap.
 *  - For each point in points:
 *    - calculate distance b/w point and vertex.
 *    - If heap size is less than k:
 *      - push to heap. Heapify.
 *    - If heap size is k:
 *      - If dist < root, heapify. Else continue.
 *  - Return heap;
 */

class Solution {
  public static class Point{
    int x;
    int y;
    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
    void print() {
      System.out.println("Point {" + x + ", " + y + "}");
    }
  }
  
  public static class Distance implements Comparable<Distance>{
    Integer dist;
    Integer idx;
    Distance(Integer dist, Integer idx){
      this.dist = dist;
      this.idx = idx;
    }
    
    @Override
    public int compareTo(Distance obj) {
      return this.dist.compareTo(obj.dist);
    }
  }
  
  public static int calculateDist(Point point, Point vertex) {
    int x_diff = Math.abs(point.x - vertex.x);
    int y_diff = Math.abs(point.y - vertex.y);
    return (x_diff * x_diff) + (y_diff * y_diff);
  }
  
  public static Point[] kClosestPoints1(Point[] points, Point vertex, int k) {
    if (k<=0) {
      return new Point[0];
    }
    if (k >= points.length) {
      return points;
    }
    Distance[] sqDistList = new Distance[points.length];
    for(int i=0; i<points.length; i++) {
      int dist = calculateDist(points[i], vertex);
      sqDistList[i] = new Distance(dist, i);
    }
    
    Arrays.sort(sqDistList);
    for(int i=0; i<sqDistList.length; i++) {
      System.out.println("id: " + sqDistList[i].idx + " dist: " + sqDistList[i].dist);
    }
    
    Distance[] result = Arrays.copyOfRange(sqDistList, 0, k);
    Point[] kClosestPoints = new Point[result.length];
    for(int i=0; i<result.length; i++){
      kClosestPoints[i] = points[result[i].idx];
    }
    return kClosestPoints;
  }
  
  public static void main(String[] args) {
    Point p1 = new Point(1,2);
    Point p2 = new Point(4,-3);
    Point p3 = new Point(1,4);
    Point[] points = new Point[]{p1, p2, p3};
    Point vertex = new Point(2, 2);
    
    Point[] result = kClosestPoints1(points, vertex, 4);
    for(int i=0; i<result.length; i++) {
      result[i].print();
    }
  }
}
