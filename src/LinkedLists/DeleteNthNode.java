package LinkedLists;

/**
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 */
public class DeleteNthNode {

    private static class ListNode<T> {
        T item;
        ListNode<T> next;
        ListNode<T> arbit;

        ListNode(T item, ListNode<T> next, ListNode<T> arbit) {
            this.item = item;
            this.next = next;
            this.arbit = arbit;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null)
            return null;

        ListNode fast = head;
        ListNode slow = head;

        for(int i=0; i < n; i++){
            fast = fast.next;
        }

        //if remove the first node
        if(fast == null) {
            head = head.next;
            return head;
        }

        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return head;
    }
}
