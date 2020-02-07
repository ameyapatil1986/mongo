package Intervals;

import java.util.*;

/**
 * https://leetcode.com/problems/meeting-rooms-ii/
 *
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class MeetingRooms2 {

    public int minMeetingRooms(Interval[] intervals) {

        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];


        for(int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i].start;
            end[i] = intervals[i].end;
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int max_count = 0;
        int room_count = 0;
        int e = 0;
        for(int s = 0; s < start.length; s++) {
            room_count++;
            for(; end[e] <= start[s]; e++) {
                room_count--;
            }
            max_count = Math.max(max_count, room_count);
        }

        return max_count;
    }
}
