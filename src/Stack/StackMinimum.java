//package StackCanAsk;
//
//import static org.junit.Assert.assertEquals;
//
//import java.util.Stack;
//
//
//
///**
// * https://leetcode.com/problems/max-stack/
// * References
// * http://www.geeksforgeeks.org/design-and-implement-special-stack-data-structure/
// * http://codereview.stackexchange.com/questions/55386/stack-with-getminimum-operation
// *
// * BB:
// * 12
// *
// *
// * Complexity:
// * O(1) for all
// * O(n) for space.
// *
// * Companies:
// * - Asked to ganccha.
// *
// */
//public class StackMinimum<T extends Comparable<T>> {
//    /*
//     * Composition triumphs over inheritance :)
//     */
//    private final Stack<T> stack1 = new Stack<T>();
//    private final Stack<T> stack2 = new Stack<T>();
//
//    public void push(T item) {
//        stack1.push(item);
//       /*
//        * keep it <= rather than less than.
//        * By keeping it <=, we can keep track of duplicates.
//        */
//
//        // Note: difference between this and 'Next greatest element' is
//        // in case of 'NGE' stack.compareTo(a[i]), is checked,
//        // in this case 'item.compareTo(stack2.peek())' is checked.
//        if (stack2.isEmpty() || item.compareTo(stack2.peek()) <= 0) {
//            stack2.push(item);
//        }
//    }
//
//    public T pop() {
//        T item = stack1.pop();
//        if (item.equals(stack2.peek())) {
//            stack2.pop();
//        }
//        return item;
//    }
//
//    public T peek() {
//        return stack1.peek();
//    }
//
//    public int size() {
//        return stack1.size();
//    }
//
//    public T getMinimum () {
//        return stack2.peek();
//    }
//
//    public boolean isEmpty() {
//        return stack1.isEmpty();
//    }
//
//    public static void main(String[] args) {
//        StackMinimum<Integer> stack1 = new StackMinimum<Integer>();
//        stack1.push(10);
//        stack1.push(1);
//        stack1.push(1); // important test case of duplicate. will be checked in interview.
//        stack1.push(2);
//        stack1.push(3);
//
//        assertEquals(1, (int)stack1.getMinimum());
//
//        stack1.push(-1);
//        assertEquals(-1, (int)stack1.getMinimum());
//
//        stack1.pop(); // -1
//        assertEquals(1, (int)stack1.getMinimum());
//
//        assertEquals(1, (int)stack1.getMinimum());
//
//        stack1.pop(); // 3
//        assertEquals(1, (int)stack1.getMinimum());
//
//        stack1.pop(); // 2
//        assertEquals(1, (int)stack1.getMinimum());
//
//        stack1.pop(); // 1
//        assertEquals(1, (int)stack1.getMinimum());
//
//        stack1.pop(); // 1
//        assertEquals(10, (int)stack1.getMinimum());
//    }
//}
