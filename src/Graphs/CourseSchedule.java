package Graphs;

import java.util.*;


/**
 * https://leetcode.com/problems/course-schedule/
 *
 * Input: 2, [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0. So it is possible.
 *              Thus course 1 depends on course 0.
 *              1 -> 0
 */
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        GraphCycleDetection<Integer> graph = new GraphCycleDetection<>();
        for(int[] a: prerequisites) {
            /**
             *  for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
             */
            graph.addNode(a[0]);
            graph.addNode(a[1]);

            graph.addEdge(a[0], a[1], 0);
        }

        return DAGCycleDetection.cycle(graph);
    }
}
