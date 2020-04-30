package BinaryTrees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 *
 * References:
 * http://www.geeksforgeeks.org/connect-nodes-at-same-level-with-o1-extra-space/
 * http://codereview.stackexchange.com/questions/41848/join-connect-all-levels-of-binary-tree-without-any-aux-storage
 *
 * Complexity:
 * O(n) - time complexity
 * O(1) - space complexity
 *
 * BB: 48
 *
 */
public class JoinLevelsWithoutAuxStore<E> {

    private TreeNode<E> root;

    public JoinLevelsWithoutAuxStore(List<? extends E> items) {
        create(items);
    }

    private static class TreeNode<E> {
        TreeNode<E> left;
        E item;
        TreeNode<E> right;
        TreeNode<E> sibling;

        TreeNode(TreeNode<E> left, E item, TreeNode<E> right, TreeNode<E> sibling) {
            this.left = left;
            this.item = item;
            this.right = right;
        }
    }

    /**
     * Takes in a BFS representation of a tree, and converts it into a tree.
     * here the left and right children of nodes are the (2*i + 1) and (2*i + 2)nd
     * positions respectively.
     *
     * @param items The items to be node values.
     */
    private void create (List<? extends E> items) {
        root = new TreeNode<E>(null, items.get(0), null, null);

        final Queue<TreeNode<E>> queue = new LinkedList<TreeNode<E>>();
        queue.add(root);

        final int half = items.size() / 2;

        for (int i = 0; i < half; i++) {
            if (items.get(i) != null) {
                final TreeNode<E> current = queue.poll();
                final int left = 2 * i + 1;
                final int right = 2 * i + 2;

                if (items.get(left) != null) {
                    current.left = new TreeNode<E>(null, items.get(left), null, null);
                    queue.add(current.left);
                }
                if (right < items.size() && items.get(right) != null) {
                    current.right = new TreeNode<E>(null, items.get(right), null, null);
                    queue.add(current.right);
                }
            }
        }
    }



    /**
     * Joins the level of the binary tree.
     * If any node is null, it is skipped in the process.
     */
    public void join1() {
        if (root == null) throw new IllegalStateException("the root cannot be null");

        TreeNode<E> firstNode = root;

        while (firstNode != null) {
            List<TreeNode<E>> nodeList= new ArrayList<TreeNode<E>>();

            for (TreeNode<E> currentNode = firstNode; currentNode != null; currentNode = currentNode.sibling) {
                if (currentNode.left != null) {
                    nodeList.add(currentNode.left);
                }
                if (currentNode.right !=  null) {
                    nodeList.add(currentNode.right);
                }
            }

            for (int i = 0; i < nodeList.size() - 1; i++) {
                nodeList.get(i).sibling = nodeList.get(i + 1);
            }

            firstNode = nodeList.get(0);
        }
    }

    /**
     * Joins the level of the binary tree.
     * If any node is null, it is skipped in the process.
     */
    public void joinAndBreakPrevious(int intendedLevel) {
        if (root == null) throw new IllegalStateException("the root cannot be null");

        TreeNode<E> firstNode = root;
        int level = 0;

        while (firstNode != null) {
            if (level == intendedLevel) {
                return;
            }

            List<TreeNode<E>> nodeList= new ArrayList<TreeNode<E>>();

            for (TreeNode<E> currentNode = firstNode; currentNode != null; ) {
                if (currentNode.left != null) {
                    nodeList.add(currentNode.left);
                }
                if (currentNode.right !=  null) {
                    nodeList.add(currentNode.right);
                }

                TreeNode<E> sibling = currentNode.sibling;
                currentNode.sibling = null;
                currentNode = sibling;
            }

            for (int i = 0; i < nodeList.size() - 1; i++) {
                nodeList.get(i).sibling = nodeList.get(i + 1);
            }

            firstNode = nodeList.get(0);
            level++;
        }
    }

//    /**
//     * Returns the levelorder representation for the given tree.
//     * Each granular item of this iterator is a set of items,
//     * where first element of set is the leftmost non-null item at that level
//     * and the last one would be the rightmost non-null item at same level.
//     *
//     * A next, advances to next level, and returns the level as a set.
//     *
//     * @return  the iterator for levelorder traversal
//     */
//    public Iterator<List<E>> levelOrderIterator() {
//        return new LevelOrderItr();
//    }
//
//    private class LevelOrderItr implements Iterator<List<E>> {
//        private TreeNode<E> node;
//
//        public LevelOrderItr() {
//            node = root;
//        }
//
//        @Override
//        public boolean hasNext() {
//            return node != null;
//        }
//
//        @Override
//        public List<E> next() {
//            if (!hasNext()) throw new NoSuchElementException("No more nodes remain to iterate");
//
//            final List<E> levelList = new ArrayList<E>();
//            TreeNode<E> leftMostNextLevelNode = null;
//
//            if (node != null) {
//                if (node.left != null) {
//                    leftMostNextLevelNode = node.left;
//                } else if (node.right != null) {
//                    leftMostNextLevelNode = node.right;
//                }
//
//                for (TreeNode<E> temp = node; temp != null; temp = temp.sibling) {
//                    levelList.add(temp.item);
//                }
//            }
//            node = leftMostNextLevelNode;
//
//            return levelList;
//        }
//
//        @Override
//        public void remove() {
//            throw new UnsupportedOperationException("Invalid operation for pre-order iterator.");
//        }
//    }


    /**
     * the only method conceivable to test is via the level iterator and printing it.
     * a non-complete tree should be used as testcase
     */
    public static void main(String[] args) {
//        Integer[] arr1 = {1, 2, 3, 4, null, null, 7};
//        List<Integer> list1 = new ArrayList<Integer>(Arrays.asList(arr1));
//
//        JoinLevelsWithoutAuxStore<Integer> joinLevels = new JoinLevelsWithoutAuxStore<Integer>(list1);
//        joinLevels.joinGivenLevel(2);
//
//        int ctr = 0;
//        Iterator<List<Integer>> itr = joinLevels.levelOrderIterator();
//        while (itr.hasNext()) {
//            List<Integer> list = itr.next();
//            System.out.println("Level: " + ctr);
//            for (Integer i : list) {
//                System.out.print(i + " - ");
//            }
//            ctr++;
//            System.out.println("");
//        }
    }
}
