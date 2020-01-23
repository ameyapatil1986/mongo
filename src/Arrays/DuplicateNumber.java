package Arrays;

/**
 * https://www.programcreek.com/2015/06/leetcode-find-the-duplicate-number-java/
 */
public class DuplicateNumber {

    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;

        do{
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while(slow != fast);

        int find = 0;

        while(find != slow){
            slow = nums[slow];
            find = nums[find];
        }
        return find;
    }
}
