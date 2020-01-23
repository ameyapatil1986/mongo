package Heap;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// A class for heap node
class ListContainer implements Comparable {
    // listNum stores list number of the element
    private List<Integer> list;
    int index;

    ListContainer(List<Integer> list) {
        this.list = list;
        this.index = 0;
    }

    public int fetchAndIncrement() {
        return list.get(index++);
    }

    public boolean isFull() {
        return index == list.size();
    }

    public int peek() {
        return list.get(index);
    }

    @Override
    public int compareTo(Object o) {
        ListContainer node = (ListContainer)o;
        return peek() - node.peek();
    }
};

// A simple Pair class in Java
class Pair<U,V> {
    private final U first;
    private final V second;

    Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }

    public U getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }
}

class MinimumRange {
    // Function to compute the minimum range that includes
    // at-least one element from given M lists
    public static Pair findMinimumRange(List<List<Integer>> listOfLists) {
        // high will be maximum element in the heap
        int high = Integer.MIN_VALUE;

        // stores minimum and maximum element found so far in heap
        Pair<Integer, Integer> pair = new Pair(0, Integer.MAX_VALUE);

        // create an empty min-heap
        PriorityQueue<ListContainer> pq = new PriorityQueue<>();

        // push first element of each list into the min-heap
        // along with list number and their index in the list
        for (int i = 0; i < listOfLists.size(); i++) {
            ListContainer node = new ListContainer(listOfLists.get(i));
            pq.add(node);
            high = Integer.max(high, node.peek());
        }

        // run till end of any list is not reached
        while (true) {
            // remove root node
            ListContainer listContainer = pq.poll();


            // update low, high if new min is found
            if (high - listContainer.peek() < pair.getSecond() - pair.getFirst()) {
                pair = new Pair(listContainer.fetchAndIncrement(), high);
            }

            // return on reaching the end of any list
            if (listContainer.isFull()) {
                return pair;
            }

            // take next element from "same" list and
            // insert it into the min-heap
            pq.add(listContainer);

            // update high if new element is greater
            high = Integer.max(high, listContainer.peek());
        }
    }

    public static void main(String[] args)
    {
        List<List<Integer>> list = Arrays.asList(
            Arrays.asList(3, 6, 8, 10, 15),
            Arrays.asList(1, 5, 12),
            Arrays.asList(4, 8, 15, 16),
            Arrays.asList(2, 6)
        );

        Pair<Integer, Integer> p = findMinimumRange(list);
        System.out.println("Minimum range is " + p.getFirst() +
            "-" + p.getSecond());
    }
}
