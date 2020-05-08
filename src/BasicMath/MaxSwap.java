package BasicMath;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/maximum-swap/
 */
public class MaxSwap {

    public int maximumSwap(int num) {

        // convert number into an array
        int[] A = new int[String.valueOf(num).length()];
        for (int i = A.length - 1; i  >= 0 ; i--) {
            A[i] = num % 10;
            num = num / 10;
        }

        Map<Integer, Integer> lastIndexMap = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            lastIndexMap.put(A[i], i);
        }

        for (int i = 0; i < A.length; i++) {
            int num1 = A[i];
            for (int num2 = 9; num1 < num2; num2--) {
                if (lastIndexMap.containsKey(num2) && lastIndexMap.get(num2) > i) {

                    // swap indexes.
                    A[i] = num2;
                    A[lastIndexMap.get(num2)] = num1;


                    // convert array back to the number.
                    int number = 0;
                    int base = 10;
                    for (int k = A.length - 1; k  >= 0 ; k--) {
                        number = number + A[k] * base;
                        base = base * 10;
                    }
                    return number;

                }
            }
        }

        return num;
    }
}
