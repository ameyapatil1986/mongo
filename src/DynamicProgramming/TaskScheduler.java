package DynamicProgramming;

import java.util.Arrays;

/**
 * https://leetcode.com/articles/task-scheduler/  ( has brilliant explanation )
 **
 * However, there is a non-negative interval n that means between two same tasks,
 * there must be at least n intervals that CPU are doing different tasks or just be idle.
 * You need to return the least number of intervals the CPU will take to finish all the given tasks.
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * n includes idle or non-idle.
 * Output: 8
 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 *
 * Explanation:
 * A and A are same tasks, between 2 A's there is a interval of atleast 2.
 * B and B are same tasks, between 2 B's there is a interval of atleast 2.
 */
public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        int[] charCount = new int[26];

        for (char c: tasks) {
            charCount[c - 'A']++;
        }

        charCount = Arrays.stream(charCount).filter(e -> e > 0).toArray();

        Arrays.sort(charCount);

        // take the char with max frequency
        int maxVal = charCount[charCount.length - 1] - 1;
        int idleSlots = maxVal * n;

        for (int i = charCount.length - 2; i >= 0; i--) {
            idleSlots -= Math.min(charCount[i], maxVal);
        }

        return idleSlots + tasks.length;
    }
}
