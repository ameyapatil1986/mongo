package DynamicProgramming;

/**
 * https://www.geeksforgeeks.org/min-cost-path-dp-6/
 * https://algorithmsandme.com/minimum-cost-path-matrix/
 * https://leetcode.com/problems/minimum-path-sum/
 */
public class MinCostPath {

    /* A utility function that returns minimum of 3 integers */
    private static int min(int x, int y, int z) {
        if (x < y)
            return (x < z)? x : z;
        else
            return (y < z)? y : z;
    }

    private static int minCost(int cost[][], int m, int n) {
        int i, j;
        int totalCost[][] = new int[m][n];

        totalCost[0][0] = cost[0][0];

        /* Initialize first row of tc array */
        for (j = 1; j < n; j++) {
            totalCost[0][j] = cost[0][j] + totalCost[0][j - 1];
        }

        /* Initialize first column of total cost(tc) array */
        for (i = 1; i < m; i++) {
            totalCost[i][0] = cost[i][0] + totalCost[i - 1][0];
        }

        /* Construct rest of the tc array */
        for (i = 1; i < m; i++) {
            for (j = 1; j < n; j++) {
                totalCost[i][j] = cost[i][j] + min(totalCost[i - 1][j - 1], totalCost[i - 1][j], totalCost[i][j - 1]);
            }
        }

        return totalCost[m - 1][n - 1];
    }
}
