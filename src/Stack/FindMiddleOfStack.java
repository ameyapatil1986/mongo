package Stack;


//import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.EmptyStackException;


/**
 * References:
 * ------------
 * http://www.geeksforgeeks.org/design-a-stack-with-find-middle-operation/
 * http://codereview.stackexchange.com/questions/55384/stack-with-getmiddle-and-deletemiddle-operation
 * https://www.youtube.com/watch?v=450maTzSIvA
 *
 * BB:
 * 11
 *
 * Complexity:
 * O(1) - time for all
 *
 */
public class FindMiddleOfStack<T> {

    private Node<T> top;
    private Node<T> middle;
    private int size = 0;

    private static class Node<T> {
        Node<T> left;
        T item;
        Node<T> right;

        Node(Node<T> left, T item, Node<T> next) {
            this.left =  left;
            this.item = item;
            this.right = next;
        }
    }

    public void push(T item) {

        final Node<T> node = new Node<T>(null, item, top);
        if (top == null) {
            top = node;
            middle = node;
        } else {
            top.left = node;
            node.right = top;
            top = node;
        }

        /*
         * 1. node1
         * 2. node2 -> node1. size % 2 == 0, thus now middle = node2.
         */
        size++;
        if (size % 2 == 0) {
            middle = middle.left;
        }
    }


    public T pop() {
        if (top == null) {
            throw new EmptyStackException();
        }

        T item = top.item;
        top = top.right;

        // important, to not leak references.
        if (top != null) {
            top.left = null;
        }

        size--;
        /*
         * 1. node4 -> node3 -> node2 -> node1
         * 2. node4 is popped.
         * 3. node3 -> node2 -> node1. here size % 2 == 1, thus move to right.
         */
        if (size % 2 == 1) {
            middle = middle.right;
        }

//        if (top == null) {
//            middle = null;
//        }

        return item;
    }

    public T peek() {
        if (top == null) {
            throw new EmptyStackException();
        }
        return top.item;
    }


    public T getMiddle() {
        if (top == null) {
            throw new EmptyStackException();
        }
        return middle.item;
    }

    /*
     * Sample example:
     * Consider a linkedlist with 3 nodes:
     * A - B - C and middle is B.
     * A-C       and middle is A
     * C         and middle is C
     * -         and middle is null.
     */
    public void deleteMiddle() {
        if (top == null) {
            throw new EmptyStackException();
        }

        /*
         * https://www.youtube.com/watch?v=450maTzSIvA
         * middle will be garbage collected.
         */
        if (middle.left != null) {
            middle.left.right = middle.right;
        }
        if (middle.right != null) {
            middle.right.left = middle.left;
        }

        size--;

        /*
         * 1. node3 -> node2 -> node1.
         * 2. node2 is deleted.
         * 3. node3 -> node1. now, size % 2 == 0, thus move left.
         */
        if (size % 2 == 0) {
            // deleted middle.
            middle = middle.left;
        } else {
            /*
             *  1. node4 -> node3 -> node2 -> node1
             *  2. node3 is deleted, and size is 3
             *  3. node4-> node2 -> node1.
             */
            middle = middle.right;
        }

        if (middle == null) {
            top = null;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    /*
     * Simplest test case:
     * add -1, 2, 3, 4
     * delete - 1, 2, 3, 4
     *
     *
     *
     */
    public static void main(String[] args) {

//        // Testing simple push and pop
//        FindMiddleStack<Integer> fm = new FindMiddleStack<Integer>();
//        fm.push(10);
//        fm.push(20);
//        fm.push(30);
//        fm.push(40);
//        fm.push(50);
//        fm.push(60);
//        fm.push(70);
//        int[] expected1 = {70, 60, 50, 40, 30, 20, 10};
//        int[] actual1 = new int[7];
//        int i1 = 0;
//        while (!fm.isEmpty()) {
//            actual1[i1++] = fm.pop();
//        }
//        assertTrue(Arrays.equals(expected1, actual1));
//
//
//        fm = new FindMiddleStack<Integer>();
//        fm.push(10);
//        fm.push(20);
//        fm.push(30);
//        fm.push(40);
//        fm.push(50);
//        fm.push(60);
//        fm.push(70);
//        int[] expected2 = {40, 40, 30, 30, 20, 20, 10};
//        int[] actual2 = new int[7];
//        int i2 = 0;
//        while (!fm.isEmpty()) {
//            actual2[i2++] = fm.getMiddle();
//            fm.pop();
//        }
//        assertTrue(Arrays.equals(expected2, actual2));
//
//        fm = new FindMiddleStack<Integer>();
//        fm.push(10);
//        fm.push(20);
//        fm.push(30);
//        fm.push(40);
//        fm.push(50);
//        fm.push(60);
//        fm.push(70);
//        int[] expected3 = {40, 50, 30, 60, 20, 70, 10};
//        int[] actual3 = new int[7];
//        int i3 = 0;
//        while (!fm.isEmpty()) {
//            actual3[i3++] =  fm.getMiddle();
//            fm.deleteMiddle();
//        }
//        assertTrue(Arrays.equals(expected3, actual3));

    }
}
