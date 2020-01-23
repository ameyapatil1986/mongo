package DynamicProgramming;

/**
 * https://leetcode.com/problems/jump-game/
 *
 * http://www.careercup.com/question?id=10130965
 * http://codereview.stackexchange.com/questions/38142/jump-game-explained-on-http-www-careercup-com-questionid-10130965
 * Latest: http://codereview.stackexchange.com/questions/44482/jump-game-to-find-minimum-hops-from-source-to-destination
 * http://stackoverflow.com/questions/9041853/interview-puzzle-jump-game
 * http://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/
 *
 * From http://stackoverflow.com/questions/9041853/interview-puzzle-jump-game
 *
 *
 * Complexity:
 * O(n2) - by dynamic programming.
 *
 * BB: 8
 */
public final class JumpGame {

    private JumpGame() {}

    /*
     * definition of a jump:
     * a[1] = 2
     * then jump is 'index ie 1' + 'arr[index]  ie 2' ie array index 3.
     */
    public static int dynamicProgramming(int[] a) {
        if (a.length == 0 || a[0] <= 0) {
            throw new IllegalArgumentException("dude - stop giving me bad input.");
        }

        int[] jump = new int[a.length];
        jump[0] = 0;

        for (int i = 1; i < a.length; i++) {

            jump[i] = Integer.MAX_VALUE;
            boolean jumpReachedTillcurrIndex = false;

            for (int j = 0; j < i; j++) {

                // can I jump from index j to index i, where j < i ?
                if ((j + a[j]) >= i) {
                    jump[i] = Math.min(jump[j] + 1, jump[i]);
                    jumpReachedTillcurrIndex = true;
                }
            }

            if (!jumpReachedTillcurrIndex) {
                throw new IllegalArgumentException("index i is bad: " + i);
            }
        }

        return jump[a.length - 1];
    }


    /**
     * mug up: {2,3,1,1,4};
     */
    public static void main(String[] args) {

    }

}
