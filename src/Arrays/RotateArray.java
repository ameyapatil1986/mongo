package Arrays;

/**
 * https://www.programcreek.com/2015/03/rotate-array-in-java/
 */
public class RotateArray {

    public static void rotate(int[] arr, int k) {
        if (arr == null || arr.length==0 || k < 0) {
            throw new IllegalArgumentException("Illegal argument!");
        }

        if(k > arr.length){
            k = k %arr.length;
        }

        //length of first part
        int a = arr.length - k;

        reverse(arr, 0,              arr.length - k - 1);
        reverse(arr, arr.length - k, arr.length-1);
        reverse(arr, 0,              arr.length-1);

    }

    public static void reverse(int[] arr, int left, int right){
        if(arr == null || arr.length == 1)
            return;

        while(left < right){
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
