package LinkedLists;

/**
 * https://wihoho.gitbooks.io/leetcode-solutions/content/143-reorder-list.html
 * https://leetcode.com/problems/reorder-list/
 */
public class Reorder {

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    public void reorderList(Node head) {
        if (head == null || head.next == null) return;
        Node slow = head;
        Node fast = head.next;

        for ( ;fast != null && fast.next != null;  slow = slow.next, fast = fast.next.next);

        Node n = reverse(slow.next);
        slow.next = null;

        Node m = head;

        while (m != null && n != null) {
            Node nextM = m.next;
            Node nextN = n.next;

            m.next = n;
            n.next = nextM;

            m = nextM;
            n = nextN;
        }
    }

    public Node reverse(Node start) {
        Node prev = null;
        Node ptr = start;
        Node ptr1 = null;

        while (ptr != null) {
            ptr1 = ptr.next;
            ptr.next = prev;

            // advance forward.
            prev = ptr;
            ptr = ptr1;
        }

        return prev;
    }
}
