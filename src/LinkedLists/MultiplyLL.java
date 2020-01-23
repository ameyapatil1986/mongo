package LinkedLists;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This pattern is followed in
 * ROBERT LAFORE.
 *
 * Although i dont intend to follow this model,
 * it can still be followed in the worst case.
 *
 */

class Nodei {
    Nodei next;
    int item;

    public Nodei(int item) {
        this.item = item;
    }
}

class LinkedListMult {

    private Nodei first;
    private Nodei last; // if setFirst is used then this field is uninitialized. But its ok to do it, coz builder pattern does same stuff
    // builder patterns leave some variables uninitilized.
    private int size;

    public LinkedListMult() {};

    public LinkedListMult(List<Integer> items) {
        for (int i : items) {
            add(i);
        }
    }

    public void add(int i) {
        Nodei node = new Nodei(i);
        if (first == null) {
            first = last = node;
        } else {
            last.next = node;
            last = node;
        }
        size++;
    }

    public Nodei getFirstNode() {
        return first;
    }

    public void setFirst(Nodei first) {
        this.first = first;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (Nodei x = first; x != null; x = x.next)
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
        LinkedListMult other = (LinkedListMult) obj;
        Nodei currentListNode = first;
        Nodei otherListNode =  other.first;

        while (currentListNode != null && otherListNode != null) {
            if (currentListNode.item != otherListNode.item) return false;
            currentListNode = currentListNode.next;
            otherListNode = otherListNode.next;
        }
        return currentListNode == null && otherListNode == null;
    }

    public List<Integer> toList() {
        List<Integer> items = new ArrayList<>();
        for (Nodei x = first; x != null; x = x.next) {
            items.add(x.item);
        }
        return items;
    }

    public int getSize() {
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
 * 40
 *
 * Complexity:
 * -----------
 * O(n), n is longer number
 *
 */
public class MultiplyLL {

    public MultiplyLL() {}

    public static LinkedListMult multLinkedList(LinkedListMult ll1, LinkedListMult ll2) {

        if (ll1.getSize() == 0 || ll2.getSize() == 0) {
            throw new IllegalArgumentException("Either one of the linkedlist is empty this multiplication is not possible.");
        }

        int sum = 0; // dont use double sum. else while (sum != 0) loop below will loop longer
        int mult = 0;
        int base = 1;
        for (Nodei nodeOut = ll1.getFirstNode(); nodeOut != null; nodeOut = nodeOut.next)  {
            mult = mult + nodeOut.item * base;
            base = base * 10;
        }

        base = 1;
        for (Nodei nodeIn = ll2.getFirstNode(); nodeIn != null; nodeIn = nodeIn.next) {
            sum = sum + (nodeIn.item * mult) * base;
            base = base * 10;
        }


        Nodei first = null;
        Nodei prev = null;
        while (sum != 0) {
            Nodei n =  new Nodei(sum % 10);
            if (first == null) {
                first = n;
            } else {
                prev.next = n;
            }
            prev = n;
            sum = sum/10;
        }

        LinkedListMult llm = new LinkedListMult();
        llm.setFirst(first);

        return llm;
    }

    public static void main(String[] args) {
        LinkedListMult ll1 = new LinkedListMult(Arrays.asList(5, 2));
        LinkedListMult ll2 = new LinkedListMult(Arrays.asList(5, 1));
        LinkedListMult ll3 = new LinkedListMult(Arrays.asList(5, 7, 3));
//        assertEquals(ll3, MultiplyLL.multLinkedList(ll1, ll2));
    }
}
