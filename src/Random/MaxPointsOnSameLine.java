package Random;

import java.util.*;


/**
 * https://leetcode.com/problems/max-points-on-a-line/
 *
 * O(n2)
 */
public class MaxPointsOnSameLine {

    class Point {
        int x;
        int y;
    }

    public double getSlope(Point[] points, int i, int j) {
        // horizontal line ( when y is equal ) has slope of 0.
        // veritical line ( when x is equal ) is slope of infinity
        return (1.0 * (points[j].y - points[i].y)) / (points[j].x - points[i].x);
    }

    public int maxPoints(Point[] points) {
        if (points == null || points.length == 0)
            return 0;

        Map<Double, Integer> result = new HashMap<Double, Integer>();
        int max = 0;

        for (int i = 0; i < points.length; i++) {
            int duplicate = 1;
            int vertical = 0;

            for (int j = i + 1; j < points.length; j++) {
                //handle duplicates and vertical
                if (points[i].x == points[j].x) {
                    if (points[i].y == points[j].y) {
                        duplicate++;
                    } else {
                        vertical++;
                    }
                } else {
                    double slope = getSlope(points, i, j);
                    result.put(slope, result.getOrDefault(slope, 0) + 1);
                }
            }

            int maxPointsOnSlope = result.values().stream().max(Comparator.comparing(e -> e)).get();
            max = Math.max(max, Math.max(vertical, maxPointsOnSlope) + duplicate);
            result.clear();
        }

        return max;
    }
}
