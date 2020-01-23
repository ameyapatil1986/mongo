

/**
 * Complexity: Time Complexity: O(logn) : example 7. ,where n is number of 7.
 * Brian Kernighan’s Algorithm.
 * Beauty of the this solution is number of times it loops is equal to the number of set bits in a given integer
 * 
 * References:
 * http://www.geeksforgeeks.org/count-set-bits-in-an-integer/
 * http://www.geeksforgeeks.org/write-a-c-program-to-find-the-parity-of-an-unsigned-integer/
 * 
 * Logic:
 * Consider numbers 0 to 7.
 * 0 0 0 
 * 0 0 1
 * 0 1 0
 * 0 1 1 - 
 * 1 0 0 - 
 * 1 0 1 - 
 * 1 1 0 - will result in 2
 *         6 & 5 => (4)100
 *         4 & 3 => 0 
 * 1 1 1 - will result in 3 
 *         7 & 6 => (6)110
 *         6 & 5 => (4)100
 *         4 & 3 => 0   
 * 
 * Asked at: VMWare.
 * 
 * BB: 4
 * 
 * 
 * SAME AS:
 *  http://www.geeksforgeeks.org/write-a-c-program-to-find-the-parity-of-an-unsigned-integer/
 */
public class CountNumberOfOnesInBinaryNumber {
    
    // NOT TO GIVE THIS ANSWER.
    public static int countNumberOfOnesNotOptimizedNotToGiveThisAnswer(int n) {
        int count = 0;
        while (n != 0) {
            if ((n & 1) != 0) {
                count++;
            }
            n = n >>> 1; // use >>> to make it work for negative numbers.
        }
        return count;
    }

    // O(number of bits set)
    // works for negative numbers.
    /*
     * BENEFIT:
     * Brian Kernighan’s Algorithm.
     * Beauty of the this solution is number of times it loops is equal to the number of set bits in a given integer
     * Time Complexity: O(logn) : example 7. ,where n is number of 7.
     * An integer n has log(n) bits, hence the worst case is O(log(n)). 
     * Complexity: http://stackoverflow.com/questions/12380478/bits-counting-algorithm-brian-kernighan-in-an-integer-time-complexity
     */
    
    /*
     * LOGIC:
     * ------
     * http://stackoverflow.com/questions/12383002/please-explain-the-logic-behind-kernighans-bit-counting-algorithm
     * 
     * Decrementing by one flips the lowest bit and every bit up to the first one. 
     * e.g. if you have 1000....0000 -1 = 0111....1111 not matter how many
     * bits it has to flip and it stops there leaving any other bits set untouched. 
     * When you and this with n the lowest bit set and only the lowest bit becomes 0
     * 
     * My explanation:
     * It takes only single step to reach the first set bit.
     * This is where we win over previous method.
     * Consider: 12 -> 1100
     * first set bit needs 2 iterations in the previous method.
     * With BK method: 12 & 11 ie 1100 & 1011 -> 1000
     * ie just 1 step.
     * 
     * then repeat the following.
     */
    
    
    /*
     * sample example:
     * 12
     * 1100 & 1011
     * 1000 & 111
     * 
     */
    public static int countNumberOfOnes(int n) {        
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n-1);
        }
        return count;
    }
    
    public static void main(String args[]) {
        System.out.println("Expected: 2, Actual: " + countNumberOfOnes(-10) + " "  + countNumberOfOnesNotOptimizedNotToGiveThisAnswer(-10));
        System.out.println("Expected: 3, Actual: " + countNumberOfOnes(7) + " " + countNumberOfOnesNotOptimizedNotToGiveThisAnswer(7));
        System.out.println("Expected: 1, Actual: " + countNumberOfOnes(8) + " " + countNumberOfOnesNotOptimizedNotToGiveThisAnswer(8));
        System.out.println("Expected: 0, Actual: " + countNumberOfOnes(0) + " " + countNumberOfOnesNotOptimizedNotToGiveThisAnswer(0));
    }
}
