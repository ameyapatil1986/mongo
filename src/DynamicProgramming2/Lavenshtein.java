package DynamicProgramming2;


/**
 * http://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Levenshtein_distance#Java
 * https://en.wikipedia.org/wiki/Levenshtein_distance
 *
 *
 * Diagrams:
 * ---------
 * https://bitbucket.org/ameyapatil/all-images/commits/dcac6504728b499afb27d6510a0c507885582f4b
 * Best-diagram:
 * https://bitbucket.org/ameyapatil/all-images/commits/8dcad40f015eab23c39c124fa60ae91a83b03b0d
 *
 *
 * Complexity:
 * -----------
 * O (n * m) - Time complexity, where n is length of first string and m is length of second string.
 * O (n * m) - Space complexity, where n is length of first string and m is length of second string.
 *
 * BB:
 * 11
 *
 * @author SERVICE-NOW\ameya.patil
 */
public final class Lavenshtein {

    private Lavenshtein() {}

    private static int minimum(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public static int computeLevenshteinDistance(String str1, String str2) {
        final int[][] distance = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++)
            distance[i][0] = i;
        for (int j = 0; j <= str2.length(); j++)
            distance[0][j] = j;

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    distance[i][j] = distance[i - 1][j - 1];
                } else {
                    distance[i][j] = minimum (distance[i - 1][j], distance[i][j - 1], distance[i - 1][j - 1]) + 1;
                }
            }
        }

        return distance[str1.length()][str2.length()];
    }

    /**
     * Example to mugup.
     * https://bitbucket.org/ameyapatil/all-images/commits/8dcad40f015eab23c39c124fa60ae91a83b03b0d
     */
    public static void main(String[] args) {
        int fo = computeLevenshteinDistance("foo", "foo");
        System.out.println(fo);

        int fo1 = computeLevenshteinDistance("fox", "foo");
        System.out.println(fo1);

        int fo2 = computeLevenshteinDistance("xpf", "foo");
        System.out.println(fo2);

        int fo3 = computeLevenshteinDistance("abcdefg", "abc");
        System.out.println(fo3);
    }
}
