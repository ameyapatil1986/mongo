package DynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/text-justification/
 *
 * O(n)
 *
 * Corner case:
 * Any line other than last line contains only 1 word.
 *
 */
public class TextJustification {

    public static List<String> textJustify(String[] words, int maxWidth) {
        final List<String> strings = new ArrayList<>();
        int i = 0;

        while (i < words.length) {
            int sum = 0;
            int startIndex = i;
            int trailingWhiteSpaces = 0;

            for ( ;i < words.length; i++) {
                sum = sum + words[i].length() + 1; // this 1 for space.

                if ((sum - 1) > maxWidth) { // subtracting 1 for space.
                    sum = sum - words[i].length() - 2;  // subtracting 2 again for space :)
                    trailingWhiteSpaces = maxWidth - sum;
                    break;
                }
            }

            StringBuilder sb = new StringBuilder();
            if (i == words.length) {
                for (int j = startIndex; j < i; j++) {
                    sb.append(words[j]);
                    sb.append(" ");
                }
            } else {
                int numberOfWords = i - startIndex;
                int spacesBetweenWords = trailingWhiteSpaces / numberOfWords;

                String space = " ";
                for (int x = 0; x < spacesBetweenWords; x++) {
                    space = space + " ";
                }

                for (int j = startIndex; j < i - 1; j++) {
                    sb.append(words[j]);
                    sb.append(space);
                }
                sb.append(words[i - 1]);
            }

            strings.add(sb.toString());
        }

        return strings;
    }


    public static void main(String[] args) {
        String[] arr = {"aaaaaaaa", "ameya", "suhas", "aaaaaaaa", "aarti", "abc", "a", "b", "c", "d", "ameya"};

        for (String str : textJustify(arr, 11)) {
            System.out.println(str);
        }
    }
}
