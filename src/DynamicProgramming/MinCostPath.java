package DynamicProgramming;

/**
 * https://www.geeksforgeeks.org/min-cost-path-dp-6/
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
        int tc[][] = new int[m+1][n+1];

        tc[0][0] = cost[0][0];

        /* Initialize first row of tc array */
        for (j = 1; j <= n; j++) {
            tc[0][j] = cost[0][j] + tc[0][j - 1];
        }

        /* Initialize first column of total cost(tc) array */
        for (i = 1; i <= m; i++) {
            tc[i][0] = cost[i][0] + tc[i - 1][0];
        }

        /* Construct rest of the tc array */
        for (i = 1; i <= m; i++) {
            for (j = 1; j <= n; j++) {
                tc[i][j] = cost[i][j] + min(tc[i - 1][j - 1], tc[i - 1][j], tc[i][j - 1]);
            }
        }

        return tc[m][n];
    }
}
