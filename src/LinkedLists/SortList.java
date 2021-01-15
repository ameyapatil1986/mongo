package LinkedLists;

import java.util.Arrays;
import java.util.List;


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class SortList {

    private static class ListNode<E> {

        int val;
        ListNode<E> next;

        ListNode(int element, ListNode<E> next) {
            this.val = element;
            this.next = next;
        }
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        //partition the list
        ListNode p1 = head;
        ListNode midPoint = midPoint(head);
        ListNode p2 = midPoint.next;
        midPoint.next = null;

        //sort each list
        p1 = sortList(p1);
        p2 = sortList(p2);

        //merge two lists
        return merge(p1, p2);
    }

    public ListNode midPoint(ListNode node) {

        if (node == null) {
            return node;
        }

        ListNode slow = node;
        ListNode fast = node.next;

        for (;fast != null && fast.next != null; slow = slow.next, fast = fast.next.next);

        return slow;
    }


    //merge two list
    private ListNode merge(ListNode n1, ListNode n2) {
        // write the code to merge two linkedlists.
        return null;
    }
}
