package Stack.Parenthesis;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

/**
 * https://leetcode.com/problems/remove-invalid-parentheses/
 *
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 *
 * Note: The input string may contain letters other than the parentheses ( and ).
 *
 * Example 1:
 *
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 *
 * Complexity:
 * O(length * 2 ^ (length/2))
 */
public class RemoveInvalidParen {

    final Set<String> result = new HashSet<String>();
    int max=0;

    public Set<String> removeInvalidParentheses(String s) {
        if( s == null)
            return result;

        dfs(s, 0, "", 0, 0);
        if(result.size()==0){
            result.add("");
        }

        return result;
    }

    public void dfs(String str, int index, String constructedString, int openBrace, int closeBrace) {
        if (openBrace > str.length()/2 || closeBrace > openBrace) {
            return;
        }

        if (index == str.length() && constructedString.length() != 0) {
            if(openBrace == closeBrace) {
                max = Math.max(max, constructedString.length());
                // avoid duplicate result.
                result.add(constructedString);
            }
            return;
        }

        if (str.charAt(index)=='(') {
            dfs(str, index++, constructedString + "(", openBrace + 1, closeBrace); //keep (
            dfs(str, index++, constructedString,       openBrace,  closeBrace); //drop (
        } else if (str.charAt(index)==')') {
            dfs(str, index++, constructedString +")", openBrace, closeBrace + 1);
            dfs(str, index++, constructedString, openBrace, closeBrace);
        } else {
            dfs(str, index++, constructedString + str.charAt(0), openBrace, closeBrace);
        }
    }

//    public void dfs(String str, String constructedString, int countOpenLeftFacingBracket, int maxLeftFacingBracketCount) {
//
//        if (str.length() == 0 && constructedString.length() != 0) {
//            if(countOpenLeftFacingBracket == 0) {
//                max = Math.max(max, maxLeftFacingBracketCount);
//                // avoid duplicate result.
//                if(!result.contains(constructedString)){
//                    result.add(constructedString);
//                }
//            }
//            return;
//        }
//
//        if (str.charAt(0)=='(') {
//            dfs(str.substring(1), constructedString + "(", countOpenLeftFacingBracket + 1, maxLeftFacingBracketCount + 1); //keep (
//            dfs(str.substring(1), constructedString,       countOpenLeftFacingBracket,     maxLeftFacingBracketCount); //drop (
//        } else if (str.charAt(0)==')') {
//            if (countOpenLeftFacingBracket > 0) {
//                dfs(str.substring(1), constructedString +")", countOpenLeftFacingBracket -1, maxLeftFacingBracketCount);
//            }
//            dfs(str.substring(1), constructedString, countOpenLeftFacingBracket, maxLeftFacingBracketCount);
//        } else {
//            dfs(str.substring(1), constructedString + str.charAt(0), countOpenLeftFacingBracket, maxLeftFacingBracketCount);
//        }
//    }
}
