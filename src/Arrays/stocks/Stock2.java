package Arrays.stocks;

import java.util.*;

/**
 * https://www.cluelesscode.com/best-time-to-buy-and-sell-stock-ii-cpp/
 */
public class Stock2 {

    int maxProfit(int[] prices) {

        int total = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i-1] < prices[i]) {
                total += prices[i] - prices[i-1];
            }
        }

        return total;

    }


}
