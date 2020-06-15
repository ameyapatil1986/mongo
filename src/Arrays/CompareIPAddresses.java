package Arrays;

/**
 * https://leetcode.com/problems/compare-version-numbers/
 */
public class CompareIPAddresses {

    public int compareVersion(String version1, String version2) {
        if (version1 == null || version2 == null) return 0;

        String[] split1 = version1.split("\\.");
        String[] split2 = version2.split("\\.");

        int i = 0, j = 0, len1 = split1.length, len2 = split2.length;

        // compare the version till we have same number of subversions
        while (i < len1 && j < len2) {
            int value1 = Integer.parseInt(split1[i++]);
            int value2 = Integer.parseInt(split2[j++]);
            if (value1 > value2) {
                return 1;
            } else if (value1 < value2) {
                return -1;
            }
        }

		// any versions left out due to uneven numbers of subversions, should be handled separately
        while(i < len1) {
            int value1 = Integer.parseInt(split1[i++]);
            if (value1 > 0) {
                return 1;
            }
        }

        while(j < len2) {
            int value2 = Integer.parseInt(split2[j++]);
            if (value2 > 0) {
                return -1;
            }
        }

        return 0;
    }

}
