package Strings;

/**
 * https://leetcode.com/problems/reverse-words-in-a-string-ii/
 */

/**
 * Reverse words of a string:
 * http://www.geeksforgeeks.org/reverse-words-in-a-given-string/
 * http://www.roseindia.net/answers/viewqa/Java-Beginners/14205-reverse-words-in-a-string(words-seperated-byone-or-more-spaces).html
 *
 * FB:
 * https://www.careercup.com/question?id=5717567253512192
 *
 * NOTE:
 * ------
 * 1. string.toChar : will not return an existing char arr, reference. it
 * would use additional space to create another char array, and return that new
 * reference.
 * 2. string.split : will create a new string array, use additional
 * space, and return that new reference.
 *
 * RULE OF THUMB:
 * --------------
 * 1. If string needs a modification  &&  added space is not allowed -> then accept only char array as input
 *
 * Complexity:
 * -----------
 * O (n) : n is number of characters of the string.
 * Note this is not an O(n2), infact its O (n  + n + (n/2))
 * n   - for initially reversing
 * n   - the outer for loop looking for space.
 * n/2 -
 *
 * Check:
 * http://tekmarathon.wordpress.com/2013/06/14/algorithm-to-reverse-words-of-a-string/
 * http://stackoverflow.com/questions/5895185/reverse-the-orders-of-words-time-complexity
 *
 * BB:
 * ---
 * 2
 *
 */
public final class ReverseWordsOfAString {

    private ReverseWordsOfAString () { }

    /**
     * This needs a modification to underlying structure.
     * Thus we will accept only a char array.
     *
     * We will not accept a string, because if we use a string then,
     * 1. we cannot reverse it as string is not mutable.
     * 2. string.toCharArry would consume extra space.
     *
     * This technique does not use any extra space + does a modification to a mutable string array.
     *
     * @param ch
     * @return
     */

    /**
     * https://leetcode.com/problems/reverse-string/
     */
    private static char[] reverseAString (char[] ch, int start, int end) {
        assert ch != null;

        for ( ; start < end; start++, end--) {
            char temp = ch[start];
            ch[start] = ch[end];
            ch[end] = temp;
        }

        return ch;
    }

    public static char[] reverseWordsOfAString (char[] ch) {
        if (ch == null) {
            throw new NullPointerException("The input string is null.");
        }


        /*
         * https://www.careercup.com/question?id=5106757177180160
         * simply comment the line below.
         */
        reverseAString(ch, 0, ch.length - 1);

        int prev = 0;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == ' ') {
                reverseAString (ch, prev, i - 1);
                prev = i + 1;
            }
        }

        // used to reverse strings when there is not a single " ".
        reverseAString (ch, prev, ch.length - 1);

        return ch;
    }

    /**
     * Technique:
     * Split and loop-reverse.
     *
     * Reference:
     * http://www.roseindia.net/answers/viewqa/Java-Beginners/14205-reverse-words-in-a-string(words-seperated-byone-or-more-spaces).html
     *
     * Note:
     * there is extra space used.
     * Also there is no need to modify and of the underlying char array.
     *
     * @param str
     */
    public static void reverseWordsOfStringWhenExtraCostIsAllowed (String str) {
        // String[] words consuming extra space.
        String[] words = str.split(" ");
        for (int i = words.length - 1; i >=0 ;i--) {
            System.out.print(words[i] + " ");
        }
    }


    public static void main(String[] args) {
        String[] strArray = {"simple string", "", "singleAlphabet", " ", " space at start", "space at end ", "comma, in between", "double  space"};

        for (String str: strArray) {

            System.out.println(" \n ---------------------------");
            /**
             * we dont care what client does to send us the data.
             * if client wants to consume added space by converting string to char array,
             * its clients fault.
             * In general we would, but in this case we dont bother at all.
             */
            char[] ch = str.toCharArray();
            reverseWordsOfAString(ch);
            for (char ch1 : ch) {
                System.out.print(ch1);
            }
            System.out.println(" \n ---------------------------");

            reverseWordsOfStringWhenExtraCostIsAllowed (str);
            System.out.println();
        }
    }
}
