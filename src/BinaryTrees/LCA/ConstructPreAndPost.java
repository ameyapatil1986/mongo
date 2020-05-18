package BinaryTrees.LCA;

/**
 *
 */
public class ConstructPreAndPost {

    private TreeNode root;

    private class TreeNode {
        TreeNode left;
        TreeNode right;
        int item;

        public TreeNode (TreeNode left, TreeNode right, int item) {
            this.left = left;
            this.right = right;
            this.item = item;
        }
    }

    /**
     * This function assumes that we only interested
     */
    public void construct(int[] pre, int[] post) {
        if (pre.length != post.length) {
            throw new IllegalArgumentException("The input is invalid");
        }

        root = doConstruct(pre, 0, post, 0, post.length - 1);
    }

    public TreeNode doConstruct(int[] pre, int prePos, int[] post, int lb, int  hb) {

        // leaf has been reached.
        if (lb == hb) {
            return new TreeNode(null, null, pre[prePos]);
        }


        TreeNode treeNode = new TreeNode(null, null, pre[prePos]);

        int leftChildIndexInPreOrder = prePos + 1;

        // search parent in post
        int leftChildIndexInPostOrder;
        for (leftChildIndexInPostOrder = lb; leftChildIndexInPostOrder <= hb; leftChildIndexInPostOrder++) {
            if (post[leftChildIndexInPostOrder] == pre[leftChildIndexInPreOrder]) {
                break;
            }
        }



        // next parent on same level is 'left subtree size' away from the node.
        // https://ameyapatil@bitbucket.org/ameyapatil/pointstonote.git
        int leftSubTreeSize = leftChildIndexInPostOrder - lb + 1; // this one is added to leftsubtree root.
        int rightChildIndexInPreOrder = leftChildIndexInPreOrder + leftSubTreeSize;

        treeNode.left =  doConstruct (pre,  leftChildIndexInPreOrder,     post, lb,                            leftChildIndexInPostOrder);
        treeNode.right = doConstruct (pre,  rightChildIndexInPreOrder,    post, leftChildIndexInPostOrder + 1, hb - 1);

        return treeNode;
    }

    public void traverse() {
        inorderTraversal(root);
    }

    private void inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.item + " ");
            inorderTraversal(root.right);
        }
    }



    public static void main(String[] args) {

        int[] pre = { 1, 2, 3 };
        int[] post = { 2, 3, 1 };

        ConstructPreAndPost cpap = new ConstructPreAndPost();
        cpap.construct(pre, post);
        System.out.print("Expected: 2 1 3, \nActual: ");
        cpap.traverse();

        System.out.println();

        // only works for full binary tree so this dude will fail
        int[] pre1 = { 10, 20, 30, 60, 70, 75, 80 };
        int[] post1 = { 20, 60, 75, 80, 70, 30, 10 };
        cpap.construct(pre1, post1);
        System.out.print("Expected: 20 10 60 30 75 70 80, \nActual   ");
        cpap.traverse();

        // // only works for full binary tree so this dude will fail
        // int[] pre1 = {10, 20, 40, 50, 30, 60, 70, 75, 80};
        // int[] post1 = {50, 40, 20, 60, 75, 80, 70, 30, 10};
        // cpap.construct(pre1, post1);
        // cpap.traverse();

    }
}
