package Iterator;

import java.util.*;


/**
 * https://leetcode.com/problems/peeking-iterator/
 */
class PeekingIterator<E> implements Iterator<E> {

    private E next;

    private Iterator<E> iter;

    private boolean noSuchElement;

    public PeekingIterator(Iterator<E> iterator) {
        // initialize incoming iterator with iter member
        iter = iterator;
        //invoke the advanceIterator method to update the next member to point to the next element of the iterator obj
        advanceIterator();

    }

    // T O(1)
    // Returns the next element in the iteration without advancing the iterator.
    public E peek() {
        return next;
    }

    // T O(1)
    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public E next() {
        E res = next; //retrieve the cached result and return;
        advanceIterator(); //remember to update the next to point to next of iterator by invoking advanceIterator
        return res;
    }

    //T O(1)
    @Override
    public boolean hasNext() {
        return !noSuchElement;
    }

    //T O(1)
    private void advanceIterator() {
        if (iter.hasNext()) {
            next = iter.next();
        } else {
            noSuchElement = true;
        }
    }
}
