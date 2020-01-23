package BasicMath;


/**
 * n = 5: There is one 5 and 3 2s in prime factors of 5! (2 * 2 * 2 * 3 * 5). So count of trailing 0s is 1.
 *
 * n = 11: There are two 5s and three 2s in prime factors of 11! (2 8 * 34 * 52 * 7). So count of trailing 0s is 2.
 *
 * We can easily observe that the number of 2s in prime factors is always more than or equal
 * to the number of 5s. So if we count 5s in prime factors, we are done
 */
public class TrailingZeros {

    public static int getTrailing0InFactorial(int num) {
        if(num < 0)
            return -1;

        int count = 0;
        for (int i = 5; (num / i) > 0; i = i * 5) {
            count = count + num/i;
        }
        return count;
    }

    public static void main(String[] args) {
        // System.out.println(getTrailing0InFactorial(5));
        System.out.println(getTrailing0InFactorial(20));
    }
}
