package Iterator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * http://buttercola.blogspot.com/2015/08/leetcode-flatten-2d-vector.html
 *
 * http://www.glassdoor.com/Interview/flatten-iterator-QTN_441521.htm
 *
 *
 * BB:
 * ---
 * 5
 *
 * Complexity:
 * -----------
 * O(n) where n is sum of all elements in all iterators.
 *
 */
public class FlattenIterator<E> /* implements Iterable<E> */{

    public Iterator<E> flattenIterator(Iterator<Iterator<E>> nested) {
        return new FIterator<E>(nested);
    }

    private class FIterator<E> implements Iterator<E> {
        private Iterator<E> currentIterator = null;
        private Iterator<Iterator<E>> nested = null;

        public FIterator(Iterator<Iterator<E>> nested2) {
            if (nested2 == null) {
                throw new NullPointerException("input cannot be null.");
            }
            this.nested = nested2;
        }

        public boolean hasNext() {
            /*
             * Reversing the conditions:
             * (currentIterator == null || !currentIterator.hasNext()) && nested.hasNext()
             * will give a null pointer exception.
             *
             * Consider 2 issues:
             * 1. null iterator.
             * 2. iterator which is empty.
             */

            while ((currentIterator == null || !currentIterator.hasNext()) && nested.hasNext()) {
                currentIterator = nested.next();
            }

            // currentIterator == null is true when the "nested" is empty on input.
            return currentIterator == null ? false : currentIterator.hasNext();
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return currentIterator.next();
        }

        public void remove() {
            currentIterator.remove();
        }
    }

    /*
     * 1. empty list.
     * 2. normal input.
     */
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> list2 = Arrays.asList(5, 6, 7, 8);

        List<Iterator<Integer>> itrList = Arrays.asList(list1.iterator(), list2.iterator());

        Iterator<Integer> itr = new FlattenIterator<Integer>().flattenIterator(itrList.iterator());
        while (itr.hasNext()) {
            System.out.print(itr.next() + ",");
            itr.remove();
        }

        itrList = Collections.emptyList();
        itr = new FlattenIterator<Integer>().flattenIterator(itrList.iterator());
        while (itr.hasNext()) {
            System.out.print(itr.next() + ",");
        }
    }
}
