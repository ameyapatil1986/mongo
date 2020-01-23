package Stack.Parenthesis;

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
 */
public class RemoveInvalidParen {

    final List<String> result = new ArrayList<String>();
    int max=0;

    public List<String> removeInvalidParentheses(String s) {
        if( s == null)
            return result;

        dfs(s, "", 0, 0);
        if(result.size()==0){
            result.add("");
        }

        return result;
    }

    public void dfs(String str, String constructedString, int countOpenLeftFacingBracket, int maxLeftFacingBracketCount) {

        if (str.length() == 0) {
            if(countOpenLeftFacingBracket == 0 && constructedString.length() != 0) {
                max = Math.max(max, maxLeftFacingBracketCount);
                // avoid duplicate result.
                if(!result.contains(constructedString)){
                    result.add(constructedString);
                }
            }
            return;
        }

        if (str.charAt(0)=='(') {
            dfs(str.substring(1), constructedString + "(", countOpenLeftFacingBracket + 1, maxLeftFacingBracketCount + 1); //keep (
            dfs(str.substring(1), constructedString,       countOpenLeftFacingBracket,     maxLeftFacingBracketCount); //drop (
        } else if (str.charAt(0)==')') {
            if (countOpenLeftFacingBracket > 0){
                dfs(str.substring(1), constructedString +")", countOpenLeftFacingBracket -1, maxLeftFacingBracketCount);
            }
            dfs(str.substring(1), constructedString, countOpenLeftFacingBracket, maxLeftFacingBracketCount);
        } else {
            dfs(str.substring(1), constructedString + str.charAt(0), countOpenLeftFacingBracket, maxLeftFacingBracketCount);
        }
    }
}
