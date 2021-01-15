package Maps;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 *    add    |     adjust   |     delete
 * ---------------------------------------
 *    empty  |      first   |     onlyOne.
 *           |              |     first.
 *
 *
 *
 * TODO:
 * 1. why is subclassing better than this sad method ?
 *    - provides a lesser storage, dont need redundant data anywhere I guess ?
 * http://stackoverflow.com/questions/19441101/requesting-recommendation-for-alternate-suggestion-for-lru-cache
 *
 * NOTE:
 * If iterator is asked its very easy. Its simply start = eldest.left;
 *
 * Put, get, delete => O(1)
 *
 * References:
 * http://www.programcreek.com/2013/03/leetcode-lru-cache-java/ (Best link)
 * http://codereview.stackexchange.com/questions/32849/design-lru-cache-interview-questions-code-review-request
 * http://stackoverflow.com/questions/23772102/lru-cache-in-java-with-generics-and-o1-operations
 * http://www.geeksforgeeks.org/implement-lru-cache/
 *
 * BB:
 * 6
 */
public class LRU<K, V> {

    private final Map<K, Entry<K, V>> map;

    /*
     * I dont have the 'head pointer' and 'tail pointer', coz maintaining 2 pointers is harder than maintaining single pointer.
     *  the 'key' of the entry is never used. but just keep it as it is. dont change the code.
     */
    private Entry<K, V> eldest;
    private int lruSize;

    public LRU (int lruSize) {
        if (lruSize <= 0) {
            throw new IllegalArgumentException("size should atleast be one");
        }
        map = new HashMap<K, Entry<K, V>>();
        this.lruSize = lruSize;
    }

    /*
     * http://stackoverflow.com/questions/70324/java-inner-class-and-static-nested-class
     * http://stackoverflow.com/questions/20572181/public-vs-private-inner-classes-in-java
     */
    public static class Entry<K, V> {
        Entry<K, V> left;
        K key;
        V value;
        Entry<K, V> right;

        Entry (Entry<K, V> left, K key, V value, Entry<K, V> right) {
            this.left = left;
            this.key = key;
            this.value = value;
            this.right = right;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    };

    private void addLatest(Entry<K, V> entry) {
        if (eldest == null) {
            entry.left = entry.right = entry;
            eldest = entry;
        } else {
            // last node of the list is the latest node. extra storage just for purpose of simplification
            Entry<K, V> currentLatest = eldest.left;

            // deal with hooking entry with tail, at this point, the entry has become the last node, or the latest node.
            currentLatest.right = entry;
            entry.left = currentLatest;

            // deal with hooking entry with head.
            eldest.left = entry;
            entry.right = eldest;
        }
    }

    /*
     * 1. eldest
     * 2. tail
     * 3. middle
     * 4. only node
     */
    private void reAdjust(Entry<K, V> entry) {
        if (entry == eldest) {
            eldest = eldest.right;
            return;
        }

        entry.left.right = entry.right;
        entry.right.left = entry.left;

        addLatest(entry);
    }

    /*
     * 1. eldest
     * 2. tail
     * 3. middle
     * 4. only node
     */
    private void remove (Entry<K, V> entry) {
        /*
         * entry is eldest, and the only only node.
         */
        if (entry.right == entry) {
            eldest = null;
            return;
        }

        /*
         * entry is the eldest, and not the only node.
         */
        if (entry == eldest) {
            eldest = eldest.right;
        }

        entry.left.right = entry.right;
        entry.right.left = entry.left;
    }

    private boolean shouldRemoveEldestEntry() {
        return map.size() > lruSize;
    }

    public synchronized void put(K key, V value) {

        if (map.containsKey(key)) {
            Entry<K, V> entry = map.get(key);
            entry.value = value;
            reAdjust(entry);
            return;
        }

        /*
         * Allowing null key. Since, this is behavior of linkedhashmap.
         * Still adding synchronization as interviewer is bound to ask this question
         */
        Entry<K, V> entry = new Entry<K, V>(null, key, value, null);
        map.put(key, entry);

        if (shouldRemoveEldestEntry()) {
            map.remove(eldest.key);
            remove (eldest);
            this.lruSize--;
        }

        addLatest(entry);
        this.lruSize++;
    }

    /**
     *
     public synchronized void putMRU(K key, V value) {

     Entry<K, V> entry = new Entry<K, V>(null, key, value, null);
     map.put(key, entry);

     if (shouldRemoveEldestEntry()) {
     remove (eldest.left); // <------ difference.
     }
     addFirst(entry);
     }
     *
     */

    public synchronized V get (K key) {

        if (!map.containsKey(key)) {
            return null;
        }

        Entry<K, V> entry = map.get(key);
        reAdjust(entry);
        return entry.value;
    }

