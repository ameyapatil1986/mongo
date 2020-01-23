package LinkedLists;


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
        ListNode firstEnd = getFirstEnd(head);
        ListNode p2 = firstEnd.next;
        firstEnd.next = null;

        //sort each list
        p1 = sortList(p1);
        p2 = sortList(p2);

        //merge two lists
        return merge(p1, p2);
    }

    //get the list partition point
    private ListNode getFirstEnd(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;
        while (p1 != null && p2 != null) {
            if (p2.next == null || p2.next.next == null) {
                return p1;
            }

            p1 = p1.next;
            p2 = p2.next.next;
        }

        return head;
    }

    //merge two list
    private ListNode merge(ListNode n1, ListNode n2) {

        return null;
        //        ListNode head = new ListNode(0);
        //        ListNode p = aux;
        //        ListNode p1 = n1;
        //        ListNode p2 = n2;
        //        while(p1!=null && p2!=null){
        //            if(p1.val < p2.val){
        //                p.next = p1;
        //                p1 = p1.next;
        //            }else{
        //                p.next = p2;
        //                p2 = p2.next;
        //            }
        //
        //            p = p.next;
        //        }
        //
        //        if(p1!=null){
        //            p.next = p1;
        //        }
        //
        //        if(p2!=null){
        //            p.next = p2;
        //        }
        //
        //        return head.next;
    }
}
