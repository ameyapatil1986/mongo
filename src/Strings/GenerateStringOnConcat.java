package Strings;

public class GenerateStringOnConcat {

    // Function that return true
    // if pre is a prefix of str
    static boolean startsWith(String str, String pre) {
        int strLen = str.length();
        int preLen = pre.length();
        int i = 0, j = 0;

        // While there are characters to match
        while (i < strLen && j < preLen) {

            // If characters differ at any position
            if (str.charAt(i) != pre.charAt(j))
                return false;
            i++;
            j++;
        }

        // str starts with pre
        return true;
    }

    // Function that return true
    // if suff is a suffix of str
    static boolean endsWith(String str, String suff) {
        int i = str.length() - 1;
        int j = suff.length() - 1;

        // While there are characters to match
        while (i >= 0 && j >= 0)
        {

            // If characters differ at any position
            if (str.charAt(i) != suff.charAt(j))
                return false;
            i--;
            j--;
        }

        // str ends with suff
        return true;
    }

    // Function that returns true
    // if str = a + b or str = b + a
    static boolean checkString(String str, String a, String b) {

        // str cannot be generated
        // by concatenating a and b
        if (str.length() != a.length() + b.length())
            return false;

        // If str starts with a
        // i.e. a is a prefix of str
        if (startsWith(str, a)) {
            // Check if the rest of the characters
            // are equal to b i.e. b is a suffix of str
            if (endsWith(str, b))
                return true;
        }

        // If str starts with b
        // i.e. b is a prefix of str
        if (startsWith(str, b)) {
            // Check if the rest of the characters
            // are equal to a i.e. a is a suffix of str
            if (endsWith(str, a))
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
