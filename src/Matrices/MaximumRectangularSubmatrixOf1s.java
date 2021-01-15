package Matrices;

/**
 * O ( n * m )
 *
 * https://www.youtube.com/watch?v=g8bSdXCG-lA
 */
public class MaximumRectangularSubmatrixOf1s {

    public int maximum(int input[][]) {
        int temp[] = new int[input[0].length];
       // LargestRectangleInHistogram lrh = new LargestRectangleInHistogram();
        int maxArea = 0;
        int area = 0;

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                if (input[i][j] == 0){
                    temp[j] = 0;
                } else {
                    temp[j] += input[i][j];  // i believe temp[j]++ should also be able to work.
                }
            }
         //   area = mh.maxHistogram(temp);
            if(area > maxArea) {
                maxArea = area;
            }
        }

        return maxArea;
    }
}
