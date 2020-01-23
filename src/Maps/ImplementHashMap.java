package Maps;

import java.util.AbstractSet;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;


/**
 * DIAGRAMS:
 * ---------
 * sequence:      https://bitbucket.org/ameyapatil/all-images/src/6c7680f2242cabd86ad5d507556f24827e970d30/HMapSeq.JPG?at=master
 * return values: https://bitbucket.org/ameyapatil/all-images/src/6c7680f2242cabd86ad5d507556f24827e970d30/HMapRetValue.JPG?at=master
 * resize:        https://bitbucket.org/ameyapatil/all-images/src/6c7680f2242cabd86ad5d507556f24827e970d30/HMapResize.JPG?at=master
 *
 * Pending:
 * - access modifiers.
 * - synchronization.
 * - generics.
 *
 * Questions:
 * ----------
 * What does the term bucket mean ?
 * -: an index position in an array is called a bucket.
 *
 * Why power of 2 is used for capacity ?
 * -: http://stackoverflow.com/questions/8352378/why-does-hashmap-require-that-the-initial-capacity-be-a-power-of-two
 *
 *  TODO:
 *  * Use synchronization and implement hashtable
 *  * Code to develop your own hashfunctions.
 *
 *  BB : 3
 *
 */
public class ImplementHashMap<K, V> {
    /**
     * The default initial capacity - MUST be a power of two.
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    /**
     * The load factor used when none specified in constructor.
     */
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private final float loadFactor;     // only use is to compute threshhold, ie (loadfactor * capacity)
    private int threshold;

    /*
     * EXPLORE: GENERICS
     * EXPLORE: why something is transient.
     * EXPLORE: what part is bucket ? is bucket same as key ?
     */
    private /* TODO: post checkpoint. transient */ Entry<K, V>[] table;

    private int modCount;               // used only for the sake of iterators.
    private /*TODO: post checkpoint  transient */ int size;


