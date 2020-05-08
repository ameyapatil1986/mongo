package Arrays;

/**
 * https://www.programcreek.com/2014/05/leetcode-first-bad-version-java/
 */
public class FirstBadVersion {

    boolean isBadVersion(int x) {
        return true;
    }

    public int firstBadVersion(int n) {
        int left = 1, right = n;

        while (left < right) {
            int m = (left + right) / 2;
            if (!isBadVersion(m)) {
                left = m + 1;
            } else {
                right = m;
            }
        }

        return left;
    }
}
