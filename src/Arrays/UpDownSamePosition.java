package Arrays;

/**
 * https://www.youtube.com/watch?v=f7Zd8hEbCz0&list=PLi9RQVmJD2fYXgBAUfaN5MXgYPUTbzqeb&index=20&t=0s
 */
public class UpDownSamePosition {

    public boolean judgeCircle(String moves) {

        int UD = 0;
        int LR = 0;

        for (int i = 0; i < moves.length(); i++) {
            char c = moves.charAt(i);

            switch (c) {
                case 'U': UD++; break;

                case 'D': UD--; break;

                case 'R': LR++; break;

                case 'L': LR--; break;
            }
        }

        return UD == 0 && LR == 0;
    }

    public static void main(String[][] args) {

    }
}
