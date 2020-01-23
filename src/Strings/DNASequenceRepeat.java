package Strings;

import java.util.*;

/**
 * https://www.youtube.com/watch?v=97_vofsFauU
 * https://leetcode.com/problems/repeated-dna-sequences/
 */
public class DNASequenceRepeat {

    private static final Map<Character, Integer> encodings = new HashMap<>();

    static {
        encodings.put('A', 0);
        encodings.put('C', 1);
        encodings.put('G', 2);
        encodings.put('T', 3);
    }

    private final int Two_POW_9 = (int) Math.pow(2, 9);

    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> res = new HashSet<>();
        HashMap duplicates = new HashMap<Integer, String>();

        for (int i = 0, rhash = 0; i < s.length(); i++) {
            if (i > 9)
                rhash -= Two_POW_9 * encodings.get(s.charAt(i - 10));

            rhash = 2 * rhash + encodings.get(s.charAt(i));

            if (i > 8) {
                if (duplicates.get(rhash) != null) {
                    res.add(s.substring(i - 9, i + 1));
                } else {
                    duplicates.put(rhash, "");
                }
            }
        }

        return new ArrayList<>(res);
    }
}

