package BasicMath;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/count-primes/
 */


/**
 * A prime number util.
 * Computes:
 * - nth prime
 * - the next prime
 * - all primes upto n
 * This class is thread safe.
 *
 * This code has 3 things to understand:
 * 1. finding the next odd multiple or prime. ( its explanation is nested in the comments )
 * 2. shifting indexes within an array. ( this can be explained in 2 phases )
 *    - look at the commented code. it is a code without adjustment within the array.
 *    - now look at uncommented code. it is adjustment within the array is well commented out.
 * 3. Why do we only loop upto the root of the number ? ( once again explained in the comments )
 *
 * References
 * -----------
 * http://www.algolist.net/Algorithms/Number_theoretic/Sieve_of_Eratosthenes
 * http://stackoverflow.com/questions/2582732/time-complexity-of-sieve-of-eratosthenes-algorithm
 * http://www.mathblog.dk/project-euler-problem-7/
 * http://codereview.stackexchange.com/questions/38214/find-next-consecutive-prime-and-find-nth-prime
 * https://www.youtube.com/watch?v=3RfYfMjZ5w0
 * http://codereview.stackexchange.com/questions/44929/prime-number-util-class
 *
 * BB: 10
 *
 * Complexity:
 * http://stackoverflow.com/questions/2582732/time-complexity-of-sieve-of-eratosthenes-algorithm
 * http://stackoverflow.com/questions/28857407/modification-of-sieve-of-eratosthenes-time-and-space-complexity-check
 *
 * If sieve size is 'n' the time complexity is O(n log (log n)) as per simple harmonic motion formula.
 * Issue is we break them into sequence of steps. Now I am not sure of complexity.
 * But we break down into small steps of default size, thus we will give answer
 * O (n log (logn))  by combining multiple small steps we take.
 *
 *
 */
public class PrimeUtil {
    /*
     * http://answers.yahoo.com/question/index?qid=20070904071500AA4JAYn
     */
    private static final boolean COMPOSITE = true;
    private static final int DEFAULT_SIZE = 100;

    // cache of primes.
    public final List<Integer> primes;
    public int cachedMaxPrime;

    public PrimeUtil() {
        primes = new ArrayList<Integer>();
        // initial seed
        primes.addAll(Arrays.asList(2, 3));
        cachedMaxPrime = primes.get(primes.size() - 1);
    }

