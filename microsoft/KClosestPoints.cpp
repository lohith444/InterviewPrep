#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

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

typedef struct point_t{
  int x;
  int y;
}Point;

typedef struct distance_t{
  uint dist;
  uint idx;
}Distance;

void swap(Distance& d1, Distance& d2) {
  Distance temp = d1;
  d1 = d2;
  d2 = temp;
}

void maxHeapify(vector<Distance>& distList, uint idx) {
  uint largest = idx;
  uint left = 2*idx + 1;
  uint right = 2*idx + 2;
  if (left < distList.size() && distList[left].dist > distList[largest].dist) {
    largest = left;
  }
  if (right < distList.size() && distList[right].dist > distList[largest].dist) {
    largest = right;
  }
  
  if (largest != idx) {
    swap(distList[idx], distList[largest]);
    maxHeapify(distList, largest);
  }
}

bool distCompare(Distance const& d1, Distance const& d2) {
  if(d1.dist == d2.dist) {
    return false;
  }
  return d1.dist > d2.dist ? false:true;
}

void printDist(vector<Distance> const& distList){
  for(uint i=0; i<distList.size(); i++) {
    cout << "Distance: {" << distList[i].dist << ", " << distList[i].idx << "}" << endl;
  }
}

void printPoints(vector<Point> const& points) {
  for(uint i=0; i<points.size(); i++) {
    cout << "Point: {" << points[i].x << ", " << points[i].y << "}" << endl;
  }
}

uint calcDist(Point p, Point v) {
  uint x_diff = abs(p.x - v.x);
  uint y_diff = abs(p.y - v.y);
  return (x_diff * x_diff) + (y_diff * y_diff);
}

vector<Point> kClosestPoints1(vector<Point> points, Point vertex, uint k) {
  vector<Point> result;
  vector<Distance> distList;
  for(uint i=0; i<points.size(); i++) {
    uint dist = calcDist(points[i], vertex);
    distList.push_back({dist, i});
  }
  sort(distList.begin(), distList.end(), &distCompare);
  printDist(distList);
  for(uint i=0; i<k && k<=distList.size(); i++) {
    result.push_back(points[distList[i].idx]);
  }
  return result;
}

vector<Point> kClosestPoints2(vector<Point> points, Point vertex, uint k) {
  vector<Point> result;
  vector<Distance> distList;
  for(uint i=0; i<points.size(); i++) {
    uint dist = calcDist(points[i], vertex);
    if (distList.size() < k) {
      distList.push_back({dist, i});
      maxHeapify(distList, 0);
    } else {
      if (distList[0].dist > dist) {
        distList[0] = {dist, i};
        maxHeapify(distList, 0);
      }
    }
  }
  for(uint i=0; i < distList.size(); i++) {
    result.push_back(points[distList[i].idx]);
  }
  return result;
}

// To execute C++, please define "int main()"
int main() {
  Point p1 = {1, 2};
  Point p2 = {4, -12};
  Point p3 = {1, 6};
  Point p4 = {10, 15};
  vector<Point> points;
  points.push_back(p1);
  points.push_back(p2);
  points.push_back(p3);
  points.push_back(p4);
  
  vector<Point> result = kClosestPoints2(points, {2,2}, 3);
  printPoints(result);
  return 0;
}
