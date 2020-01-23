package Backtrack;

import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;


/**
 * https://leetcode.com/problems/word-search/
 *
 * NavigableSet:
 * -------------
 * https://bitbucket.org/ameyapatil/all-images/commits/710077367a878ba43688176f6b5f5885098928bc
 *
 *
 * Reference:
 * ----------
 * http://fragmaticprogrammer.wordpress.com/2012/07/02/boggle-solver-2/
 * https://github.com/alextriana/boggle_solver
 * https://bitbucket.org/ameyapatil/pointstonote/commits/f4336caa952c5ef7d9e095d3bce7f47173fe1057
 * http://codereview.stackexchange.com/questions/33045/boggle-solver-in-java
 * http://codereview.stackexchange.com/questions/33428/reading-a-text-file-need-help-cleaning-exception-handling
 *
 * Reason for using navigable set:
 * -- navigation set gives us a prefix,
 * -- if prefix does not exist then obviously there is not point traversing the rest of word permutations
 *
 *
 * STATIC INITIALIZER:
 * -------------------
 *
 * Why should it be static:
 * http://stackoverflow.com/questions/1415955/private-final-static-attribute-vs-private-final-attribute
 *
 * I/O inside an intializer:
 * http://stackoverflow.com/questions/1044855/load-java-properties-inside-static-intializer-block/1044908#1044908
 *
 * Why not use a singleton:
 * http://agiletribe.wordpress.com/2013/10/08/dont-abuse-singleton-pattern/
 *
 * why not lazy initialization?
 * http://stackoverflow.com/questions/19481907/reading-a-text-file-of-dictionary-is-it-a-good-canditate-for-lazy-init
 *
 * error handling in static intializer block ?
 * http://stackoverflow.com/questions/19482202/is-it-standard-recommended-practice-to-covert-checked-to-unchecked-exceptions
 * http://stackoverflow.com/questions/2070293/exception-in-static-initialization-block/2070409#2070409 (check kevinarpe)
 *
 *
 * Complexity:
 * -----------
 * http://exceptional-code.blogspot.com/2012/02/solving-boggle-game-recursion-prefix.html
 * O((N*N)!) <-- mug up this answer for now.
 * Better solution lies in dynamic programming here: http://exceptional-code.blogspot.com/2012/02/solving-boggle-game-recursion-prefix.html
 * do dynamic programming if time permits else settle for this answer.
 *
 * BB:
 * ---
 * 1
 *
 * Complexity:
 * -----------
 * http://www.javaexperience.com/time-complexity-of-collection-classes/
 * http://stackoverflow.com/questions/29067904/complexity-of-treesets-subset-method
 */
public class Boggle {

    // modifier sequence: private static final int STUDENT_AGE = 18;
    // http://stackoverflow.com/questions/12801223/private-static-final-fields
    /**
     * Why declare it as static ?
     * http://stackoverflow.com/questions/1415955/private-final-static-attribute-vs-private-final-attribute
     * check jon skeets answer.
     */
    /**
     * In interview reply with a "Trie." Java does not have a trie thus workaround by using TreeSet,
     * http://stackoverflow.com/questions/4691166/how-to-implement-a-dictionary-trie-vs-hashtable-and-important-issues <- trie wins.
     * http://codereview.stackexchange.com/questions/33045/boggle-solver-in-java                                       <- trie wins.
     * http://en.wikipedia.org/wiki/Trie#Dictionary_representation                                                     <- trie wins.
     */
    private final NavigableSet<String> dictionary;
    private final char[][] m;

    /**
     * Note: NavigableSet is an interface inherting from Set.
     * But, navigableSet adds the new 'subset' api, which set does not offer.
     *
     * @param m
     * @param dictionary
     */
    public Boggle(char[][] m, NavigableSet<String> dictionary) {
        this.m = m;
        this.dictionary = dictionary;
    }

    private static enum Direction {
        W(-1, 0), NW(-1, -1), N(0, -1), NE(-1, 1), E(0, 1), SE(1, 1), S(1, 0), SW(1, -1);

        private int rowDelta;
        private int colDelta;

        Direction(int rowDelta, int colDelta) {
            this.rowDelta = rowDelta;
            this.colDelta = colDelta;
        }

        public int getRowDelta() {
            return rowDelta;
        }

        public int getColDelta() {
            return colDelta;
        }
    }

    public Set<String> solve() {
        final Set<String> validWords = new HashSet<String>();
        final boolean[][] visited = new boolean[m.length][m[0].length];

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                /**
                 * https://www.careercup.com/question?id=5890898499993600
                 * if (m[i][j] == 'f') {
                 *  traverseBoard(m, i, j, "", validWords, visited);
                 * }
                 *
                 */

                /**
                 * http://stackoverflow.com/questions/8172420/how-to-convert-a-char-to-a-string-in-java
                 */
                traverseBoard(m, i, j, "", validWords, visited);
            }
        }
        return validWords;
    }

    private void traverseBoard(char[][] m, int row, int col, String prefix, Set<String> validWords, boolean[][] visited) {
        if (row < 0 || row == m.length || col < 0 || col == m[0].length) return;

        String word = prefix + m[row][col];

        // word: "foo"
        // word + charmax: "foo" + *
        if (dictionary.subSet(word, word + Character.MAX_VALUE).isEmpty()) {
            return;
        }

        if (visited[row][col]) {
            return;
        }

        /**
         * https://www.careercup.com/question?id=5890898499993600
         * if (m[i][j] == stringtosearch.charAt(i)) { return true or false }
         * visited array is not required.
         */

        if (dictionary.contains(word)) {
            validWords.add(word);
        }
        visited[row][col] = true;

        for (Direction dir : Direction.values()) {
            int currRow = row + dir.getRowDelta();
            int currCol = col + dir.getColDelta();

            traverseBoard(m, currRow, currCol, word, validWords, visited);
        }

        visited[row][col] = false;
    }

    public static void main (String[] args) {
        char[][] board = { {'a', 'b', 'c', 'd' },
            {'n', 'x', 'p', 'q' },
            {'k', 't', 'i', 's' },
            {'e', 'f', 'g', 's' },
        };

        NavigableSet<String> dictionary = new TreeSet<>();
        dictionary.add("sit");
        dictionary.add("ban");
        dictionary.add("git");
        dictionary.add("pig");
        dictionary.add("six");
        dictionary.add("sip");
        dictionary.add("tip");
        dictionary.add("pit");

        Boggle boggle = new Boggle(board, dictionary);
        for (String str :  boggle.solve()) {
            System.out.println(str);
        }
    }
}
