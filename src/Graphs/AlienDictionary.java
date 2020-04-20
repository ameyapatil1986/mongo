package Graphs;

import java.util.*;


/**
 * https://zhuhan0.blogspot.com/2017/06/leetcode-269-alien-dictionary.html
 */
public class AlienDictionary {

    /**
     * If c1 comes before c2 then there is an edge from c1 -> c2.
     */
    public String alienOrder(String[] words) {

        if (words == null || words.length == 0)
            return "";

        Map<Character, Set<Character>> map = new HashMap<Character, Set<Character>>();
        Map<Character, Integer> inDegree = new HashMap<Character, Integer>();
        StringBuilder sb = new StringBuilder();

        // According to Leetcode:  There may be multiple valid order of letters, return any one of them is fine.
        // Thus, if there is only 1 word in the dictionary called "abcd" then any order is fine.
        // i.e. : abcd is same as bdca.
        for(String s: words){
            for(char c: s.toCharArray()){
                inDegree.put(c, 0);
            }
        }

        // compare each word and its pre-word char by char,
        // if different, since c1 is in front of c2, put c2 into c1's next set, c2 in-dgree + 1
        for(int i = 0; i < words.length - 1; i++) {
            String cur = words[i];
            String next = words[i + 1];
            // using longer one
            int length = Math.min(cur.length(), next.length());

            for (int j = 0; j < length; j++) {
                char c1 = cur.charAt(j);
                char c2 = next.charAt(j);

                if (c1 != c2) {
                    final Set<Character> set = map.getOrDefault(c1, new HashSet<>());

                    if (!set.contains(c2)) {
                        set.add(c2);
                        /**
                         * In degree of 2 is possible with following example:
                         * a
                         * c a
                         * c b
                         * b b
                         * 
                         */
                        inDegree.put(c2, inDegree.get(c2) + 1);
                    }

                    // there would be cycles without this break
                    // eg: ab and ba.
                    break;
                }
            }
        }

        // topological sort via BFS
        final Queue<Character> q = new LinkedList();
        // put all 0 in-degree into queue
        for(char c: inDegree.keySet()) {
            if (inDegree.get(c) == 0) q.add(c);
        }

        while (!q.isEmpty()) {
            char c = q.poll();
            sb.append(c);

            if (map.containsKey(c)) {
                for (char c2: map.get(c)) {
                    // all next chars' in-degree abstract 1
                    inDegree.put(c2, inDegree.get(c2) - 1);
                    if(inDegree.get(c2) == 0) q.add(c2);
                }
            }
        }

        /**
         *
         * apqr
         * bpqr
         * apqr
         * bpqr
         *
         */
        if(sb.length() != inDegree.size()) return "";

        return sb.toString();
    }
}
