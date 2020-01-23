package BinaryTrees;


public class LeastCommonAncestorBST {

    private TreeNode root;

    private static class TreeNode {
        TreeNode left;
        TreeNode right;
        int item;

        TreeNode (TreeNode left, TreeNode right, int item) {
            this.left = left;
            this.right = right;
            this.item = item;
        }
    }

    public void makeBinarySearchTree(Integer[] a) {
        for (int i : a) {
            addElement(i);
        }
    }

    public void addElement(int element) {
        if (root == null) {
            root = new TreeNode(null, null, element);
        } else {
            TreeNode prevNode = null;
            TreeNode node = root;

            while (node != null) {
                prevNode = node;
                if (element < node.item) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }

            if (element < prevNode.item) {
                prevNode.left = new TreeNode(null, null, element);
            } else {
                prevNode.right = new TreeNode(null, null, element);
            }
        }
    }


    public int leastCommonAncestor(int n1, int n2) {
        TreeNode node  = findLCA(root, n1, n2);
        if (node != null) {
            return node.item;
        } else {
            throw new IllegalArgumentException(" Input was not valid ");
        }
    }

    private TreeNode findLCA(TreeNode node, int n1, int n2) {
        if (node == null) return null;

        if (n1 < node.item && n2 < node.item) {
            return findLCA(node.left, n1, n2);
        }

        if (n1 > node.item && n2 > node.item) {
            return findLCA(node.right, n1, n2);
        }

        boolean bothExist = doesExist(node, n1) && doesExist(node, n2);

        return bothExist ? node : null;
    }

    private boolean doesExist(TreeNode node, int n) {

        while (node != null) {
            if (node.item == n) {
                return true;
            }

            if (n < node.item) {
                node = node.left;
            }

            if (n > node.item ) {
                node = node.right;
            }
        }
        return false;
    }

    /**
     * Use the same 4 use cases of the binary tree - LCA
     * @param args
     */
    public static void main(String[] args) {
        /**
         * Binary tree with duplicate values.
         *
         *        50
         *    25       75
         * 12    60       75
         *                   80
         *
         */
        Integer[] arr2 = {50, 25, 75, 12, 60, 75, 80};
        LeastCommonAncestorBST commonAncestor = new LeastCommonAncestorBST();
        commonAncestor.makeBinarySearchTree(arr2);

        // duplicate requested
        int ancestor = commonAncestor.leastCommonAncestor(25, 60);
        System.out.println("Expected 50, actual " + ancestor);

        ancestor = commonAncestor.leastCommonAncestor(25, 80);
        System.out.println("Expected 50, actual " + ancestor);

        ancestor = commonAncestor.leastCommonAncestor(25, 25);
        System.out.println("Expected 25, actual " + ancestor);

        ancestor = commonAncestor.leastCommonAncestor(50, 50);
        System.out.println("Expected 50, actual " + ancestor);

        //        ancestor = commonAncestor.leastCommonAncestor(25, 12);
        //        System.out.println("Expected 25, actual " + ancestor);
        //
        //        ancestor = commonAncestor.leastCommonAncestor(50, 60);
        //        System.out.println("Expected 50, actual " + ancestor);
        //
        //        ancestor = commonAncestor.leastCommonAncestor(50, 75);
        //        System.out.println("Expected 50, actual " + ancestor);
        //
        //        ancestor = commonAncestor.leastCommonAncestor(12, 75);
        //        System.out.println("Expected 50, actual " + ancestor);
        //
        //        ancestor = commonAncestor.leastCommonAncestor(60, 80);
        //        System.out.println("Expected 75, actual " + ancestor);
        //
        //        ancestor = commonAncestor.leastCommonAncestor(75, 75);
        //        System.out.println("Expected 75, actual " + ancestor);

        /**
         * Binary tree with duplicate values.
         *          50
         *    25         75
         * 12     25  60    80
         *
         */
        Integer[] arr3 = {50, 25, 75, 12, 25, 60, 80};
        LeastCommonAncestorBST commonAncestor1 = new LeastCommonAncestorBST();
        commonAncestor1.makeBinarySearchTree(arr3);

        // duplicate requested
        System.out.println("Expected 25, actual " + commonAncestor1.leastCommonAncestor(25, 25));
    }
}
