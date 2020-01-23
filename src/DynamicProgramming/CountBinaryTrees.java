package DynamicProgramming;

/**
 * https://www.youtube.com/watch?v=YDf982Lb84o&t=306s
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/CountNumberOfTreesInBST.java
 */
public class CountBinaryTrees {

    int countTreesRec(int numKeys) {
        if (numKeys <= 1) {
            return(1);
        }
        else {
            int sum = 0;
            int left, right, root;
            for (root = 1; root <= numKeys; root++) {
                left = countTreesRec(root - 1);
                right = countTreesRec(numKeys - root);
                sum += left*right;
            }
            return (sum);
        }
    }

    public int countTrees(int n) {
        int T[] = new int[n + 1];
        T[0] = 1;
        T[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                T[i] +=  T[j] * T[i-j-1];
            }
        }

        return T[n];
    }

}
