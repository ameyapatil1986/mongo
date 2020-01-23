package Intervals;

import java.util.*;

class Interval {
    int start;
    int end;

    Interval(int x, int y) {
        this.start = x;
        this.end = y;
    }

    int getStart() {
        return start;
    }

    int getEnd() {
        return end;
    }

}



/**
 * https://leetcode.com/articles/interval-list-intersections/
 *
 * Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
 */
public class IntersectionOfTwoIntervals {

    class Solution {
        public Interval[] intervalIntersection(Interval[] A, Interval[] B) {
            List<Interval> ans = new ArrayList();
            int i = 0, j = 0;

            while (i < A.length && j < B.length) {
                // Let's check if A[i] intersects B[j].
                // lo - the startpoint of the intersection
                // hi - the endpoint of the intersection
                int maxStart = Math.max(A[i].start, B[j].start); //take max start
                int minEnd = Math.min(A[i].end, B[j].end); // take min end
                // max start - min end.
                if (maxStart <= minEnd)
                    ans.add(new Interval(maxStart, minEnd));

                // Remove the interval with the smallest endpoint
                if (A[i].end < B[j].end)
                    i++;
                else
                    j++;
            }

            return ans.toArray(new Interval[ans.size()]);
        }
    }
}
