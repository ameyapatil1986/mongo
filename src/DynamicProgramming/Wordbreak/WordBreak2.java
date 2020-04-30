package DynamicProgramming.Wordbreak;

import java.util.*;


/**
 * https://www.programcreek.com/2014/03/leetcode-word-break-ii-java/
 */
public class WordBreak2 {

//    public boolean wordBreakWithDictionaryFrequency(String s, Map<String, String> wordDict) {
//
//        boolean[] isWordBreak = new boolean[s.length() + 1];
//
//        isWordBreak[0] = true;
//
//        for (int i = 1; i <= s.length() ; i++) {
//            for (int j = 0; j < i; j++) {
//                if (isWordBreak[j]) {
//                    if (wordDict.containsKey(s.substring(j, i))) {
//                        /**
//                         * reduce frequency in dictionary
//                         */
//
//                        isWordBreak[i] = true;
//                        break;
//                    }
//                }
//            }
//        }
//
//        return isWordBreak[s.length()];
//    }


    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> [] pos = new ArrayList[s.length() + 1];
        pos[0]=new ArrayList<String>();

        for(int i = 0; i < s.length(); i++) {
            if (pos[i] != null) {
                for(int j = i + 1; j <= s.length(); j++) {
                    String sub = s.substring(i,j);
                    if (wordDict.contains(sub)) {
                        if (pos[j]==null) {
                            List<String> list = new ArrayList<String>();
                            list.add(sub);
                            pos[j]=list;
                        } else {
                            // eg: bate and ate.
                            pos[j].add(sub);
                        }

                    }
                }
            }
        }

        if (pos[s.length()] == null) {
            return new ArrayList<String>();
        } else {
            List<String> result = new ArrayList<String>();
            dfs(pos, result, "", s.length());
            return result;
        }
    }

    public void dfs(List<String>[] pos, List<String> result, String curr, int i) {
        if(i==0){
            result.add(curr.trim());
            return;
        }

        for(String s: pos[i]){
            String combined = s + " " +  curr;
            dfs(pos, result, combined, i-s.length());
        }
    }
}
