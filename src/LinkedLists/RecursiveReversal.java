package LinkedLists;

/**
 * Complexity:
 * O(n)
 *
 * Reference:
 * http://stackoverflow.com/questions/354875/reversing-a-linked-list-in-java-recursively
 *
 *
 * BBNo: 3
 *
 * @param <E>
 */
public class RecursiveReversal<E> {

    Node<E> first;
    Node<E> last;

    public void add (E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<E>(e, null);
        last = newNode;
        if (first == null) {
            first = newNode;
        } else {
            l.next = newNode;
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

    /**
     * Following the prototype of
     * public E removeLast() {
     * function in LinkedList
     */
    public void reverseLinkedList() {
         recursiveReversal(first);
    }

    private void recursiveReversal(Node<E> node) {
        if (node == null) {
            return;
        }

        if (node.next == null) {
            first = node; // this will be first node in the linkedlist.
            return;
        }

        recursiveReversal(node.next);

        node.next.next = node;
        node.next = null;
    }

    public static void main(String args[]) {
        int[] a = {1, 2, 3, 4, 5};
        RecursiveReversal<Integer> recursiveReversalLL = new RecursiveReversal<Integer>();
        // nested loop a good practice as per effective java
        for (int val : a) {
            recursiveReversalLL .add(val);
        }
        recursiveReversalLL.displayList();
        recursiveReversalLL.reverseLinkedList();
        System.out.println();
        recursiveReversalLL.displayList();

    }
}
