package BitManip;


/**
 * https://leetcode.com/problems/sum-of-two-integers/
 *
 * http://www.geeksforgeeks.org/add-two-numbers-without-using-arithmetic-operators/
 * 
 * BB:
 * ----
 * 13
 * 
 * Complexity:
 * -----------
 * http://stackoverflow.com/questions/29441691/time-complexity-of-bitwise-addition
 * complexity = O(1), or you can say O(size of int in bits) = O(32 bits or 64 bits) = O(1)
 */
public class AddNumbersWithoutActualAdding {

    /*
     * explanation:
     * it works similar to half adder and full adder.
     * I will supplement my partial understanding by example images on bit bucket.
     * 
     * http://en.wikipedia.org/wiki/Adder_%28electronics%29#Half_adder
     * Just follow the simplest half adder diagram on wikipedia and things will be clear itself.
     * 
     */
    public static int Add(int x, int y) {
        
        // Iterate till there is no carry  
        while (y != 0) {
           
            // http://stackoverflow.com/questions/1991380/what-does-the-operator-do-in-java
            int carry = x & y;  
            int sum = x ^ y; 

            y = carry << 1;
            x = sum;
        }
        return x;
    }


    public static void main(String[] args) {
        /*
         * https://bitbucket.org/ameyapatil/all-images/src/f73c2ce6208c1e0ea93957ad427e6c562fd3c834/HalfAdder1+1.JPG?at=master
         */
        System.out.println(Add(1, 1));
        /*
         * https://bitbucket.org/ameyapatil/all-images/src/f73c2ce6208c1e0ea93957ad427e6c562fd3c834/HalfAdder10+5.JPG?at=master
         */
        System.out.println(Add(10, 5));
    }
}
