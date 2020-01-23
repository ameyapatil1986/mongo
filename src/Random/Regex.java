package Random;


//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;

/**
 * FB:
 * https://www.careercup.com/question?id=5293306254721024
 */

/**
 * Issyes to deal with:
 * - +1 and -1 and nothing for pos
 * - renaming
 * - general cleanup
 *
 * Todo:
 * - constants vs enum
 *
 *
 * The way regex is usually done.
 *
 * Companies:
 * http://www.glassdoor.com/Interview/Write-the-actual-code-to-parse-a-regular-expression-including-which-stands-for-0-or-more-characters-which-stands-QTN_193841.htm
 *
 *
 * http://www.vogella.com/articles/JavaRegularExpressions/article.html
 * https://bitbucket.org/ameyapatil/pointstonote/src/7e947823591f890e41ed8bdf4bb4bb7bfa5773f9/src/RegexParser.java
 *
 * But for sake of interview the regex is simplified.
 * -------------------------------
 * IT IS DONE EXACTLY THE SAME AS:
 * -------------------------------
 * http://codereview.stackexchange.com/questions/10776/better-implementation-of-a-simplified-regular-expression-engine
 * http://www.careercup.com/question?id=17697664
 *
 * Design a regex parser for: * and .
 *
 * Reference/Code review:
 * http://codereview.stackexchange.com/questions/43579/regex-parser-implementing-dot-star-and-question-mark
 *
 * BB: 4
 *
 * Complexity:
 *
 * O(n).
 * http://stackoverflow.com/questions/4378455/what-is-the-complexity-of-regular-expression
 *
 * O (nm), n and m, length or regex and string
 * Still not sure, but currently as of May 26th 2016, it appears to be O(n + m).
 * to understand complexity more consider: a******b******c , and axbxc
 */
public final class Regex {

    /* should we have char constants
    * - http://stackoverflow.com/questions/19554605/is-it-worth-keeping-constants-for-primitives-in-java
    */

    private Regex() {};

    /*
     * http://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
     * If everything can be used for enums then when to use the constants ?
     * http://stackoverflow.com/questions/613837/enums-and-constants-which-to-use-when
     * http://stackoverflow.com/questions/7418214/make-this-private-enum-static-or-not
     */
    private static enum Wildcards {
        STAR('*'), DOT('.'), QUESTION('?');

        private final char wildCard;

        Wildcards (char wildCard) {
            this.wildCard = wildCard;
        }

        public char getWildCard() {
            return this.wildCard;
        }
    }


    /**
     * The index of first non-star character following first occurence of star
     * eg: if regex is a**b, then index is startIndex is 1 and return value is 3 (index of b)
     *
     * @param regex          : the regex string.
     * @param firstStarIndex : index of the first occurene of * after a non *, eg: a**b, then starIndex would be 1
     * @return               : the index of first non-star character following first occurence of star
     */
    private static final int getNonWildCardIndex(String regex, int regexPos, Wildcards foo) {
        for (int k = regexPos; k < regex.length(); k++) {
            if (regex.charAt(k) != foo.getWildCard()) {
                return k;
            }
        }
        return regex.length();
    }

