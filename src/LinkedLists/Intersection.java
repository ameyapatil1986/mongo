package LinkedLists;

import BinaryTrees.LCA.LCAParentPointer;


public class Intersection {

    private static class ListNode<T> {
        T val;
        ListNode<T> next;
        ListNode<T> arbit;

        ListNode(T item, ListNode<T> next, ListNode<T> arbit) {
            this.val = item;
            this.next = next;
            this.arbit = arbit;
        }
    }


    private ListNode fetchNodeAtEqualLevel(ListNode node, int levelsToJumpUp) {
        for (int count = 0; count < levelsToJumpUp; count++) {
            node = node.next;
        }
        return node;
    }


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int len1 = 0;
        int len2 = 0;
        if (headA == null || headB == null)
            return null;

        for (ListNode p  = headA ; p != null; p = p.next, len1++);

        for (ListNode p  = headB ; p != null; p = p.next, len2++);

        ListNode p1 = fetchNodeAtEqualLevel(headA, len1 - len2);
        ListNode p2 = fetchNodeAtEqualLevel(headA, len2 - len1);


        for(;p1 != null && p2 != null; p1 = p1.next, p2 = p2.next) {
            if (p1.val == p2.val){
                return p1;
            }
        }

        return null;
    }
}
