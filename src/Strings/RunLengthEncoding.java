package Strings;



/**
 *
 * http://rosettacode.org/wiki/Run-length_encoding#Java
 * http://www.geeksforgeeks.org/run-length-encoding/
 *
 * Complexity:
 * O(n), for both encode and decode, where n is the length of the input world.
 *
 * BB:
 * 18
 *
 */
public final class RunLengthEncoding {

    private RunLengthEncoding() { }

    public static String runLength (String source) {
        final StringBuilder target = new StringBuilder();

        for (int i = 0; i < source.length(); i++) {
            int runLength = 1;
            // if (i == a.length - 1 || a[i] != a[i + 1])
            // same as variable combinations.
            while (i < (source.length() - 1) && source.charAt(i) == source.charAt(i + 1)) {
                runLength++;
                i++;
            }
            target.append(runLength);
            target.append(source.charAt(i));
        }
        return target.toString();
    }


    public static String decodeWithoutPatterns(String decoder) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < decoder.length(); i = i + 2) {
            int frequency = Integer.parseInt(decoder.charAt(i) + "");

            for (int alphabetCount = 0; alphabetCount < frequency; alphabetCount++) {
                sb.append(decoder.charAt(i + 1));
            }
        }

        return sb.toString();
    }


    //    /*
    //     * pattern:
    //     * - compile:   static method that takes in the 'regex'
    //     * - matcher
    //     *
    //     * Matcher:
    //     * - group
    //     * - find()
    //     */
    //    public static String decode(String source) {
    //        StringBuffer dest = new StringBuffer();
    //        Pattern pattern = Pattern.compile("[0-9]+|[a-zA-Z]");  // means a number of unspecfied digits followed by a character
    //        Matcher matcher = pattern.matcher(source);  // taking in the input string
    //        while (matcher.find()) {  //  find either for (0-9) or find (a-Z)
    //            int runLength = Integer.parseInt(matcher.group());
    //            matcher.find();      //  find either for (0-9) or find (a-Z)
    //
    //            for (int i = 0; i < runLength; i++) {
    //                dest.append(matcher.group()); // groups (0-9) and (a-Z) ie 101A19b, means 101 will be grouped together.
    //            }
    //        }
    //        return dest.toString();
    //    }
    //


    /**
     * aaa
     * aaab
     */
    public static void main(String[] args) {
        String encodedStr = runLength("geeksforgeeks");
        System.out.println("-- the encoded value is --: " + encodedStr);
        System.out.println("-- the decoded value is --: " + decodeWithoutPatterns(encodedStr));
    }
}
