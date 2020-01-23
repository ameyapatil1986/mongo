package Arrays;

/**
 * https://leetcode.com/problems/permutations/
 * https://leetcode.com/problems/subsets-ii/
 *
 * Sample example:
 * https://bitbucket.org/ameyapatil/all-images/src/c24474c2d4c77af9d1d715d93a83085f1b854e9e/permute.JPG?at=master&fileviewer=file-view-default
 *
 */

/**
 * Reference:
 * Recursive solution: http://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
 * Non-Recursive: Same technique, as this code, but use C language.
 *
 * Diagram:
 * https://bitbucket.org/ameyapatil/all-images/src/c24474c2d4c77af9d1d715d93a83085f1b854e9e/permute.JPG?at=master
 *
 * Complexity: O (n * n!) O(n!) to compute and O(n) to print
 *  http://stackoverflow.com/questions/5363619/complexity-of-recursive-string-permutation-function
 *
 * Interview: Microsoft Phone screen.
 *
 * BB: 2
 *
 */
public class Permutations {

    private static void swap(char ch[], int i, int j) {
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
    }

    public static void permute(String word) {
        /*
         * To print it in lexicographical order: Sort them before:
         * http://www.geeksforgeeks.org/lexicographic-permutations-of-string/
         */
        doPermute(word.toCharArray(), 0);
    }

    /**
     * Correct methpd:
     * http://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
     *
     * Diagram:
     * https://bitbucket.org/ameyapatil/all-images/src/c24474c2d4c77af9d1d715d93a83085f1b854e9e/permute.JPG?at=master
     */
    public static void doPermute(char word[], int level) {
        if (level == (word.length - 1)) {
            /*
             * Another question.
             * Given london, NYC, Paris, print all combinations such as:
             * I want to goto L, NYC, Paris
             * I want to goto L, Paris, NYC
             * I want to goto NYC, Paris, L
             * ..
             * ..
             * etc.
             * Instead of char[], pass in a String.
             */
            System.out.println(word);
            return;
        }

        for (int i = level; i < word.length; i++) {
            swap (word, level, i);
            doPermute(word, level + 1);
            swap (word, level, i);
        }
    }

    public static void main (String args[]) {
        permute("abc");
        System.out.println("========================================================");
    }
}
