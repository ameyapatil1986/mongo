package Intervals;

/**
 * https://leetcode.com/problems/gas-station/
 *
 * NOTE: the problem is not if you can circle or not. Problem is which node to begin from.
 *
 *
 * Input:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 *
 * Output: 3
 *
 * Explanation:
 * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 4. Your tank = 4 - 1 + 5 = 8
 * Travel to station 0. Your tank = 8 - 2 + 1 = 7
 * Travel to station 1. Your tank = 7 - 3 + 2 = 6
 * Travel to station 2. Your tank = 6 - 4 + 3 = 5
 * Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
 * Therefore, return 3 as the starting index.
 * Example 2:
 *
 * Input:
 * gas  = [2,3,4]
 * cost = [3,4,3]
 *
 * Output: -1
 *
 * Explanation:
 * You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
 * Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 0. Your tank = 4 - 3 + 2 = 3
 * Travel to station 1. Your tank = 3 - 3 + 3 = 3
 * You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
 * Therefore, you can't travel around the circuit once no matter where you start.
 */
public class GasStation {

    /**
     * https://baihuqian.github.io/2018-06-23-gas-station/
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // std
        int start = 0;
        int tank = 0;
        int deficit = 0;

        for (int i = 0; i < cost.length; i++) {
            tank += gas[i] - cost[i]; // gc
            if (tank < 0) {
                start = i + 1;
                deficit += tank;
                tank = 0;
            }
        }

        if (deficit + tank < 0) {
            return -1;
        } else {
            return start;
        }
    }
}
