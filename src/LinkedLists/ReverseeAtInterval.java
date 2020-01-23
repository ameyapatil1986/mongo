package LinkedLists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * References:
 * http://www.careercup.com/question?id=5084469887238144
 * http://codereview.stackexchange.com/questions/51183/reverse-linkedlist-at-interval
 *
 * BB:
 * 22
 *
 * Complexity:
 * O(n)
 */
public class ReverseeAtInterval<T> implements Iterable<T> {

    private Node<T> first;
    private Node<T> last;
    private int size;


    public ReverseeAtInterval(List<T> c) {
        for (T item : c) {
            add(item);
        }
    }

    public void add (T t) {
        final Node<T> l = last;
        final Node<T> node = new Node<T>(t, null);
        last = node;
        if (first == null) {
            first = node;
        } else {
            l.next = node;
        }
        size++;
    }

    private static class Node<T> {
        T item;
        Node<T> next;

        Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }

    private static class ReversedFirstLastNextData<T> {
        Node<T> first;  // first node in the reversed list.
        Node<T> last;   // last node in the reversed list.
        Node<T> next;   // next node - this node is not a part of revered list

        public ReversedFirstLastNextData(Node<T> first, Node<T> last, Node<T> next) {
            this.first = first;
            this.last = last;
            this.next = next;
        }
    }


    private ReversedFirstLastNextData<T> reverse(Node<T> start, int n) {
        Node<T> prev = null;
        Node<T> ptr = start;
        Node<T> ptr1 = null;

        for (int ctr = 0; ctr < n; ctr++) {
            ptr1 = ptr.next;
            ptr.next = prev;

            // advance forward.
            prev = ptr;
            ptr = ptr1;
        }

        return new ReversedFirstLastNextData<T>(prev, start, ptr);
    }

    public void reverseAtInterval(int n) {
        ReversedFirstLastNextData<T> current = null;
        Node<T> inputNode = first;
        Node<T> last = null;

        // easy to understand this for loop.
        for (int i = 0; i < (size/n); i++) {
            current = reverse(inputNode, n);

            if (last == null) {
                first = current.first;
                last = current.last;
            } else {
                last.next = current.first;
                last = current.last;
            }

            inputNode = current.next;
        }

        //        current = reverse(inputNode, size % k);
        //        if (prev != null) { prev.last.next = current.first; }

        // OR.
        current.last.next = current.next;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListItr();
    }

    private class LinkedListItr implements Iterator<T> {
        private Node<T> prev;
        private Node<T> current;
        private int currentSize = 0;
        private int initialCapacity;

        public LinkedListItr() {
            this.current  = first;
            this.currentSize = 0;
            initialCapacity = size;
        }


        @Override
        public boolean hasNext() {
            return currentSize < size;
        }

        @Override
        public T next() {
            if (initialCapacity != size) {
                throw new ConcurrentModificationException();
            }

            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            currentSize++;
            prev = current;
            T item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            if (initialCapacity != size) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            prev.next = current.next;
            size--;
            initialCapacity--;
        }
    }


    public List<T> toArray() {
        List<T> list = new ArrayList<T>();
        Node<T> ptr = first;
        while (ptr != null) {
            list.add(ptr.item);
            ptr = ptr.next;
        }
        return list;
    }


//
//    public static void main(String[] args) {
//        ReverseeAtInterval<Integer> rai = new ReverseeAtInterval<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
//        rai.reverseAtInterval(4);
//        assertEquals(Arrays.asList(4, 3, 2, 1, 8, 7, 6, 5), rai.toArray());
//
//        rai = new ReverseeAtInterval<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
//        rai.reverseAtInterval(3);
//        assertEquals(Arrays.asList(3, 2, 1, 6, 5, 4, 7, 8), rai.toArray());
//    }
}
