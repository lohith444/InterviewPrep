#include <iostream>
#include <vector>
#include <stack>
using namespace std;

/*
Write a function that takes in one argument,
histogram and returns maximum area of the rectangle
formed by histogram.
Histogram denotes height at index and of unit width.

Example:
hist[] = [1,2,3,4,5,3,3,2,2]
y
|
|    _
|   _
|  _  __
| _     __
|_
|__________________________>x
Max area formed is 2*8 = 16

Approach:
- Create a stack.
- At each index, check the value.
- If the stack is empty or if value at curr index is greater than or equal to stack top, push the element to the stack. (As the previous rect can be included in the curr rect)
- If the value at curr index is less than stack top, then calculate current max area. Update max area,
*/

void updateCurrMax(stack<uint>& st, vector<uint> const& hist, uint& max, uint marker) {
  uint currMaxIdx = st.top();
  st.pop();
  uint currMax = hist[currMaxIdx] * (st.empty() ? (marker-1) : (marker-1-st.top()));
  if (currMax > max) {
    max = currMax;
  }
}

uint maxArea(vector<uint> const& hist) {
  stack<uint> rectToEvaluate;
  uint max_area = 0;
  uint i = 0;
  while(i < hist.size()) {
    if (rectToEvaluate.empty() || hist[rectToEvaluate.top()] <= hist[i]) {
      rectToEvaluate.push(i++);
    } else {
      updateCurrMax(rectToEvaluate, hist, max_area, i);
    }
  }
  
  while(!rectToEvaluate.empty()) {
    updateCurrMax(rectToEvaluate, hist, max_area, i);
  }
  return max_area;
}

// To execute C++, please define "int main()"
int main() {
  vector<uint> hist;
  hist.push_back(1);
  hist.push_back(2);
  hist.push_back(3);
  hist.push_back(4);
  hist.push_back(5);
  hist.push_back(3);
  hist.push_back(3);
  hist.push_back(2);
  hist.push_back(2);
  
  cout << "Max area: " << maxArea(hist) << endl;
  return 0;
}
