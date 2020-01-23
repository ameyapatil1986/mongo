package Arrays;

import java.util.Arrays;

/**
 * https://leetcode.com/articles/boats-to-save-people/
 * https://www.youtube.com/watch?v=4aBtEz8MrDw&list=PLi9RQVmJD2fYXgBAUfaN5MXgYPUTbzqeb&index=69
 *
 * Input: people = [1,2], limit = 3
 * Output: 1
 * Explanation: 1 boat (1, 2)
 *
 * Input: people = [3,2,2,1], limit = 3
 * Output: 3
 * Explanation: 3 boats (1, 2), (2) and (3)
 *
 *
 * The i-th person has weight people[i], and each boat can carry a maximum weight of limit.
 * Each boat carries at most 2 people at the same time, provided the sum of the weight of those people is at most limit.
 * Return the minimum number of boats to carry every given person.  (It is guaranteed each person can be carried by a boat.)
 */
public class BoatsToSavePeople {

    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int i = 0, j = people.length - 1;
        int ans = 0;

        while (i <= j) {
            ans++;
            //  boat carries at most 2 people at the same time
            if (people[i] + people[j] <= limit)
                i++;
            j--;
        }

        return ans;
    }
}
