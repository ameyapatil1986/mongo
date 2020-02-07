package LinkedLists;

import java.util.List;

class Nodex<T extends Comparable <T>> {
    Nodex<T> next;
    T item;

    Nodex(T item) {
        this.item = item;
    }
}


class LinkedLister<T extends Comparable <T>> {

    private Nodex<T> first;
    private Nodex<T> last;

    public LinkedLister(List<T> items) {
        for (T item : items) {
            add(item);
        }
    }

    private void add(T item) {
        Nodex<T> node = new Nodex<>(item);
        if (first == null) {
            first = last = node;
        } else {
            last.next = node;
            last = node;
        }
    }

    public Nodex<T> getFirstNode() {
        return first;
    }

    public void setFirstNode(Nodex<T> first) {
        this.first = first;
    }
}



/**
 * The task is merge 2 sorted linkedlist.
 *
 * This code has generics removed to simplify life.
 *
 * References:
 * http://www.programcreek.com/ ( in case people dont like what we do, use this site as reference )
 * http://stackoverflow.com/questions/7685/merge-sort-a-linked-list
 * http://stackoverflow.com/questions/10707352/interview-merging-two-sorted-singly-linked-list
 * http://stackoverflow.com/questions/17130809/where-to-set-references-to-null-if-references-are-passed-by-value-in-java
 * http://stackoverflow.com/questions/16495034/big-o-complexity-to-merge-two-lists
 * http://codereview.stackexchange.com/questions/56479/merge-two-linked-list
 *
 *
 * Complexity:
 * O(n)
 *
 * BBNo:
 * 7
 *
 */
public final class MergeLinkedList {

    private MergeLinkedList() {
    }
//
//    // does not create new linkedlist, just merges second into first.
//    public static <T extends Comparable <T>> void mergeRecursive(LinkedLister<T> firstList, LinkedLister<T> secondList) {
//        if (firstList == null) {
//            throw new IllegalArgumentException("Bad input");
//        }
//        if (secondList == null) {
//            return;
//        }
//
//        Nodex<T> firstNode = mergeLinkedListRecursive(firstList.getFirstNode(), secondList.getFirstNode());
//        secondList.setFirstNode(null);
//        firstList.setFirstNode(firstNode);
//    }
//
//    private static <T extends Comparable <T>> Nodex<T> mergeLinkedListRecursive(Nodex<T> node1, Nodex<T> node2) {
//        if (node1 == null) {
//            return node2;
//        }
//
//        if (node2 == null) {
//            return node1;
//        }
//
//        if (node1.item.compareTo(node2.item) < 0) {
//            node1.next = mergeLinkedListRecursive(node1.next, node2);
//            return node1;
//        } else {
//            node2.next = mergeLinkedListRecursive(node1, node2.next);
//            return node2;
//        }
//    }

    // does not create new linkedlist, just merges second into first.
    public static <T extends Comparable <T>> void merge(LinkedLister<T> firstList, LinkedLister<T> secondList) {
        if (firstList == null) {
            throw new IllegalArgumentException("Bad input");
        }
        if (secondList == null) {
            return;
        }

        Nodex<T> firstNode = mergeLinkedList(firstList.getFirstNode(), secondList.getFirstNode());
        secondList.setFirstNode(null);
        firstList.setFirstNode(firstNode);
    }

    private static <T extends Comparable <T>> Nodex<T> mergeLinkedList(Nodex<T> node1, Nodex<T> node2) {
        if (node1 == null) {
            return node2;
        }

        if (node2 == null) {
            return node1;
        }

        Nodex<T> first = null;
        Nodex<T> prev = null;
        Nodex<T> aux = null;

        while (node1 != null && node2 != null) {
            if (node1.item.compareTo(node2.item) < 0) {
                aux = node1;
                node1 = node1.next;
            } else {
                aux = node2;
                node2 = node2.next;
            }

            if (prev == null) {
                first = aux;
            } else {
                prev.next = aux;
            }
            prev = aux;
        }

        prev.next = node1 != null ? node1 : node2;

        return first;
    }
}
