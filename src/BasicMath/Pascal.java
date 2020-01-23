package BasicMath;

/**
 * http://www.geeksforgeeks.org/pascal-triangle/
 * https://github.com/gouthampradhan/leetcode/blob/master/problems/src/array/ProductOfArrayExceptSelf.java
 *
 * Complexity:
 * O(n2)
 *
 * BB:
 * 12
 *
 * @author SERVICE-NOW\ameya.patil
 */
public class Pascal {

    private Pascal() { }

    public static void doPascal(int n) {
        if (n <= 0) throw new IllegalArgumentException("n: " + n + " should be greater than 0.");

        for (int line = 1; line <= n; line++) {
            /*
             * code to display the triangle.
             * think about it in this way:
             * When "line == n", we dont want to print any space.
             * Thus when n - line == 0, we dont want to print any space.
             * Thus, i = 0, and i < (n - line)  i ++
             */
            for (int i = 0; i < n - line; i++) {
                System.out.print(" ");
            }

            int c = 1;
            for (int i = 1; i <= line; i++) {
                /*
                 * remember to add " ".
                 */
                System.out.print(c + " ");     // no one will care for iterator in pascal. simple print statements in the code would do the magic.
                /*
                 * https://bitbucket.org/ameyapatil/all-images/commits/56c33ac60a9cf6b3ae54c1d9f8f1db43bca71636
                 */
                c =  (c * (line - i)) / i;       // keep revising the diagram, each time you memorize this code.
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        doPascal(4);
    }
}
