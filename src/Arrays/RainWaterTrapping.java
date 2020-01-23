package Arrays;

/**
 * https://letstalkalgorithms.com/trapping-rain-water-solution-java/
 */
public class RainWaterTrapping {

    public int trap(int[] height) {
        int left = 0, right = height.length-1;
        int maxleft = 0, maxright = 0;
        int result = 0;

        while(left <= right){

            if(height[left] < height[right]){
                if(height[left]>=maxleft) maxleft = height[left];
                else result += maxleft - height[left];
                left++;
            }
            else{
                if(height[right]>=maxright) maxright = height[right];
                else result+=maxright-height[right];
                right--;
            }
        }
        return result;
    }


}
