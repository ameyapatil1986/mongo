package Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class ProductSuggestion {

    public static List<List<String>> productCodes(List<String> products, String keyStrokes) {
        List<List<String>> suggestions = new ArrayList<>();

        for (int i = 0; i < keyStrokes.length(); i++) {
            String str = keyStrokes.substring(0, i + 1);
            suggestions.add(products.stream().filter(w -> w.startsWith(str)).collect(Collectors.toList()));
        }

        return suggestions;
    }

    public static void main(String[] args) {
        for (List<String> pc: productCodes(Arrays.asList("mobile","mouse","moneypot","monitor","mousepad"), "mouse")) {
            for (String s : pc) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}
