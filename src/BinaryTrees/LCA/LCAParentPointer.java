package BinaryTrees.LCA;

public class LCAParentPointer<T> {

    private TreeNode treeNode;

    private static class TreeNode<T> {
        private TreeNode<T> left;
        private TreeNode<T> parent;
        private TreeNode<T> right;
        private T item;

        TreeNode(T item) {
            this.item = item;
        }
    }

    private int getDepthOfNode(TreeNode<T> node) {
        int level1 = 0;
        for (TreeNode<T> temp = node; temp != null; temp = temp.parent) {
            level1++;
        }
        return level1;
    }


    private TreeNode<T> fetchNodeAtEqualLevel(TreeNode<T> node, int levelsToJumpUp) {
        TreeNode<T> temp = node;
        for (int count = 0; count < levelsToJumpUp; count++) {
            temp = temp.parent;
        }
        return temp;
    }


    public T findLCA(TreeNode<T> node1, TreeNode<T> node2) {
        if (node1 == null || node2 == null) {
            throw new IllegalStateException("Both nodes should be non-null");
        }

        int level1 = getDepthOfNode(node1);
        int level2 = getDepthOfNode(node2);

        TreeNode<T> temp1 = fetchNodeAtEqualLevel(node1, level1 - level2);
        TreeNode<T> temp2 = fetchNodeAtEqualLevel(node2, level2 - level1);

        while (temp1 != temp2) {
            temp1 = temp1.parent;
            temp2 = temp2.parent;
        }

        return temp1.item;
    }
}
