package Arrays;

/**
 * https://leetcode.com/problems/majority-element/
 */
public class MajorityElement {

    /* Function to print Majority Element */
    void printMajority(int a[], int size) {
        /* Find the candidate for Majority*/
        int cand = findCandidate(a, size);

        if (isMajority(a, size, cand))
            System.out.println(" " + cand + " ");
        else
            System.out.println("No Majority Element");
    }

    /* Function to find the candidate for Majority */
    int findCandidate(int[] a, int size) {
        int majIndex = 0, count = 1;

        for (int i = 1; i < size; i++) {
            if (a[majIndex] == a[i]) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                majIndex = i;
                count = 1;
            }
        }
        return a[majIndex];
    }

    /* Function to check if the candidate occurs more
      than n/2 times */
    boolean isMajority(int a[], int size, int cand) {
        int i, count = 0;
        for (i = 0; i < size; i++) {
            if (a[i] == cand)
                count++;
        }
        return count > size / 2;
    }

}
