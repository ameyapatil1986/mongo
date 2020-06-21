package Stack;

import java.util.*;


/**
 * https://leetcode.com/problems/asteroid-collision/
 * https://www.youtube.com/watch?v=6GGTBM7mwfs
 *
 * We are given an array asteroids of integers representing asteroids in a row.
 *
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
 *
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 */
public class AsteroidCollision {

    //  asteroids = [-2, -1, 1, 2]
    public static List<Integer> asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        List<Integer> leftAsteroids = new ArrayList<>();
        List<Integer> rightAsteroids = new ArrayList<>();

        for (int i = 0; i < asteroids.length; i++) {

            if (asteroids[i] >= 0) {
                stack.push(asteroids[i]);
            } else {
                while (true) {
                    if (stack.peek() == Math.abs(asteroids[i])) {
                        stack.pop();
                        break;
                    } else if (stack.peek() < Math.abs(asteroids[i])) {
                        stack.pop();
                        if (stack.isEmpty()) {
                            leftAsteroids.add(asteroids[i]);
                        }
                    } else if (stack.peek() > Math.abs(asteroids[i])) {
                        break;
                    }
                }
            }

            // if 0 throw some illegal state exception.
        }

        while (!stack.isEmpty()) {
            rightAsteroids.add(stack.pop());
        }
        Collections.reverse(rightAsteroids);
        leftAsteroids.addAll(rightAsteroids);

        return leftAsteroids;
    }

    public static void main(String[] args) {
        int[] asteroid1 = {-5, 5};          // Output: [-5, 5]
        int[] asteroid2 =  {5, 10, -5};     // Output: [5, 10]
        int[] asteroid3 =  {10, 2, -5};     // Output: [10, 2, -5]
        int[] asteroid4 =  {-2, -1, 1, 2};  // Output: [-2, -1, 1, 2]
        for (int i :asteroidCollision(asteroid1)) {
            System.out.println("------: " + i);
        }
    }
}

