package DynamicProgramming.Wordbreak;

import java.util.*;


/**
 * https://www.programcreek.com/2014/03/leetcode-word-break-ii-java/
 */
public class WordBreak2 {


    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<List<String>> pos = new ArrayList<>();
        pos.add(new ArrayList<String>());

        for(int i = 0; i < s.length(); i++) {
            if (pos.get(i) != null) {
                for(int j = i + 1; j <= s.length(); j++) {
                    String sub = s.substring(i,j);
                    if (wordDict.contains(sub)) {
                        if (pos.get(j)==null) {
                            List<String> list = new ArrayList<String>();
                            pos.set(j, list);
                        }
                        // eg: bate and ate.
                        pos.get(j).add(sub);
                    }
                }
            }
        }

        if (pos.get(s.length()) == null) {
            return new ArrayList<String>();
        } else {
            List<String> result = new ArrayList<String>();
            dfs(pos, result, "", s.length());
            return result;
        }
    }

    public void dfs(List<List<String>> pos, List<String> result, String curr, int i) {
        if(i==0){
            result.add(curr.trim());
            return;
        }

        for(String s: pos.get(i)){
            String combined = s + " " +  curr;
            dfs(pos, result, combined, i - s.length());
        }
    }
}
