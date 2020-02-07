package Intervals;

import java.util.*;

/**
 * https://www.programcreek.com/2012/12/leetcode-insert-interval/
 * https://leetcode.com/problems/insert-interval/
 * https://www.programcreek.com/2012/12/leetcode-insert-interval/
 */
public class InsertInterval {

    public boolean overlap(Interval i1, Interval i2) {
        return i1.start < i2.end && i2.start < i1.end;
    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        boolean anyPreviousOverlap = false;
        int i = 0;

        for (i = 0; i < intervals.size(); i++) {
            if (overlap(intervals.get(i), newInterval)) {
                newInterval = new Interval(Math.min(intervals.get(i).start, newInterval.start), Math.max(newInterval.end, intervals.get(i).end));
                anyPreviousOverlap = true;
            } else {
                if (anyPreviousOverlap) {
                    result.add(newInterval);
                    break;
                } else {
                    result.add(intervals.get(i));
                }
            }
        }

        for (; i < intervals.size(); i++) {
            result.add(intervals.get(i));
        }
        return result;
    }
}