    private void validate(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Expecting a non-zero value");
        }
    }

    /**
     * Find the nth prime. ie if n == 6, then return 13.
     *
     * @param nThPrime     the nth prime
     * @return      the prime at nth position
     */
    /*
     * Complexity is : O(m * n log logn), m is number of times while loops, while n size of seive. eg:
     */
    public synchronized int getNthPrime(int nThPrime) {
        validate(nThPrime);

        /*
         * imagine only 2 being in primes arraylist at position 0.
         * and nthprime was = 1 ie 1st prime.
         */
        if (nThPrime <= primes.size()) {
            return primes.get(nThPrime - 1);
        }
        int n = DEFAULT_SIZE; // find all primes for next 100 numbers.

        /*
         * Complexity is O(m) for this outer while loop.
         */
        while (primes.size() < nThPrime) {
            /*
             * complexity is O(log log n) since there is a simple harmonic motion.
             */
            computePrimesUptoN(n);
            n += DEFAULT_SIZE; // find all primts for next 100 numbers.
        }
        return primes.get(nThPrime - 1);
    }

    /**
     * Given an input prime, return the next prime.
     * ie, if prime == 13 then return 17, ie 17 is the next prime of 13.
     *
     * @param prime     the prime number whose next should be found
     * @return          the next prime of the input prime.
     */
    /*
     * Complexity is : O(n log logn), where n is value of input next prime.
     * Eg: if input is 7, then the value of n is 11.
     */
    public synchronized int getNextPrime(int prime) {
        validate(prime);

        int primeIndex = Collections.binarySearch(primes, prime);
        int n = DEFAULT_SIZE; // adding cachedMaxPrime to DEFAULT_SIZE is a tiny optimization, nothing else.

        /*
         * Complexity is O(n) for this outer while loop.
         */
        while (primeIndex == -1 || primeIndex == (primes.size() - 1)) {
            /*
             * complexity is O(log log n) since there is a simple harmonic motion.
             */
            computePrimesUptoN(n);
            n += DEFAULT_SIZE;
            primeIndex = Collections.binarySearch(primes, prime);
        }
        return primes.get(primeIndex + 1);
    }

    /**
     * Given an input n, find all primes from 0 to n
     * Returns an unmodifiable list.
     *
     * @param n     the number upto which the primes should be calculated
     * @return      An unmodifiable list.
     */
    /*
     * Complexity is : O(log logn), where n is the input value of n.
     * Not: this complexity is not O(log log n).
     *
     * Space complexity: O(n).
     */
    public synchronized List<Integer> getPrimesUptoN(int n) {
        validate(n);

        if (n <= cachedMaxPrime) {
            List<Integer> list = new ArrayList<Integer>();
            for (int i = 0; primes.get(i) <= n; i++) {
                list.add(i);
            }
            return Collections.unmodifiableList(list);
        }
        return Collections.unmodifiableList(computePrimesUptoN(n));
    }

    /**
     * Means if n = 50, then return all primes upto 50.
     * ie: 2, 3, 5, 7, 11, 13, 21, 17, 19, 23, 29, 31, 37, 41, 43, 47
     *
     * @param n
     * @return
     */
    private List<Integer> computePrimesUptoN(int n) {
        // composite is name of the sieve, ie nothing else but the sieve.
        // optimizing the sieve size, but trimming it to "n - cacheprime"

        /*
         * 1. composites is of size: (50 - 13) : 37.
         * 2. basically 14...50.
         * 3. basically 14 is at index 0. basically all even numbers are at even indices.
         * 4. basically 15 is position 1.
         * 6. basically each number is offset by 'cachedPrime + 1'. Eg: 14 is mapped to index 0, ie 14 is offset by '13 + 1'
         */

        boolean[] composites = new boolean[n - cachedMaxPrime]; //
        int root = (int)Math.sqrt(n);                           // root is sqrt(50) ie 7.

        // loop through all "first prime upto max-cached-primes"

        /*
         * We need i <= root, and NOT i < root
         * Try cache of {3, 5, 7} and n of 50. you will really why
         * assume initial cache of {3, 5, 7, 11, 13}.
         * Now, we can skip 11 and 13 if n = 50.
         * Thats because until 50, all multiples of 11 and all multiples of 13, will be marked true by {3, 4, 5}'s multiples.
         * Eg:
         * 11 * 2 = 22 => composite marked true, since even
         * 11 * 3 = 33 => composite marked true, since multiple of 3.
         * 11 * 4 = 44 => composite marked true, since even
         *
         * Now, same for 13.
         * 13 * 2  = 26 => composite marked true, since even
         * 13 * 3  = 39 => composite marked true, since multiple of 3.
         *
         * --------------------------------------------------------------------------
         *
         *
         * Also start from i = 1, primes.get(0) == 2, and we skip even indices of composite array.
         *
         */

        /**
         * https://www.quora.com/What-is-the-time-complexity-for-implementing-the-Sieve-of-Eratosthenes
         *
         * Complexity is O(n * log (log n)).
         *
         * Log (log n) is complexity of the following formula (1/2 + 1/3 + 1/5 ... 1/x)
         *
         * n  * (1/2 + 1/3 + 1/5 + .. ) will make it O(n * log ( logn ));
         *
         * More explanation:
         * -----------------
         * 1. when prime is 2, we call filterComposites, thus effectively -> n/2
         * 2. when prime is 3, we call filterComposites, thus effectively -> n/3
         * 3. when prime is 5, we call filterComposites, thus effectively -> n/5
         * 4. thus eventually: n ( 1/2 + 1/3 + 1/5 .. )
         * 5. thus n * log (log n)
         *
         *
         * We get log logn by some mathematical formula which we do not care about.
         *
         * Initially we loop the composite array 1/6 times ( when prime is 3)
         * Then we loop it 1/10 times (when prime is 5)
         * effectively creating a harmonic motion.
         */
        for (int i = 1; i < primes.size() && primes.get(i) <= root; i++) { // will loop until prime = 7.
            int prime  = primes.get(i); // first prime: 3

            // get the first odd multiple of this prime, greater than max-prime
            // (13 / 3) * 3 + 3 => 15
            // (13 / 5) * 5 + 5 => 15
            // (13 / 7) * 7 + 7 => 14
            int firstPrimeMultiple = (cachedMaxPrime / prime) * prime + prime;
            if (firstPrimeMultiple % 2 == 0) {
                /*
                 * since we know that no even number other than 2 can be a prime, we only want to consider odd numbers
                 * while filtering.
                 */

                // 14 + 7 => 21
                firstPrimeMultiple += prime;
            }
            /*
             * Combos' called
             * prime: 3, firstPrimeMultiple: 15 ( 15 is the first odd multiple of 3, after '13' the max cached prime. )
             * prime: 5, firstPrimeMultiple: 15 ( 15 is the first odd multiple of 5, after '13' the max cached prime. )
             * prime: 7, firstPrimeMultiple: 21 ( 21 is the first odd multiple of 7, after '13' the max cached prime. )
             */
            filterComposites(composites, prime, firstPrimeMultiple, n);
        }

        // loop through all primes in the range of max-cached-primes upto root.
        for (int prime = cachedMaxPrime + 2; prime <= root; prime = prime + 2) {
            // selecting all the prime numbers.
            if (!composites[prime - (cachedMaxPrime + 1)]) {
                /*
                 * Assume: the max cached prime was 3, so we will begin with 5, then 7, 9 etc.
                 *
                 * prime 5 * 1 (it is prime, not to be marked as composite so skip.)
                 * prime 5 * 2 (this is even, so skip)
                 * prime 5 * 3 ( you can proceed from 15 onwards, since prime * 3 is definately not a prime )
                 *
                 * prime 7 * 1 (it is prime, not to be marked as composite so skip.)
                 * prime 7 * 2 (this is even, so skip)
                 * prime 7 * 3 ( you can proceed from 15 onwards, since prime * 3 is definately not a prime )
                 *
                 * prime 9 * 1 ( well, 9 is > root, so we do not need to process it. it would be taken care by 3 )
                 *
                 */
                filterComposites(composites, prime, 3*prime, n);
            }
        }

        // by doing i + 2, we essentially skip all even numbers
        // also skip cachedMaxPrime, since quite understandably its already cached.
        for (int i = 1; i < composites.length; i = i + 2) {
            if (!composites[i]) {
                /*
                 * each number is offset by (cachedMaxPrime + 1) ie in our example: 14 (13 + 1), as 14 is mapped to index 0.
                 */
                primes.add(i + (cachedMaxPrime + 1));
            }
        }

        cachedMaxPrime = primes.get(primes.size() - 1);
        return primes;
    }

    private void filterComposites(boolean[] composites, int prime, int firstMultiple, int n) {
        // we want to add prime, twice to the multiple so that we only bother to filter odd-numbers.

        for (int multiple = firstMultiple; multiple < n; multiple += prime + prime) {

            //
            // Each number is offset by 'cachedPrime + 1'. Eg: 14 is mapped to index 0, ie 14 is offset by '13 + 1'
            // eg: assume n = 50, cachemax = 13, and multiple = 15, then 15 should be at composite position of 1.
            // 13 should not exist in array, since we did 50 - 13 => 37. thus array begins with 14.
            // 14 should be at pos: 0
            // 15 should be at pos: 1
            composites[multiple - (cachedMaxPrime + 1)] = COMPOSITE;

            /*
             * prime: 3, firstMultiple: 15
             * -  composite[15 - 13 - 1] = composite[1]  = true;
             * -  composite[21 - 13 - 1] = composite[7]  = true;
             * -  composite[27 - 13 - 1] = composite[13] = true;
             * -  composite[33 - 13 - 1] = composite[19] = true;
             * -  composite[39 - 13 - 1] = composite[23] = true;
             * -  composite[45 - 13 - 1] = composite[31] = true;
             */

            /*
             * prime: 5, firstMultiple: 15
             * -  composite[15 - 13 - 1] = composite[1]  = true;
             * -  composite[25 - 13 - 1] = composite[11] = true;
             * -  composite[35 - 13 - 1] = composite[21] = true;
             * -  composite[45 - 13 - 1] = composite[31] = true;
             */

            /*
             * prime: 7, firstMultiple: 21
             * -  composite[21 - 13 - 1] = composite[9] = true;
             * -  composite[35 - 13 - 1] = composite[21] = true;
             * -  composite[49 - 13 - 1] = composite[35] = true;
             */
        }
    }


    public static void main(String[] args) {
        PrimeUtil myPrimes = new PrimeUtil();
        for (int i : myPrimes.getPrimesUptoN(50)) {
            System.out.print(i + ":");
        }
        System.out.println();
        //        for (int i : myPrimes.getPrimesUptoN(100)) {
        //            System.out.print(i + ":");
        //        }

        //        System.out.println(myPrimes.getNthPrime(1));
        //        System.out.println(myPrimes.getNthPrime(2));
        //
        //        System.out.println(myPrimes.getNextPrime(2));
    }
}
