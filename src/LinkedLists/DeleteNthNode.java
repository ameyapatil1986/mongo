package LinkedLists;

/**
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 */
public class DeleteNthNode {

    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> arbit;

        Node(T item, Node<T> next, Node<T> arbit) {
            this.item = item;
            this.next = next;
            this.arbit = arbit;
        }
    }

    public Node removeNthFromEnd(Node head, int n) {
        if(head == null)
            return null;

        Node fast = head;

        for(int i = 0; i < n; i++){
            fast = fast.next;
        }

        //if remove the first node
        if(fast == null) {
            head = head.next;
            return head;
        }

        Node slow = head;
        Node prev = null;
        for (; fast != null; fast = fast.next, slow = slow.next) {
            prev = slow;
        }

        prev.next = slow.next;

        return head;
    }
}
