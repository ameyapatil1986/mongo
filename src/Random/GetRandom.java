package Random;

import java.util.*;


/**
 * https://leetcode.com/problems/insert-delete-getrandom-o1/
 * https://www.programcreek.com/2014/08/leetcode-insert-delete-getrandom-o1-java/
 * https://medium.com/@lenchen/leetcode-380-insert-delete-getrandom-o-1-48849ca37731
 */
public class GetRandom {

    HashMap<Integer, Integer> valueMap;
    HashMap<Integer, Integer> idxMap;

    /** Initialize your data structure here. */
    public GetRandom() {
        valueMap = new HashMap<>();
        idxMap = new HashMap<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (valueMap.containsKey(val)) {
            return false;
        }

        valueMap.put(val, valueMap.size());
        idxMap.put(idxMap.size(), val);

        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (valueMap.containsKey(val)) {
            int idx = valueMap.get(val);
            valueMap.remove(val);
            idxMap.remove(idx);

            if (!idxMap.isEmpty()) {
              Integer tailElem = idxMap.remove(idxMap.size());
              if (tailElem != null) {
                  idxMap.put(idx, tailElem);
                  valueMap.put(tailElem, idx);
              }
            }

            return true;
        }

        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        if (idxMap.size() == 0) {
            return -1;
        }

        if (idxMap.size() == 1) {
            return idxMap.get(0);
        }

        Random r = new Random();
        int idx = r.nextInt(idxMap.size());

        return idxMap.get(idx);
    }
}
