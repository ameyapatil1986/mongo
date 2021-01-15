package DynamicProgramming2;

/**
 * https://www.youtube.com/watch?v=YDf982Lb84o&t=306s
 * https://www.youtube.com/watch?v=JrTKVvYhT_k
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/CountNumberOfTreesInBST.java
 */
public class CountBinaryTrees {

    public int countTrees(int n) {
        int T[] = new int[n + 1];
        T[0] = 1;
        T[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                T[i] +=  T[j] * T[i - j - 1];
            }
        }

        return T[n];
    }

}
