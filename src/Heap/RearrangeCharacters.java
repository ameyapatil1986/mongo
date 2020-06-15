package Heap;

// Java program to rearrange characters in a string
// so that no two adjacent characters are same.
import java.io.*;
import java.util.*;


/**
 * https://leetcode.com/problems/reorganize-string/
 * https://massivealgorithms.blogspot.com/2018/04/leetcode-767-reorganize-string.html
 */


/**
 * Complexity:
 * O(n log n)
 */
class RearrangeCharacters {
    // Function to rearrange character of a string
    // so that no char repeat twice
    static void rearrangeString(String str) {
        int n = str.length();

        Map<Character, Integer> charCount = new HashMap<>();
        for (int i = 0; i < n; i++) {
            charCount.put(str.charAt(i), charCount.getOrDefault(charCount.get(0), 0) + 1);
        }

        // Insert all characters with their frequencies into a priority_queue
        Queue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(Comparator.comparing(e -> -e.getValue()));
        pq.addAll(charCount.entrySet());

        StringBuilder stringBuilder = new StringBuilder();

        // work as the previous visited element
        // initial previous element be. ( '#' and
        // it's frequency '-1' )
        Map.Entry<Character, Integer> prev = null;

        // traverse queue
        while (pq.size() != 0) {

            // pop top element from queue and add it
            // to string.
            Map.Entry<Character, Integer> current = pq.poll();
            current.setValue(current.getValue() - 1);

            stringBuilder.append(current.getKey());

            // If frequency of previous character is less
            // than zero that means it is useless, we
            // need not to push it
            if (prev != null && prev.getValue() > 0)
                pq.add(prev);

            prev = current;
        }

        String result = stringBuilder.toString();
        // If length of the resultant string and original
        // string is not same then string is not valid
        if (n != result.length())
            System.out.println(" Not valid String ");
        else
            System.out.println(str);
    }

    // Driver program to test above function
    public static void main(String args[]) {
        String str = "bbbaa" ;
        rearrangeString(str);
    }
}

// This code is contributed by rachana soma
