package Heap;

import java.util.*;


/**
 * https://leetcode.com/problems/k-closest-points-to-origin/
 */
public class KClosestPoints {

    class PointsData {
        int[] point;
        double distance;

        PointsData(int[] point, double distance) {
            this.point = point;
            this.distance = distance;
        }
    }

    public List<int[]> kClosest(int[][] points, int K) {
        HashMap<Double, List<Integer>> map = new HashMap<>();
        Queue<PointsData> pq = new PriorityQueue<>(Comparator.comparing(p -> p.distance));

        for (int i = 0; i < points.length; i++) {
            int[] p = points[i];
            Double distance = Math.sqrt((p[1]) * (p[1]) + (p[0]) * (p[0]));
            pq.add(new PointsData(p, distance));

            if (pq.size() > K) {
               pq.poll();
            }
        }

        List<int[]> point  = new ArrayList();
        while (!pq.isEmpty()) {
            point.add(pq.poll().point);
        }

        return point;
    }
}
