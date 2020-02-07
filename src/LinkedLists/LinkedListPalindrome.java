package LinkedLists;

/**
 * https://leetcode.com/problems/palindrome-linked-list/
 */

/**
 * Complexity:
 * O(n)
 * space:
 * O(1)
 * http://stackoverflow.com/questions/28384987/space-complexity-on-creating-an-object-on-recursion
 *
 * BB: 5
 *
 * http://www.geeksforgeeks.org/function-to-check-if-a-singly-linked-list-is-palindrome/
 */
public class LinkedListPalindrome<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;

    public void add(E item) {
        final Node<E> newNode = new Node<E>(item, null);
        if (first == null) {
            first = last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    public boolean detectPalindrome() {
        return oneDetectPalindrome(first, first.next).isPalindrome;
    }

    private static class NodeBool<E> {
        Node<E> node;
        boolean isPalindrome;

        public NodeBool(boolean isPalindrome, Node<E> next) {
            this.isPalindrome = isPalindrome;
            this.node = next;
        }
    }

    private NodeBool<E> oneDetectPalindrome(Node<E> slow, Node<E> fast) {
        // reached the midpoint.
        if (fast == null) {
            // aba
            return new NodeBool<E>(true, slow.next);
        } else if (fast.next == null) {
            // abba
            if (slow.element == slow.next.element) {
                return new NodeBool<E>(true, slow.next.next);
            } else {
                // abcd
                return new NodeBool<E>(false, null);
            }
        }

        final NodeBool<E> nodeBool = oneDetectPalindrome(slow.next, fast.next.next);

        if (nodeBool.isPalindrome) {
            if (slow.element == nodeBool.node.element) {
                return new NodeBool<E>(true, nodeBool.node.next);
            }
        }
        return new NodeBool<E>(false, null);
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 2, 1};
        LinkedListPalindrome<Integer> createAndTravelLL = new LinkedListPalindrome<Integer>();
        // nested loop a good practice as per effective java
        for (int val : a) {
            createAndTravelLL.add(val);
        }
        // createAndTravelLL.displayList();
        System.out.println("expected true, actual:  " + createAndTravelLL.detectPalindrome());

        int[] a1 = {1, 2, 3, 2, 5};
        createAndTravelLL = new LinkedListPalindrome<Integer>();
        // nested loop a good practice as per effective java
        for (int val : a1) {
            createAndTravelLL.add(val);
        }
        //createAndTravelLL.displayList();
        System.out.println("expected false, actual:  " + createAndTravelLL.detectPalindrome());
    }
}
