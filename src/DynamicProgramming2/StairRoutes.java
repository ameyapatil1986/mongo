package DynamicProgramming2;

/**
 * EXAMPLE:
 * ========
 * steps 3, jumps 2.
 * https://bitbucket.org/ameyapatil/all-images/commits/e99bed0e6912481b6c62d44e71880d85ef3df5da
 */

/**
 * Link:
 * -----
 * http://www.geeksforgeeks.org/count-ways-reach-nth-stair/
 *
 * BB:
 * ---
 * 18
 *
 * Complexity:
 * ----
 * O(mn)
 *
 */
public class StairRoutes {

    public static int getNumberOfStairOptions(int stairCount, int jumps) {
        if (stairCount <= 0 || jumps <= 0) {
            throw new IllegalArgumentException("Bad jumps.");
        }

        // stair[0] means ground. it is not a step. stair[1] is the first step.
        int[] stairs = new int[stairCount + 1];

        for (int currentStep = 1; currentStep < stairs.length; currentStep++) {

            int currJumps =  Math.min(currentStep, jumps);

            for (int j = 1; j <= currJumps; j++) {
                // jump from the ground.
                if ((currentStep - j) == 0) {
                    stairs[currentStep] += 1;;
                }

                // j steps backward from current step.
                if ((currentStep - j) > 0) {
                    stairs[currentStep] += stairs[currentStep - j];
                }
            }
        }

        return stairs[stairs.length - 1];
    }


    public static void main(String[] args) {
        int x = getNumberOfStairOptions(1, 1);
        System.out.println(x);

        x = getNumberOfStairOptions(2, 2);
        System.out.println(x);

        x = getNumberOfStairOptions(3, 2);
        System.out.println(x);
    }
}
