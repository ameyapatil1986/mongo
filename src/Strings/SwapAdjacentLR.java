package Strings;

/**
 * https://linlaw0229.github.io/2019/03/09/777-Swap-Adjacent-in-LR-String/
 *
 * O(n)
 *
 */
public class SwapAdjacentLR {

      public  boolean canTransform(String s, String t) {
            if(s.length() != t.length()) return false;

          /**
           * RX XR -> true
           * XR RX -> false
           *
           * XL LX: true
           * LX XL: false
           */
          for(int i = 0, j = 0; i < s.length() && j < t.length(); ) {

                if (s.charAt(i) == 'X') i++;
                if (t.charAt(j) == 'X') j++;

                if (s.charAt(i) != 'X' && t.charAt(j) != 'X') {
                    // XL, XR
                    if (s.charAt(i) != t.charAt(j)) return false;

                    // LX & XL.  here both chars for i and j are 'L'. Since L cannot move right, its a false.
                    if (s.charAt(i) == 'L' && i < j) return false;
                    // XR & RX  here both chars for i and j are 'R'. Since R cannot move left, its a false.
                    if (t.charAt(j) == 'R' && j < i) return false;
                    else {
                        //printf("%c, %c, %d, %d\n", s[i], t[j], i, j);
                        i++;
                        j++;
                    }
                }
            }

            return true;
      }

}
