package Arrays;

/**
 * https://www.programcreek.com/2013/01/leetcode-remove-duplicates-from-sorted-array-java/
 */


/**
 * http://discuss.leetcode.com/questions/207/remove-duplicates-from-sorted-array
 * http://stackoverflow.com/questions/1532819/algorithm-efficient-way-to-remove-duplicate-integers-from-an-array
 * http://stackoverflow.com/questions/18552420/avoiding-exceptions-by-short-circuiting?noredirect=1#comment27291627_18552420
 * http://codereview.stackexchange.com/questions/30592/code-review-request
 * 
 * Complexity: O(n)
 * 
 * Diagram:
 * https://bitbucket.org/ameyapatil/all-images/commits/a3cb6544120010dd6ddf309bed42ef1aa44f8b37
 * 
 * 
 * Eliminate duplicates: geek => gk
 * remove warmup.duplicate    : geek => gek
 * 
 * BB : 13
 *
 */
public final class RemoveDuplicateSorted {
    
    private RemoveDuplicateSorted () { }

    /*
     * NOTE - Since, input is sorted, we ignore the case of 'order NOT maintained-same array'.
     */
    public static int orderMaintainedSameArray(int[] a) {
        if (a == null) {
            throw new NullPointerException();
        } 

        int ctr = 0;
        
        for (int i = 0; i < a.length; i++) {
            // include last element by default, if not the last element, do the check for duplication.
            if (i == a.length - 1 || a[i] != a[i + 1]) {
                a[ctr] = a[i];
                ctr++;
            }
        } 
        
        /**
         * Since collection sort does not return an array we too, will not
         * return an array.
         */
        return ctr;
    }
}