    public ImplementHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public ImplementHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        if (loadFactor <= 0 || Float.isNaN(loadFactor) || loadFactor > 1)
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);

        // Find a power of 2 >= initialCapacity

        this.loadFactor = loadFactor;
        threshold = (int) (initialCapacity * loadFactor);
        /*
         * EXPLORE: generics.
         */
        table = new Entry[initialCapacity];
    }

    /*
     * http://tutorials.jenkov.com/java-collections/hashcode-equals.html
     * http://stackoverflow.com/questions/34119364/why-do-we-need-to-check-hashcode-twice
     * http://stackoverflow.com/questions/34119364/why-do-we-need-to-check-hashcode-twice
     * http://stackoverflow.com/questions/19349436/do-keys-with-different-hashes-also-get-mapped-to-the-same-index-in-hashmap
     */
    private boolean hashAndEqualsCheckSuccess(Entry<K, V> e, K key) {

        if (e.key == null && key == null) {
            return true;
        }

        if (e.key == null || key == null) {
            return false;
        }

        return e.hash == key.hashCode() & e.key.equals(key);
    }

    /**
     * The result of this value is same as ( h mod len )
     * http://stackoverflow.com/questions/10879302/hashmap-implentation-in-java-how-does-the-bucket-index-calculation-work#10879302
     *
     * And what guarantees same result if length changes: its called rehashing.
     * http://stackoverflow.com/questions/19349526/how-can-hashmap-guarantee-same-index-for-key-used-again/19349591?noredirect=1#19349591
     *
     */
    private int indexFor(int h, int length) {
        return h / (length-1);
    }

    public /* synchronized */ void clear() {
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    public /* synchronized */  boolean containsKey(K key) {
        int keyIndex = key == null ? 0 : key.hashCode() % table.length;

        for (Entry<K, V> e = table[keyIndex]; e != null; e = e.next) {
            if (hashAndEqualsCheckSuccess(e, key))
                return true;
        }
        return false;
    }

    public /* synchronized */   V get(K key) {
        int keyIndex = key == null ? 0 : key.hashCode() % table.length;

        for (Entry<K, V> e = table[keyIndex]; e != null; e = e.next) {
            if (hashAndEqualsCheckSuccess(e, key))
                return e.value;
        }
        return null;
    }

    public /* synchronized */  V remove(K key) {
        int keyIndex = key == null ? 0 : key.hashCode() % table.length;

        Entry<K,V> prev = null;
        Entry<K, V> e = table[keyIndex];

        for (; e != null; e = e.next) {
            if (hashAndEqualsCheckSuccess(e, key)) {
                modCount++;
                size--;
                if (prev == null) {
                    table[keyIndex] = e.next;
                    break;
                } else {
                    prev.next = e.next;
                    break;
                }
            }
            prev = e;
        }

        return e == null ? null : e.value;
    }

    public /* synchronized */  V put(K key, V value) {
        int hash = key == null ? 0 : key.hashCode();
        int keyIndex = hash % table.length;

        for (Entry<K, V> e = table[keyIndex]; e != null; e = e.next) {
            if (hashAndEqualsCheckSuccess(e, key)) {
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }

        table[keyIndex] = new Entry(key, value, table[keyIndex], hash);

        size = size + 1;
        if (size == threshold) {
            resize(2 * table.length);
        }

        return null;
    }

    private void resize (int newCapacity) {
        Entry<K,V>[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int)(newCapacity * loadFactor);
    }


    /**
     * Transfers all entries from current table to newTable.
     */
    private void transfer(Entry[] newTable) {
        /**
         * EXPLORE: synchronization
         */

        for (int j = 0; j < table.length; j++) {
            Entry<K, V> next = null;

            for (Entry<K, V> e = table[j]; e!= null; e = next) {
                next = e.next;

                int newKeyIndex = e.hash % newTable.length;

                e.next = newTable[newKeyIndex];
                newTable[newKeyIndex] = e;
            }
        }
    }


    // dont make anything in this class private, since we want dudes like LinkedHashMap to override it.
    private static class Entry<K,V> {
        final K key;
        V value;
        Entry<K, V> next;
        final int hash;

        Entry (K key, V value,  Entry<K, V> next, int hash) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.hash = hash;
        }

        // kind of makes sense to mark it as final since we dont want to change this behavior at all.
        public final K getKey() {
            return key;
        }

        // kind of makes sense to mark it as final since we dont want to change this behavior at all.
        public final V getValue() {
            return value;
        }

        //        TODO: post checkpoint.
        //        public final boolean equals(Object o) {
        //            if (!(o instanceof Entry)) {
        //                return false;
        //            }
        //            Entry e = (Entry)o;
        //            return (key==null ? e.getKey()==null : key.equals(e.getKey())) &&
        //                        (value==null ? e.getValue()==null : value.equals(e.getValue()));
        //
        //        }
        //
        //        public final int hashCode() {
        //            return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
        //        }
        //
        //        public final String toString() {
        //            return getKey() + "=" + getValue();
        //        }

    }
    //
    //    TODO: post checkpoint
    //
    //    /*
    //     * Explore: why is this abstract ?
    //     */
    //    private abstract class HashIterator<E> implements Iterator<E> {
    //        private Entry<K,V> next;                       // next entry to return
    //        private int expectedModCount;   // For fast-fail
    //        private int index;              // current slot
    //        private Entry<K,V> current;     // current entry
    //
    //        HashIterator() {
    //            expectedModCount = modCount;
    //            if (size > 0) { // advance to first entry
    //                Entry[] t = table;
    //                for (;index < t.length && ((next = t[index]) == null); index++);
    //            }
    //        }
    //
    //        public final boolean hasNext() {
    //            return next != null;
    //        }
    //
    //        /**
    //         * EXPLORE: what pattern is this being referred to ?
    //         */
    //        final Entry<K,V> nextEntry() {
    //            if (modCount != expectedModCount)
    //                throw new ConcurrentModificationException();
    //
    //            current = next;
    //            if (current == null)
    //                throw new NoSuchElementException();
    //
    //            next = next.next;
    //
    //            if (next == null) {
    //                Entry[] t = table;
    //                for (index++ ;index < t.length && ((next = t[index]) == null); index++);
    //            }
    //            return current;
    //        }
    //
    //        public void remove() {
    //            if (current == null)
    //                throw new IllegalStateException();
    //            if (modCount != expectedModCount)
    //                throw new ConcurrentModificationException();
    //            K k = current.key;
    //            current = null;
    //            // explore this stuff " ImplementHashMap.this" ?
    //            ImplementHashMap.this.remove(k);
    //            expectedModCount = modCount;
    //        }
    //    }
    //
    //
    //    private Set<Entry<K,V>> entrySet = new EntrySet();
    //
    //    private Set<Entry<K,V>> entrySet() {
    //        return entrySet;
    //    }
    //
    //    /*
    //     * explore: where iterator comes from, complete heirarchy of collection methods.
    //     *
    //     */
    //    private final class EntrySet extends AbstractSet<Entry<K,V>> {
    //
    //        public Iterator<Entry<K, V>> iterator() {
    //            /*
    //             * if I do "return new HashIterator()", I get an error, explore why ?
    //             */
    //            return new HashIterator() {
    //                public Entry<K, V> next() {
    //                    return nextEntry();
    //                }
    //            };
    //        }
    //
    ////        public boolean contains(Object o) {
    ////             // dont worry for now.
    ////            return false;
    ////        }
    //
    //        public int size() {
    //            return size;
    //        }
    //
    ////        public void clear() {
    ////           // dont worry for now.
    ////        }
    //    }

    public static void main(String[] args) {

        //        ENHANCED FOR LOOP ITERATOR
        //        Map map = new HashMap();
        //        Iterator entries = map.entrySet().iterator();
        //        while (entries.hasNext()) {
        //            Map.Entry entry = (Map.Entry) entries.next();
        //            Integer key = (Integer)entry.getKey();
        //            Integer value = (Integer)entry.getValue();
        //            System.out.println("Key = " + key + ", Value = " + value);
        //        }


        //            Iterator it = mp.entrySet().iterator();
        //            while (it.hasNext()) {
        //                Map.Entry pairs = (Map.Entry)it.next();
        //                System.out.println(pairs.getKey() + " = " + pairs.getValue());
        //                it.remove(); // avoids a ConcurrentModificationException
        //            }
        //        }
    }

}
