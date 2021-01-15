package BasicMath;

/**
 * O(nloglogn)
 */
public class CountPrimes {

    public int countPrimes(int n) {
        boolean[] composite = new boolean[n+1];

        double sqrt = Math.sqrt(n);

        for (int i = 2; i <= sqrt; i++) {
            if(!composite[i]) {
                for(int j = i * i; j <= n; j = j + i){
                    composite[j] = true;
                }
            }
        }

        int res = 0;
        for(int i = 2; i < composite.length; i++){
            if(!composite[i]) {
                res++;
            }
        }

        return res;
    }

}
