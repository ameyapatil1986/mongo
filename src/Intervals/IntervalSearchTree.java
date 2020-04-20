package Intervals;

/**
 * https://leetcode.com/problems/my-calendar-i/
 */

/**
 * References:
 * -----------
 * Best link - http://www.youtube.com/watch?v=q0QOYtSsTg4
 * http://stackoverflow.com/questions/4446112/search-for-interval-overlap-in-list-of-intervals
 * http://www.dgp.utoronto.ca/people/JamesStewart/378notes/22intervals/
 * http://stackoverflow.com/questions/18922461/augment-java-collection-to-get-interval-tree
 * http://codereview.stackexchange.com/questions/42008/interval-search-tree
 *
 * http://stackoverflow.com/questions/29236902/do-10-20-and-20-30-overlap
 * "Well overlapping means that the intersection is not empty. The intersection is the singleton {20} thus yes."
 *
 * Two types of interval trees:
 * - Centered interval trees.
 * - Augmented interval trees.
 *
 * some concept revolves around red-black and some around AVL.
 *
 *
 * Complexity:
 * -----------
 * - Avg: O(logn) Worst: O(n) --> to add
 * - Avg: O(logn) Worst: O(n) --> to look for overlap.
 *
 * BB:
 * ---
 * - 9
 *
 */
public class IntervalSearchTree {

    private IntervalNode root;

    private class IntervalNode {
        IntervalNode left;
        int start;
        int end;
        int maxEnd;
        IntervalNode right;

        public IntervalNode(IntervalNode left, int start, int end, int maxEnd, IntervalNode right) {
            this.left = left;
            this.start = start;
            this.end = end;
            this.maxEnd = maxEnd;
            this.right = right;
        }
    }

    public void book (int start, int end) {
        if (overlap(start, end)) {
            add(start, end);
        }
    }

    /**
     * Adds an interval to the the calendar
     *
     * @param start the start of interval
     * @param end   the end of the interval.
     */
    public void add (int start, int end) {
        if (start >= end) throw new IllegalArgumentException("The end " + end + " should be greater than start " + start);

        if (root == null) {
            root =  new IntervalNode(null, start, end, end, null);
        }

        IntervalNode inode = root;

        while (inode != null) {
            inode.maxEnd = Math.max(end, inode.maxEnd);

            if (start < inode.start) {
                if (inode.left == null) {
                    inode.left = new IntervalNode(null, start, end, end, null);
                    return;
                }
                inode = inode.left;
            } else {
                if (inode.right == null) {
                    inode.right = new IntervalNode(null, start, end, end, null);
                    return;
                }
                inode = inode.right;
            }
        }
    }

    /**
     * Tests if the input interval overlaps with the existing intervals.
     *
     * Rules:
     * 1.  If interval intersects return true. obvious.
     * 2.  if (leftsubtree == null || leftsubtree.max <=  low) go right
     * 3.  else go left
     *
     * @param start     the start of the interval
     * @param end       the end of the interval
     * return           true if overlap, else false.
     */
    public boolean overlap(int start, int end) {
        if (start >= end) throw new IllegalArgumentException("The end " + end + " should be greater than start " + start);

        IntervalNode intervalNode = root;

        while (intervalNode != null) {
            if (intersection(start, end, intervalNode.start, intervalNode.end)) return true;

            // OR: intervalNode.left != null && intervalNode.left.maxEnd >= start
            if (intervalNode.left != null && start < intervalNode.left.maxEnd) {
                intervalNode = intervalNode.left;
            } else {
                intervalNode = intervalNode.right;
            }
        }

        return false;
    }

    /**
     * Returns if there is an intersection in the two intervals
     * Two intervals such that one of the points coincide:
     * eg: [10, 20] and [20, 40] are NOT considered as intersecting.
     */
    private boolean intersection (int start, int end, int intervalStart, int intervalEnd) {
        // OR: start <= intervalEnd && end >= intervalStart;
        return start < intervalEnd && intervalStart < end;
    }

    public static void main(String[] args) {
        IntervalSearchTree intervalSearchTree = new IntervalSearchTree();
        intervalSearchTree.add(17, 19);
        intervalSearchTree.add(5, 8);
        intervalSearchTree.add(21, 24);
        intervalSearchTree.add(5, 8);
        intervalSearchTree.add(4, 8);
        intervalSearchTree.add(15, 18);
        intervalSearchTree.add(7, 10);
        intervalSearchTree.add(16, 22);

        System.out.println("Expected true,   Actual: " + intervalSearchTree.overlap(23, 25));
        System.out.println("Expected false,  Actual: " + intervalSearchTree.overlap(12, 14));
        System.out.println("Expected true,   Actual: " + intervalSearchTree.overlap(21, 23));
        // testing adjoint
        System.out.println("Expected false,  Actual: " + intervalSearchTree.overlap(10, 15));
        System.out.println("Expected false,  Actual: " + intervalSearchTree.overlap(10, 14));
        System.out.println("Expected false,  Actual: " + intervalSearchTree.overlap(11, 15));
    }
}
