package DynamicProgramming;

/**
 *
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green.
 * The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
 * For example, costs[0][0] is the cost of painting house 0 with color red;
 * costs[1][2] is the cost of painting house 1 with color green, and so on...
 * Find the minimum cost to paint all houses.
 *
 * [
 *             red     blue    green
 *   house1 [   17,     2,      17 ],
 *   house2 [   16,    16,       5 ],
 *   house3 [   14,     3,      19 ]
 * ]
 *
 * https://www.programcreek.com/2014/05/leetcode-paint-house-java/
 *
 */
public class  PaintHouse {

    public int minCost(int[][] costs) {
        if(costs==null||costs.length==0){
            return 0;
        }

        int[][] matrix = new int[costs.length][3];

        // copy first row.
        matrix[0][0] = costs[0][0];
        matrix[0][1] = costs[0][1];
        matrix[0][2] = costs[0][2];

        for (int i = 1; i < matrix.length; i++){
            matrix[i][0] += Math.min(matrix[i-1][1], matrix[i-1][2]);
            matrix[i][1] += Math.min(matrix[i-1][0], matrix[i-1][2]);
            matrix[i][2] += Math.min(matrix[i-1][0], matrix[i-1][1]);
        }

        int m = costs.length-1;
        return Math.min(Math.min(matrix[m][0], matrix[m][1]), matrix[m][2]);
    }



//    public int minCost(int[][] costs) {
//        if( costs == null || costs.length == 0)
//            return 0;
//
//        for(int i = 1; i < costs.length; i++) {
//            costs[i][0] += Math.min(costs[i-1][1], costs[i-1][2]);
//            costs[i][1] += Math.min(costs[i-1][0], costs[i-1][2]);
//            costs[i][2] += Math.min(costs[i-1][0], costs[i-1][1]);
//
//            // print matrix and see how things look.
//            for (int x = 0; x < costs.length; x++) {
//                for (int y = 0; y < costs[0].length; y++) {
//                    System.out.println(costs[x][y]);
//                }
//            }
//        }
//
//        int m = costs.length-1;
//        return Math.min(Math.min(costs[m][0], costs[m][1]), costs[m][2]);
//    }
}
