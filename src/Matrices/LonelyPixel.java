package Matrices;


/**
 * https://www.careercup.com/question?id=5757729091092480
 *
 * Given an N x M image with black pixels and white pixels,
 * if a pixel is the only one in its color throughout its entire row and column,s
 * then it is a lonely pixel. Find the number of lonely pixels in black from the image. (O(NM))
 *
 *
 */
public class LonelyPixel {

    static void findLonely(int[][] pixels) {
        final int ROWS = pixels.length;
        final int COLS = pixels[0].length;

        int[] r = new int[ROWS];
        int[] c = new int[COLS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (1 == pixels[i][j]) {
                    r[i]++;
                    c[j]++;
                }
            }
        }

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (pixels[i][j] == 1 && r[i] == 1 && c[j] == 1) {
                    System.out.printf("lonely pixel at %d %d %n", i, j);
                }
            }
        }
    }

    public static void main(final String args[]) {

    }
}
