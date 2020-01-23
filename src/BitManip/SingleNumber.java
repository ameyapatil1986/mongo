package BitManip;

/**
 * https://leetcode.com/problems/single-number/
 */
public class SingleNumber {

    public static int nonDuplicate(int[] a) {
        
        int result = a[0];
        for (int i = 1; i < a.length; i++) {
            result = result ^ a[i];
        }
        return result;
    }
    
    public static void main(String[] args) {
        
        int[] a = {1, 1, 2, 3, 3, 4, 4};
        System.out.println(nonDuplicate(a));
        
    }
}
