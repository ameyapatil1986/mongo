package Strings;

public class GenerateStringOnConcat {
    // Function that returns true
    // if str = a + b or str = b + a
    static boolean checkString(String str, String a, String b) {

        // str cannot be generated
        // by concatenating a and b
        if (str.length() != a.length() + b.length())
            return false;

        // If str starts with a
        // i.e. a is a prefix of str
        if (str.startsWith(a) && str.endsWith(b)) {
            // Check if the rest of the characters
            // are equal to b i.e. b is a suffix of str
                return true;
        }

        if (str.startsWith(b) && str.endsWith(a)) {
                return true;
        }

        return false;
    }

    // Driver code
    public static void main(String args[]) {
        String str = "GeeksforGeeks";
        String a = "Geeksfo";
        String b = "rGeeks";

        if (checkString(str, a, b))
            System.out.println("Yes");
        else
            System.out.println("No");

    }
}
