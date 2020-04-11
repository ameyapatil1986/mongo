package BinaryTrees;

import java.util.HashMap;
import java.util.Map;

/**
 * Thinking:
 * --------
 * PUT (Same logic for BST and trie):   root is precreated for both dudes.
 * BST Tree: if left == null or right == null then attach else continue to next node
 * TRIE: if char == null then attach else continue to the next node
 *
 *
 * GET/FETCH: (check comments in the code below, but again the same thing.)
 *
 * NOTE:
 * At end looping through char array, at all cases of get /put/delete,  I have final node, and not NULL.
 *
 * TODO:
 * How to design the constructor ?
 * All the special characters.
 * Get all words in a prefix ?
 *
 *
 * Links
 * ------
 * http://stackoverflow.com/questions/19085886/how-to-design-constructors/19085919?noredirect=1#19085919
 * http://www.cs.duke.edu/~ola/courses/cps108/fall96/joggle/trie/Trie.java
 * http://codereview.stackexchange.com/questions/32010/trie-code-review-request-for-improvement
 *
 * BB : 44
 *
 * @author SERVICE-NOW\ameya.patil
 */
public class Trie {

    private TrieNode root;

    public Trie () {
        root = new TrieNode(new HashMap<Character, TrieNode>(), null);
    }

    /**
     * http://stackoverflow.com/questions/19085886/how-to-design-constructors/19085919?noredirect=1#19085919
     */
    private static class TrieNode {
        Map<Character, TrieNode> map;
        String meaning;

        public TrieNode(Map<Character, TrieNode> map, String meaning) {
            this.map = map;
            this.meaning = meaning;
        }
    }

    public void add (String word, String meaning) {
        TrieNode node = root;
        char[] ch = word.toCharArray();

        for (int i = 0; i < ch.length; i++) {
            //create a child node for each new alphabet.
            node.map.putIfAbsent(ch[i], new TrieNode(new HashMap<>(), null));
            node = node.map.get(ch[i]);
        }

        /*
         * Helper: for a word like CHD, we will have 4 nodes.
         * (root) --- (c-node) -- (h-node) -- (d-node)
         */
        node.meaning = meaning;
    }

    public String getMeaning (String word) {
        TrieNode node = root;
        char[] ch = word.toCharArray();
        int i = 0;

        /*
         * dont think of this like a tree.
         * think like it is a linked list, where node.next is dictated by the characters.
         */
        for (node = root; node != null && i < ch.length; node = node.map.get(ch[i]), i++);

        if (node == null) {
            return null;
        } else {
            return node.meaning;
        }
    }

    /**
     * Deletes all its children, but does not delete itself.
     */
    public void prune (String string) {
        TrieNode node = root;
        char[] ch = string.toCharArray();
        int i = 0;

        for (node = root; node != null && i < ch.length; node = node.map.get(ch[i]), i++);

        if (node == null) {
            return;
        }
        node.map.clear();
    }

//    public void print() {
//        printWhole(root);
//    }

//    private void printWhole(TrieNode node) {
//        if (node == null) {
//            return;
//        }
//        if (node.word != null) {
//            System.out.println("Word: " + node.word + " Meaning: " + node.meaning);
//        }
//        for (TrieNode n : node.map.values()) {
//            printWhole(n);
//        }
//    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("mouse", "rat");
        trie.add("cop", "police");
        trie.add("cope", "endure");

        System.out.println("Expected rat, Actual: " + trie.getMeaning("mouse"));
        System.out.println("Expected police, Actual: " + trie.getMeaning("cop"));
        System.out.println("Expected endure, Actual: " + trie.getMeaning("cope"));
        System.out.println("Expected null, Actual: " + trie.getMeaning("co"));

//        trie.print();

        trie.prune("cop");

        System.out.println("Expected police, Actual: " +trie.getMeaning("cop"));
        System.out.println("Expected null, Actual: " +trie.getMeaning("cope"));

//        trie.print();
    }
}
