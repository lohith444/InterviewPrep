import java.io.*;
import java.util.*;

/*
 * Write a function that takes two parameters, 
 * as arguments, meetings and haveHours
 * and returns a optimized meeting objects.
 *
 * meetings: List of meeting objects (with name, hours)
 * haveHours: Total maximum hours available.
 *
 * Return meeting objects that is closest 
 * to the haveHours
 *
 * Examples:
 * meetings: [{'m1':4}, {'m2':3}, {'m3':5}]
 * haveHours: 8
 * Return: [{'m3':5}, {'m2':3}]
 *
 * [4,3,5], 8 => [5,3]
 *
 * Naive solution: T= O(n*n), S= O(n)
 *  - Create a result meetingObj array and maxHours.
 *  - For each object in meeting object:
 *     - Create a temporary meetingObj array.
 *     - Go through rest of the meeting objects, update totalHours and temporary meetingObj array.
 *     - If totalHours > maxHours:
         - update maxHours and meetingObj array.
 *  - Return meetingObj array.
 *
 *
 * Time/Space: O(meeting_size * haveHours)
 * Using dynamic program. (knap-sack problem)
 * Form matrix of 1-haveHours hrs to be made by meeting hrs.
 * hours: [3, 6, 2, 5] havHours = 10 
 * hours\total  1 2 3 4 5 6 7 8 9 10
 *     3        0 0 3 3 3 3 3 3 3 3
 *     6        0 0 3 3 3 6 6 6 9 9
 *     2        0 2 3 3 5 6 6 8 9 8
 *     5        0 2 3 3 5 6 7 8 9 10
 */

class Solution {
  public static class Meeting {
    String name;
    int hours;
    Meeting(String name, int hours){
      this.name = name;
      this.hours = hours;
    }
    void printMeeting(){
      System.out.println("Meeting: {" + this.name + ":" + this.hours + "}");
    }
  }
  
  public static Meeting[] optimizedMeetings(Meeting[] meetings, int haveHours) {
    int m = meetings.length;
    int n = haveHours;
    int[][] hoursTable = new int[m][n];
    
    // Fill up the first row.
    // If given hour is <= required hour:
    //     fill given hour.
    // Else fill zero.
    for(int j=1; j <= haveHours; j++) {
      if (meetings[0].hours <= j) {
        hoursTable[0][j-1] = meetings[0].hours;
      } else {
        hoursTable[0][j-1] = 0;
      }
    }
    
    for(int i=1; i < meetings.length; i++) {
      for(int j=1; j <= haveHours; j++) {
        int currHour = meetings[i].hours;
        if (currHour > j) {
          hoursTable[i][j-1] = hoursTable[i-1][j-1];
        } else if (currHour == j) {
          hoursTable[i][j-1] = currHour;
        } else {
          int diff_idx = j - currHour - 1;
          hoursTable[i][j-1] = Math.max(hoursTable[i-1][j-1], currHour + hoursTable[i-1][diff_idx]);
        }
      }
    }
    
    /*for(int i=0; i<m; i++) {
      for(int j=0; j<n; j++){
       System.out.print(hoursTable[i][j] + " ");
      }
      System.out.println();
    }*/
    
    Meeting[] result = new Meeting[m];
    int result_idx = 0;
    int j = n-1;
    int i = m-1;
    int maxHours = hoursTable[m-1][n-1];
    while (i>=0 && j>=0 && maxHours > 0) {
      if(i>0 && hoursTable[i][j] == hoursTable[i-1][j]) {
        i--;
      } else {
        result[result_idx++] = meetings[i];
        maxHours -= meetings[i].hours;
        j = maxHours - 1;
        i--;
      }
    }
    return Arrays.copyOfRange(result, 0, result_idx);
  }
  
  public static void main(String[] args) {
    Meeting obj1 = new Meeting("m1",3);
    Meeting obj2 = new Meeting("m2",6);
    Meeting obj3 = new Meeting("m3",5);
    Meeting obj4 = new Meeting("m4",2);
    Meeting[] input = new Meeting[]{obj1,obj2,obj3,obj4};
    Meeting[] result = optimizedMeetings(input, 10);
    for (int i=0; i<result.length; i++){
      result[i].printMeeting();
    }
  }
}
