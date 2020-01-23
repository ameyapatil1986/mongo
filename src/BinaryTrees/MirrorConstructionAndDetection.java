package BinaryTrees;

import java.util.LinkedList;
import java.util.Queue;


/**
 * https://leetcode.com/problems/invert-binary-tree/
 */
public class MirrorConstructionAndDetection {

    private TreeNode root;

    public MirrorConstructionAndDetection() {}

    private MirrorConstructionAndDetection(TreeNode root) {
        this.root = root;
    }

    private static class TreeNode {
        TreeNode left;
        TreeNode right;
        Integer element;
        TreeNode(TreeNode left, TreeNode right, Integer element) {
            this.left = left;
            this.right = right;
            this.element = element;
        }
    }

    public void createBinaryTree (Integer[] arr) {
        if (arr == null)  {
            throw new NullPointerException("The input array is null.");
        }

        root = new TreeNode(null, null, arr[0]);

        final Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        final int half = arr.length / 2;

        for (int i = 0; i < half; i++) {
            if (arr[i] != null) {
                final TreeNode current = queue.poll();
                final int left = 2 * i + 1;
                final int right = 2 * i + 2;

                if (arr[left] != null) {
                    current.left = new TreeNode(null, null, arr[left]);
                    queue.add(current.left);
                }
                if (right < arr.length && arr[right] != null) {
                    current.right = new TreeNode(null, null, arr[right]);
                    queue.add(current.right);
                }
            }
        }
    }

    public MirrorConstructionAndDetection mirrorTree() {
        if (root == null) {
            throw new NullPointerException("The root is null.");
        }
        return new MirrorConstructionAndDetection(mirrorTree(root));
    }

    private TreeNode mirrorTree(TreeNode node) {
        if (node == null) {
            return null;
        }

        TreeNode mirror = new TreeNode(null, null, node.element);
        mirror.left = mirrorTree(node.right);
        mirror.right = mirrorTree(node.left);

        return mirror;
    }


    public boolean checkMirror(MirrorConstructionAndDetection mirror) {
        return isMirror(root, mirror.root);
    }

    private boolean isMirror(TreeNode node, TreeNode mirrorNode) {

        if (node == null && mirrorNode == null) {
            return true;
        }

        if (node == null || mirrorNode == null) {
            return false;
        }

        if (node.element != mirrorNode.element) {
            return false;
        }

        return isMirror(node.left, mirrorNode.right) && isMirror(node.right, mirrorNode.left);
    }
}
