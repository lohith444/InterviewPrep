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
 * 1. Sort in 
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
    Meeting[] result = new Meeting[meetings.length];
    for(int i = 0; i < meetings.length; i++) {
      result[i] = meetings[i];
    }
    return result;
  }
  
  public static void main(String[] args) {
    Meeting obj1 = new Meeting("m1",4);
    Meeting obj2 = new Meeting("m2",3);
    Meeting obj3 = new Meeting("m3",5);
    Meeting[] input = new Meeting[]{obj1,obj2,obj3};
    Meeting[] result = optimizedMeetings(input, 8);
    for(int i = 0; i < result.length; i++) {
      result[i].printMeeting();
    }
  }
}
