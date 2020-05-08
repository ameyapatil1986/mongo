package Strings;

/**
 * https://www.youtube.com/watch?v=v0Cof91iWIk
 * https://massivealgorithms.blogspot.com/2017/01/leetcode-482-license-key-formatting.html
 *
 * O(n)
 */
public class LicenseKeyFormatting {

//    public String licenseKeyFormatting(String s, int k) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = s.length() - 1; i >= 0; i--)
//            if (s.charAt(i) != '-')
//                sb.append(sb.length() % (k + 1) == k ? '-' : "").append(s.charAt(i));
//        return sb.reverse().toString().toUpperCase();
//    }

   // Tanvi's simple code
    public static String createGroups(String s, int k) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != '-') {
                sb.append(s.charAt(i));
                count++;
                if (count == k && i > 0) {
                    sb.append('-');
                    count = 0;
                }
            }

        }
        return sb.reverse().toString().toUpperCase();
    }
}
