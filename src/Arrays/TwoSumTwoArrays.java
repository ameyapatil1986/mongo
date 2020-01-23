package Arrays;

import java.util.*;

public class TwoSumTwoArrays {


    /**
     * [-6 ]   [ -2 ]  x = -8
     * [6  ]   [2   ]  x =  8
     * [-6  ]  [2   ]  x  = 4
     * [-6  ]  [2   ]  x = -4
     *
     */
    public static void checkSumTwoNumbers(int[] smallArr, int[] longArr, int x) {

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < smallArr.length; i++) {
            // arraylist is used since numbers can repeat.
            map.putIfAbsent(x - smallArr[i], new ArrayList<>()).add(i);
        }

        for (int i = 0; i < longArr.length; i++) {
            if (map.containsKey(longArr[i])) {
                // print i and map.get(longArr[i]);
            }
        }
    }




    public static void main() {

    }
}
