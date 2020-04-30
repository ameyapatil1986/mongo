package Arrays.container;

/**
 * https://leetcode.com/problems/container-with-most-water/
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 */
public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;

        while (l < r) {
            // NOTE: these are lines, not histograms.
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }

        return maxarea;
    }

}
