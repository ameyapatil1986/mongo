package Arrays;

/**
 * https://www.programcreek.com/2014/05/leetcode-first-bad-version-java/
 */
public class FirstBadVersion {

    public int firstBadVersion(int n) {
        int i = 1, j = n;

        while (i < j) {
            int m = (i + j) / 2;
            if (!isBadVersion(m)) {
                i = m + 1;
            } else {
                j = m;
            }
        }

        if (isBadVersion(i)) {
            return i;
        }

        return j;
    }
}