    /**
     * A valid wildcard tail is a tail of ?' s and *'s.
     * Its a tail which matches 0 characters
     *
     * @param regex         The input regex
     * @param index         The index from which the wild card check must be initiated.
     * @return  true is the resulting wildcard matches empty string.
     */
    /**
     * http://stackoverflow.com/questions/1932399/is-it-a-bad-idea-to-declare-a-final-static-method
     */
    private static final boolean validWildCardTail(String regex, int index) {
        assert regex != null;

        for (int i = index; i < regex.length(); i++) {
            if (regex.charAt(i) !=  Wildcards.QUESTION.getWildCard() &&
                regex.charAt(i) !=  Wildcards.STAR.getWildCard()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if we have scanned the entire string for regex match.
     * If not, it means some characters from strPos in the input string do not match regex.
     *
     * @param str       The input string.
     * @param strPos    The current string index
     * @return  true if entire string has been scanned for regex match.
     */
    private static final boolean endOfStringCheck(String str, int strPos) {
        return str.length() == strPos;
    }

    /**
     * Checks if the regex matches matches the empty string. At this point input string has been traversed.
     * Only the regex needs to be travelled to.
     *
     * @param regex       The input regex
     * @param regexPos    The current regex index.
     * @return  true if regex matches the input string
     */
    private static final boolean endOfRegexCheck (String regex, int regexPos) {
        if (regexPos == regex.length()) return true;

        // example: regex: "colou?*" string "colou"
        if (regex.charAt(regexPos) == Wildcards.QUESTION.getWildCard() ||
            // example: regex: "colou?*" string "colo"
            (regexPos < (regex.length() - 1)) && regex.charAt(regexPos + 1) == Wildcards.QUESTION.getWildCard() ||
            // example: regex: "****",  string: ""
            regex.charAt(regexPos) == Wildcards.STAR.getWildCard()) {

            // check the  `regexPos + 1 argument`, its very sensible. regixPos is already checked. thus we start check from the next position.
            return validWildCardTail(regex, regexPos + 1);
        } else {
            return false;
        }
    }

    private static final boolean matcher(String regex, int regexPos, String str, int strPos) {
        assert regexPos >= 0 && regexPos <= regex.length();
        assert strPos >= 0 && strPos <= str.length(); // it should break when its == strPos, > strpos is an impossible condition.

        while ((regexPos < regex.length() && strPos < str.length())) {;

            /*
             * regex.charAt(i) == str.charAt(j)  is a boolean evaluation where swich case should / can be avoided.
             * http://stackoverflow.com/questions/427760/when-to-use-if-else-if-else-over-switch-statments-and-vice-versa
             */
            if (regex.charAt(regexPos) == str.charAt(strPos) || regex.charAt(regexPos) == Wildcards.DOT.getWildCard()) {
                regexPos++;
                strPos++;
            }
            /*
             *  nested if else is better than non-nested.
             *  http://www.macs.hw.ac.uk/~pjbk/pathways/cpp1/node99.html
             */
            else if (regex.charAt(regexPos) == Wildcards.STAR.getWildCard()) {
                int nextNonStarIndex = getNonWildCardIndex(regex, regexPos,  Wildcards.STAR);

                /*
                 * For a case where regex and string, both have not yet reached the last char but regex is all *'s till end.
                 * Eg:  Regext abc*** && String is abcxyz
                 */
                if (nextNonStarIndex == regex.length()) return true;

                for (; strPos < str.length(); strPos = strPos + 1) {
                    if (matcher(regex, nextNonStarIndex, str, strPos)) return true;
                }

                /*
                 *  we can return early if we want to, its only a matter of our choice.
                 *  http://stackoverflow.com/questions/4838828/why-should-a-function-have-only-one-exit-point
                 */
                return false;
            } else {
                // regex  : colou?r
                // string : colour
                if (regex.charAt(regexPos) == Wildcards.QUESTION.getWildCard()) {
                    return matcher(regex, getNonWildCardIndex(regex, regexPos,  Wildcards.QUESTION), str, strPos);
                }

                // regex: colou?r
                // regex: color
                if (regexPos < (regex.length() - 1) && regex.charAt(regexPos + 1) ==  Wildcards.QUESTION.getWildCard()) {
                    return matcher(regex, getNonWildCardIndex(regex, regexPos + 1,  Wildcards.QUESTION), str, strPos);
                }

                return false;
            }
        }

        // consider if regex is: abc and string is abcd
        return endOfStringCheck(str, strPos) && endOfRegexCheck(regex, regexPos);
    }


    /**
     * Matches the regex with the string with the following rules.
     *  *  means 0 or more number of any char matches
     *  .  means just a single any char match.
     *  ?  means the immediate preceding character can exist or not. eg: colour? matches color and colour
     *
     * @param regex  : The regex to match the string again
     * @param str    : The string to be matched.
     * @return       : true / false
     */
    public static final boolean matches(String regex, String str) {
        return matcher(regex, 0, str, 0);
    }

    public static void main(String[] args) {

//        assertTrue(Regex.matches("colou?**rs", "colours"));
//        assertTrue(Regex.matches("colou?**rs", "colors"));
//
//        assertTrue(Regex.matches("colou**?rs", "colours"));
//        // Assert.assertTrue(RegexToy.matches("colou**?rs", "colors")); <---- exlusive case.
//
//        assertTrue(Regex.matches("colou?**r", "colour"));
//        assertTrue(Regex.matches("colou?**r", "color"));
//
//        assertTrue(Regex.matches("colou?*", "colou"));
//        assertTrue(Regex.matches("colou?*", "colo"));
//
//        assertTrue(Regex.matches("colou?r", "colour"));
//        assertTrue(Regex.matches("colou?r", "color"));
//
//        assertTrue(Regex.matches("colou?*?r", "colour"));
//        assertTrue(Regex.matches("colou?*?r", "color"));
//
//        // Success cases
//        assertTrue(Regex.matches("", ""));
//        assertTrue((Regex.matches("***", "")));
//
//        String[] regexList = { "abc****", "abc", "a*b*c", "****abc", "**a**b**c****", "abc*" };
//        String str1 = "abc";
//
//        for (String regex : regexList) {
//            assertTrue(Regex.matches(regex, str1));
//        }
//
//        String regex = "abc****";
//        String[] strList1 = { "abcxyz", "abcx", "abc" };
//        for (String str : strList1) {
//            assertTrue(Regex.matches(regex, str));
//        }
//
//        regex = "***abc";
//        String[] strList2 = { "xyzabc", "xabc", "abc" };
//        for (String str : strList2) {
//            assertTrue(Regex.matches(regex, str));
//        }
//
//        assertTrue(Regex.matches("a.c", "abc"));
//        assertTrue(Regex.matches("a*.*c", "abc"));
//
//
//        assertTrue(Regex.matches("a*.b.*c", "axxbxxbxxc"));
//
//        // Fail cases.
//        assertFalse(Regex.matches("abc", "abcd"));
//        assertFalse(Regex.matches("*a", "abcd"));
//        assertFalse(Regex.matches("a", ""));
//        assertFalse(Regex.matches(".a*c", "abc"));
//        assertFalse(Regex.matches("a.*b", "abc"));
//        assertFalse(Regex.matches("..", "abc"));
//        assertFalse(Regex.matches("", "abc"));
//
//        // exceptions
//        try {
//            Regex.matches(null, "abc");
//            // Assert.fail();
//        } catch (NullPointerException e) {
//        }
//
//        try {
//            Regex.matches("abc", null);
//            //  Assert.fail();
//        } catch (NullPointerException e) {
//        }

    }
}
