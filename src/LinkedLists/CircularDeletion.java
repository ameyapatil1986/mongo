package LinkedLists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 *      insert    |     delete
 * ---------------------------------
 *    empty.      |     empty.
 *                |     first.
 *
 *
 *
 */

/**
 * Complexity: O(n)
 *
 * References:
 * http://codereview.stackexchange.com/questions/30223/code-to-insert-and-delete-in-doubly-linked-list-please-review
 * http://stackoverflow.com/questions/16184876/deleting-a-node-in-circular-linked-list
 *
 * BB: 11
 */
public class CircularDeletion<T extends Comparable<T>> {

    private Node<T> first;

    private static class Node<T> {
        T element;
        Node<T> next;

        Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }
    }

    /*
     * 3 Steps:
     * ---------
     * 1. no node
     * 2. smaller than first node.
     * 3. somewhere in the middle or last.
     */
    public void insertNodeInCircularLL(T element) {
        final Node<T> node = new Node<T>(element, null);

        // using guard clause like pattern. leads to cleaner indentation.
        if (first == null) {
            first = node;
            first.next = first;
            return;     // DONT FORGET THE RETURNS
        }

        // smaller than first node.
        if (element.compareTo(first.element) < 0) {
            Node<T> currNode;
            for (currNode = first; currNode.next != first; currNode = currNode.next);

            /*
             * following lines below also includes the case, when linkedlist had only 1 node, prior to inserting the 'input node'
             * eg: first node: 10 current node: 5.
             */
            node.next = first;
            currNode.next = node;

            first = node;   // DONT FORGET THIS

            return;
        }

        // either middle or end.
        Node<T> prev = first;
        Node<T> currNode = first.next;

        for (; currNode != first; currNode = currNode.next, prev = prev.next) {
            if (element.compareTo(currNode.element) < 0) {
                break;
            }
        }

        /*
         * following lines below also includes the case, when linkedlist had only 1 node, prior to inserting the 'input node'
         * eg: first node: 10, and current node was 20.
         */
        node.next = currNode;
        prev.next = node;
    }

    public void deleteNodeInCircularLL(T element) {

        if (first == null) {
            throw new IllegalStateException();
        }

        // if first node.
        if (element.compareTo(first.element) == 0) {
            if (first.next == first) {
                first = null;
            } else {
                // reaching the last node.
                Node<T> currNode = null;

                for (currNode = first; currNode.next != first; currNode = currNode.next);

                currNode.next = first.next;
                first = first.next;
            }
            return;
        }

        // if any node other than first node.
        Node<T> prev = first;
        Node<T> currNode = first.next;

        for (currNode = first.next; currNode != first; currNode = currNode.next, prev = prev.next) {
            if (element.compareTo(currNode.element) == 0) {
                prev.next = currNode.next;
                return;
            }
        }

        throw new IllegalArgumentException(" Asked to delete a node which does not exist.");
    }


    public List<T> toList() {
        List<T> elements = new ArrayList<T>();

        if (first != null) {
            elements.add(first.element);

            for(Node<T> tempNode = first.next; tempNode != first; tempNode = tempNode.next) {
                elements.add(tempNode.element);
            }
        }
        return elements;
    }

//    public static void main(String[] args) {
//        CircularDeletion<Integer> clld = new CircularDeletion<Integer>();
//        int[] a = {10, 20, 30, 40, 50};
//        for (int i : a) {
//            clld.insertNodeInCircularLL(i);
//        }
//
//        // delete first.
//        clld.deleteNodeInCircularLL(10);
//        assertEquals(Arrays.asList(20, 30, 40, 50), clld.toList());
//
//        // delete some node in the middle.
//        clld.deleteNodeInCircularLL(30);
//        assertEquals(Arrays.asList(20, 40, 50), clld.toList());
//
//        // delete the node in the end.
//        clld.deleteNodeInCircularLL(50);
//        assertEquals(Arrays.asList(20, 40), clld.toList());
//    }
}
