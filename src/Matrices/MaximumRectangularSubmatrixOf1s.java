package Matrices;

/**
 * O ( n * m )
 */
public class MaximumRectangularSubmatrixOf1s {

    public int maximum(int input[][]) {
        int temp[] = new int[input[0].length];
       // LargestRectangleHistogram lrh = new LargestRectangleHistogram();
        int maxArea = 0;
        int area = 0;

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                if (input[i][j] == 0){
                    temp[j] = 0;
                } else {
                    temp[j] += input[i][j];
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
