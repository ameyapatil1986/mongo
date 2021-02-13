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

        reverse(arr, 0,  arr.length - 1);
        reverse(arr, 0, k-1);
        reverse(arr, k,  arr.length-1);
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

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        rotate(arr, 13);
        for (int i : arr) {
            System.out.print(i + ",");
        }
    }
}
