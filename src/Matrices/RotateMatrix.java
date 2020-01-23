package Matrices;

/*
 * Generics have some issue with arrays and matrices to its best to avoid it.
 */
final class Pixel {

    private final int color;

    public Pixel(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}

/**
 * https://github.com/gouthampradhan/leetcode/blob/master/problems/src/array/RotateMatrix.java
 *
 * https://leetcode.com/problems/rotate-image/
 */
public class RotateMatrix {

    /**
     * Rotes the image in anti clockwise direction.
     *
     * @param   image the image to be rotated.
     * @return  the titled image.
     * @throws  NPE
     */
    public static Pixel[][] rotateAntiClockWise(Pixel[][] image) {
        int col = image.length;
        int row = image[0].length;

        final Pixel[][] rotatedPixel = new Pixel[row][col];

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                // my current rows = my rotated columns.
                // my current columns became inverse in my rotated matrix.
                rotatedPixel[rotatedPixel.length -1 -j][i] = image[i][j];
            }
        }
        return rotatedPixel;
    }


    /**
     * Rotates the image in clockwise direction.
     *
     * @param   image the image to be rotated.
     * @returns the rotated image.
     * @throws  NPE
     */
    public static Pixel[][] rotateClockWise(Pixel[][] image) {
        int col = image.length;
        int row = image[0].length;

        final Pixel[][] rotatedPixel = new Pixel[row][col];

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                // my current rows  became inverse in my rotated matrix.
                // my current column = my rotated row.
                rotatedPixel[j][rotatedPixel[0].length -1 - i] = image[i][j];
            }
        }
        return rotatedPixel;
    }


    public static void main(String[] args) {
        //   int[][] m = { {1, 2, 3, 4} ,
        //                {10, 20, 30, 40}};
        Pixel[][] image = new Pixel[2][4];
        image[0][0] = new Pixel(1);
        image[0][1] = new Pixel(2);
        image[0][2] = new Pixel(3);
        image[0][3] = new Pixel(4);

        image[1][0] = new Pixel(10);
        image[1][1] = new Pixel(20);
        image[1][2] = new Pixel(30);
        image[1][3] = new Pixel(40);

        Pixel[][] rotatedAntiClockNinetyDegreeImage = rotateAntiClockWise(image);

        /*
         * Expected:
         *
         * 4  40
         * 3  30
         * 2  20
         * 1  10
         *
         */
        for (int i = 0; i < rotatedAntiClockNinetyDegreeImage.length; i++) {
            for (int j = 0; j < rotatedAntiClockNinetyDegreeImage[0].length; j++) {
                System.out.print(rotatedAntiClockNinetyDegreeImage[i][j].getColor() + " ");
            }
            System.out.println();
        }

        System.out.println("==========================================");


        Pixel[][] rotatedClockWiseNinetyDegreeImage = rotateClockWise(image);

        /*
         * Expected:
         *
         * 10  1
         * 20  2
         * 30  3
         * 40  4
         *
         */
        for (int i = 0; i < rotatedClockWiseNinetyDegreeImage.length; i++) {
            for (int j = 0; j < rotatedClockWiseNinetyDegreeImage[0].length; j++) {
                System.out.print(rotatedClockWiseNinetyDegreeImage[i][j].getColor() + " ");
            }
            System.out.println();
        }
    }
}
