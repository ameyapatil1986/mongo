package Strings.palindrome;


import java.util.stream.*;

public class Palindrome {

    public boolean isPalindromeUsingIntStream(String text) {
        String temp  = text.replaceAll("\\s+", "").toLowerCase();

        // end is exlusive.
        return IntStream.range(0, temp.length() / 2)
            .noneMatch(i -> temp.charAt(i) != temp.charAt(temp.length() - i - 1));
    }
}
