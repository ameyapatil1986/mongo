package Strings;

import java.util.*;


/**
 * https://leetcode.com/articles/find-and-replace-pattern/
 */
public class FindAndReplacePattern {

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> ans = new ArrayList();
        for (String word: words)
            if (match(word, pattern))
                ans.add(word);
        return ans;
    }

    public boolean match(String word, String pattern) {
        Map<Character, Character> M = new HashMap();
        for (int i = 0; i < word.length(); ++i) {
            char w = word.charAt(i);
            char p = pattern.charAt(i);
            if (!M.containsKey(w)) M.put(w, p);
            if (M.get(w) != p) return false;
        }

        Set<Character> set = new HashSet<>();
        for (char p : M.values()) {
            if (set.contains(p)) {
                return false;
            }
            set.add(p);
        }
        return true;
    }
}
