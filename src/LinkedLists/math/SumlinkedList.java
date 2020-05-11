package LinkedLists.math;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * https://leetcode.com/problems/add-two-numbers/
 *
 * This pattern is followed in
 * ROBERT LAFORE.
 *
 * Although i dont intend to follow this model,
 * it can still be followed in the worst case.
 *
 */

class Node {
    Node next;
    int item;

    public Node(int item) {
        this.item = item;
    }
}

class LinkedListSum {

    private Node first;
    private Node last; // if setFirst is used then this field is uninitialized. But its ok to do it, coz builder pattern does same stuff
    int size;                   // builder patterns leave some variables uninitilized.

    public LinkedListSum() {};

    public LinkedListSum(List<Integer> items) {
        for (int i : items) {
            add(i);
        }
    }

    public void add(int i) {
        Node node = new Node(i);
        if (first == null) {
            first = last = node;
        } else {
            last.next = node;
            last = node;
        }
        size++;
    }

    public Node getFirstNode() {
        return first;
    }

    public void setFirst(Node first) {
        this.first = first;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (Node x = first; x != null; x = x.next)
            hashCode = 31*hashCode + (x == null ? 0 : x.hashCode());
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LinkedListSum other = (LinkedListSum) obj;
        Node currentListNode = first;
        Node otherListNode =  other.first;

        while (currentListNode != null && otherListNode != null) {
            if (currentListNode.item != otherListNode.item) return false;
            currentListNode = currentListNode.next;
            otherListNode = otherListNode.next;
        }
        return currentListNode == null && otherListNode == null;
    }

    public List<Integer> toList() {
        List<Integer> items = new ArrayList<>();
        for (Node x = first; x != null; x = x.next) {
            items.add(x.item);
        }
        return items;
    }

    public int size() {
        return size;
    }

}



/**
 *
 * http://www.geeksforgeeks.org/add-two-numbers-represented-by-linked-lists/
 * http://www.geeksforgeeks.org/sum-of-two-linked-lists/
 *
 * In such a case;
 * number 541 is represented as 1->4->5
 *
 * BB:
 * ---
 * 27
 *
 * Complexity:
 * -----------
 * O(n), n is longer number
 *
 */
public class SumlinkedList {

    public SumlinkedList() {}


    public static LinkedListSum addLinkedList(LinkedListSum ll1, LinkedListSum ll2) {
        if (ll1.size() == 0 || ll2.size() == 0) {
            throw new IllegalArgumentException("Either one of the linkedlist is empty this subtraction is not possible.");
        }


        Node ll1node = ll1.getFirstNode();
        Node ll2node = ll2.getFirstNode();

        return getLinkedlistSum(ll1node,  ll2node);
    }


    private static LinkedListSum getLinkedlistSum(Node ll1node, Node ll2node) {
        final LinkedListSum resultLinkedList = new LinkedListSum();

        Node first = null;
        Node prev = null;

        int carry = 0;

        while (ll1node != null || ll2node != null) {
            int sum = (ll1node != null ? ll1node.item : 0) + (ll2node != null ? ll2node.item : 0) + carry;
            carry = sum / 10;
            Node curr = new Node(sum % 10);

            if (first == null) {
                first = curr;
            } else {
                prev.next = curr;
            }
            prev = curr;

            ll1node = ll1node != null ? ll1node.next : null;
            ll2node = ll2node != null ? ll2node.next : null;
        }

        if (carry > 0) {
            prev.next = new Node(carry);
        }

        resultLinkedList.setFirst(first);
        return resultLinkedList;
    }



    public static void main(String[] args) {
        LinkedListSum ll1 = new LinkedListSum(Arrays.asList(1, 2, 3, 4, 5));
        LinkedListSum ll2 = new LinkedListSum(Arrays.asList(1, 2, 3, 4, 5));
        LinkedListSum ll3 = new LinkedListSum(Arrays.asList(2, 4, 6, 8, 0, 1));

//        assertEquals(ll3, SumlinkedList.addLinkedList(ll1, ll2));
    }
}
