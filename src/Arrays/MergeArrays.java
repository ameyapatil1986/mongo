package Arrays;


public class MergeArrays {

    private MergeArrays() {}

    public static void mergeSortedArrays(Integer[] big, Integer[] small) {
        if (big.length < small.length) {
            throw new IllegalArgumentException("This is bad length");
        }

        int limit = big.length - small.length -1;
        mergeSortedArrays(big, limit, small);
    }

    // bigarry: [1, 3, -, - ]  small array:[2, 4] => [1, 2, 3, 4]
    private static void mergeSortedArrays(Integer[] big, int limit, Integer[] small) {
        int limitIndex = limit;
        int bigIndex = big.length - 1;
        int smallIndex = small.length - 1;

        while (smallIndex >= 0) {
            /*
             * limitIndex < 0 can occur in once such scenario's in which the big array
             * always had 'all elements' bigger than 'all elements' in the small array.
             */
            // for merge sort: if (pos2 > high || pos1 <= mid && a[pos1] <= a[pos2])
            if (limitIndex < 0 || big[limitIndex] <= small[smallIndex]) {
                big[bigIndex--] = small[smallIndex--];
            } else {
                big[bigIndex--] = big[limitIndex--];
            }
        }
    }

    public static void main(String args[]) {

    }
}