    /**
     *   public synchronized V getMRU (K key) {
     *      Entry<K, V> entry = map.get(key);
     *
     *        if (entry != null) {
     *            reAdjust(entry); // addLast, if (entry == eldest), then eldest = eldest.right;
     *            return entry.value;
     *        }
     *        return null;
     *    }
     */

    public synchronized V remove (K key) {

        if (!map.containsKey(key)) {
            return null;
        }


        Entry<K, V> entry = map.remove(key);
        remove(entry);
        this.lruSize--;
        return entry.value;
    }


    public Iterator<Entry<K, V>> iterator() {
        return map.values().iterator();
    }

    public Entry<K, V> getEldest() {
        return eldest;
    }


    /*
     * simplest test case:
     * * put.
     *   - put upto the limit.
     * * get.
     *   - first
     *   - last
     *   - middle
     *   - only node
     * * remove.
     *   - first
     *   - middle
     *   - last
     *   - only node
     */
    public static void main(String[] args) {

        /*
         * testing the PUT limit.
         */
        LRU<String, Integer> lru = new LRU<String, Integer>(4);
        lru.put("Xavi", 10);
        lru.put("Ronaldo", 20);
        lru.put("Messi", 20);
        lru.put("Neymar", 30);

        Entry<String, Integer> node = lru.getEldest();
        if (node != null) {
            System.out.print(node.key + ",");
            for (node = node.right; node != lru.getEldest(); node = node.right) {
                System.out.print(node.key + ",");
            }
        }
        lru.put("Suarez", 30);

        /*
         * xavi has been expelled.
         */
        System.out.println("\n ---------------------- Put more than threshold, xavi should be expelled. ----------------------");
        node = lru.getEldest();
        if (node != null) {
            System.out.print(node.key + ",");
            for (node = node.right; node != lru.getEldest(); node = node.right) {
                System.out.print(node.key + ",");
            }
        }

        /*
         * testing the GET.
         * - get first
         * - get last
         * - get middle
         * - get if only 1 exists.
         */
        // first.
        System.out.println("\n ---------------------- Get first on ronaldo: ----------------------");
        lru.get("Ronaldo");
        node = lru.getEldest();
        if (node != null) {
            System.out.print(node.key + ",");
            for (node = node.right; node != lru.getEldest(); node = node.right) {
                System.out.print(node.key + ",");
            }
        }

        // last
        System.out.println("\n ---------------------- Get last on ronaldo: ----------------------");
        lru.get("Ronaldo");
        node = lru.getEldest();
        if (node != null) {
            System.out.print(node.key + ",");
            for (node = node.right; node != lru.getEldest(); node = node.right) {
                System.out.print(node.key + ",");
            }
        }

        // middle
        System.out.println("\n ---------------------- Get middle on Suarez: ----------------------");
        lru.get("Suarez");
        node = lru.getEldest();
        if (node != null) {
            System.out.print(node.key + ",");
            for (node = node.right; node != lru.getEldest(); node = node.right) {
                System.out.print(node.key + ",");
            }
        }



        /*
         * REMOVE:
         * - middle
         * - first
         * - last
         */
        // remove neymar
        System.out.println("\n ---------------------- Remove middle Neymar: ----------------------");
        lru.remove("Neymar");
        node = lru.getEldest();
        if (node != null) {
            System.out.print(node.key + ",");
            for (node = node.right; node != lru.getEldest(); node = node.right) {
                System.out.print(node.key + ",");
            }
        }


        // remove first
        System.out.println("\n ---------------------- Remove first messi: ----------------------");
        lru.remove("Messi");
        node = lru.getEldest();
        if (node != null) {
            System.out.print(node.key + ",");
            for (node = node.right; node != lru.getEldest(); node = node.right) {
                System.out.print(node.key + ",");
            }
        }



        // remove last.
        System.out.println("\n ---------------------- Remove last suarez ----------------------");
        lru.remove("Suarez");
        node = lru.getEldest();
        if (node != null) {
            System.out.print(node.key + ",");
            for (node = node.right; node != lru.getEldest(); node = node.right) {
                System.out.print(node.key + ",");
            }
        }



        /**
         * Get only 1.
         */
        System.out.println("\n ---------------------- Get on the only one node remaining ----------------------");
        lru.get("Ronaldo");
        node = lru.getEldest();
        if (node != null) {
            System.out.print(node.key + ",");
            for (node = node.right; node != lru.getEldest(); node = node.right) {
                System.out.print(node.key + ",");
            }
        }


        System.out.println("\n ---------------------- Deleting that node  ----------------------");
        lru.remove("Ronaldo");
        node = lru.getEldest();
        if (node != null) {
            System.out.print(node.key + ",");
            for (node = node.right; node != lru.getEldest(); node = node.right) {
                System.out.print(node.key + ",");
            }
        }

    }
}

