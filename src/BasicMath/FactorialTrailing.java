package BasicMath;

/**
 * https://www.youtube.com/watch?v=3Hdmv_Ym8PI
 */
public class FactorialTrailing {

    public int trailingZeroes(int n) {
       int numberOfFives = 0;

       while (n >= 5) {
           numberOfFives += n / 5;  // Math.floor(n/5)
           n = n / 5;               // Math.floor(n/5)
       }

        return numberOfFives;
    }
}
