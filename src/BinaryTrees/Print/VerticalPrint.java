package BinaryTrees.Print;

import java.util.*;

// Data Structure to store a binary tree node
class Node
{
    int key;
    Node left = null, right = null;

    Node(int key) {
        this.key = key;
    }
}

public class VerticalPrint {

    // Recursive function to do a pre-order traversal of the tree and fill map
    // Here node has 'dist' horizontal distance from the root of the tree
    public static void printVertical(Node node, int dist, Map<Integer, List<Integer>> map) {
        if (node == null) {
            return;
        }

        map.putIfAbsent(dist, new ArrayList<>()).add(node.key);

        printVertical(node.left, dist - 1, map);
        printVertical(node.right, dist + 1, map);
    }

    // Function to perform vertical traversal of a given binary tree
    public static void printVertical(Node root) {
        Map<Integer, List<Integer>> map = new TreeMap<>();

        printVertical(root, 0, map);

        for (Collection<Integer> collection: map.values()) {
            System.out.println(collection);
        }
    }

}
