package LinkedLists;

import java.util.LinkedList;
import java.util.List;


/**
 * Complexity: O (n)
 *
 * BBNo: 4
 *
 * @author SERVICE-NOW\ameya.patil
 */
public class LinkedListReversal<E> {

    Node<E> first;
    Node<E> last;

    public void add(int item) {
        Node node = new Node(item, null);
        if (first == null) {
            first = last = node;
        } else {
            last.next = node;
            last = node;
        }
    }

    public void displayList() {
        Node<E> tempFirst = first;
        while (tempFirst != null) {
            System.out.print(tempFirst.item + " ");
            tempFirst = tempFirst.next;
        }
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    /*
     * https://www.careercup.com/question?id=5134949294276608
     */
    public void reverseLinkedList() {
        Node<E> prev = null;
        Node<E> ptr = first;
        Node<E> ptr1 = null;

        while (ptr != null) {
            ptr1 = ptr.next;
            ptr.next = prev;

            // advance.
            prev = ptr;
            ptr = ptr1;
        }

        first = prev;
    }

    public static void main(String args[]) {
        int[] a = {1, 2, 3, 4, 5};
        LinkedListReversal<Integer> reversalLL = new LinkedListReversal<Integer>();
        // nested loop a good practice as per effective java
        for (int val : a) {
            reversalLL.add(val);
        }
        reversalLL.displayList();
        reversalLL.reverseLinkedList();
        System.out.println();
        reversalLL.displayList();

    }
}
