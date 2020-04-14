package BasicMath;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/maximum-swap/
 */
public class MaxSwap {

    public int maximumSwap(int num) {

        int[] A = new int[Integer.toString(num).length()];
        for (int i = A.length - 1; i  >= 0 ; i--) {
            A[i] = num % 10;
            num = num / 10;
        }

        Map<Integer, Integer> lastIndexMap = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            lastIndexMap.put(A[i], i);
        }

        for (int i = 0; i < A.length; i++) {
            for (int d = 9; A[i] < d; d--) {
                if (lastIndexMap.containsKey(d) && lastIndexMap.get(d) > i) {
                    int tmp = A[i];
                    A[i] = A[lastIndexMap.get(d)];
                    A[lastIndexMap.get(d)] = tmp;


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
