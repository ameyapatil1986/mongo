package LinkedLists;


/**
 * Given : A B C D
 * output should be BA DC
 *
 * Companies:
 * Facebook
 * Ebay
 *
 * Complexity:
 * O(n)
 *
 * TBR: minor improvements
 *
 * BBno: 6
 *
 * @author SERVICE-NOW\ameya.patil
 */
public class SwapPairs<E> {

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

    public void swapPairs() {
        if (first == null) {
            throw new NullPointerException("Linkedlist is empty");
        }

        if (first.next == null ) {
            return;
        }

        Node<E> ptr1 = first;
        Node<E> ptr2;
        Node<E> ptr3;
        Node<E> prev = null;

        while (ptr1 != null && ptr1.next != null) {
            ptr2 = ptr1.next;
            ptr3 = ptr2.next;

            // swap
            ptr2.next = ptr1;
            ptr1.next = ptr3;

            // hook with previous pair
            if (prev == null) {
                first = ptr2;
            } else {
                prev.next = ptr2;
            }

            // advance.
            prev = ptr1;
            ptr1 = ptr3;
        }
    }

    public static void main (String[] args) {
        // testing even.
        int[] a1 = {1, 2, 3, 4};
        SwapPairs<Integer> swapPairs = new SwapPairs<Integer>();
        for (int val : a1) {
            swapPairs.add(val);
        }
        swapPairs.swapPairs();
        System.out.print("Expected: 2 1 4 3,   Actual: ");
        swapPairs.displayList();

        System.out.println();

        // testing odd.
        int[] a2 = {1, 2, 3, 4, 5};
        swapPairs = new SwapPairs<Integer>();
        for (int val : a2) {
            swapPairs.add(val);
        }
        swapPairs.swapPairs();
        System.out.print("Expected: 2 1 4 3 5, Actual: ");
        swapPairs.displayList();

        System.out.println();

        // testing two numbers.
        int[] a3 = {1, 2};
        swapPairs = new SwapPairs<Integer>();
        for (int val : a3) {
            swapPairs.add(val);
        }
        swapPairs.swapPairs();
        System.out.print("Expected: 2 1,       Actual: ");
        swapPairs.displayList();

        System.out.println();

        // only 1 element.
        int[] a4 = {1};
        swapPairs = new SwapPairs<Integer>();
        for (int val : a4) {
            swapPairs.add(val);
        }
        swapPairs.swapPairs();
        System.out.print("Expected: 1,         Actual: ");
        swapPairs.displayList();
    }
}
