package BasicMath;

import java.util.Set;
import java.util.*;

/**
 * https://medium.com/@lenchen/leetcode-202-happy-number-718924d70eea
 */
public class HappyNumber {

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<Integer>();

        while(!set.contains(n)) {
            set.add(n);

            n = getSum(n);

            if(n==1)
                return true;
        }

        return false;
    }

    public int getSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += ( n % 10) * ( n % 10);
            n = n / 10;
        }
        return sum;
    }
}
