package BasicMath;

public class PowerOfThree {

    public boolean isPowerOfThree(int n) {
        if (n == 1)
            return true;

        boolean result = false;

        while (n > 0) {
            if (n % 3 == 0) {
                n = n / 3;
                if (n == 1)
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }
}
