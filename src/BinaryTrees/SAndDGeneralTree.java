package BinaryTrees;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


class TreeSDGeneralNode<E> {
    TreeSDGeneralNode<E> left;
    E element;
    TreeSDGeneralNode<E> right;

    TreeSDGeneralNode(TreeSDGeneralNode<E> left, E element, TreeSDGeneralNode<E> right) {
        this.left = left;
        this.element = element;
        this.right = right;
    }
}

class TGree<E> {

    private TreeSDGeneralNode<E> root;

    public TGree(List<E> items) {
        create(items);
    }

    public TGree(TreeSDGeneralNode<E> root) {
        this.root = root;
    }

    private void create (List<E> items) {

        root = new TreeSDGeneralNode<E>(null, items.get(0), null);

        final Queue<TreeSDGeneralNode<E>> queue = new LinkedList<TreeSDGeneralNode<E>>();
        queue.add(root);

        final int half = items.size() / 2;

        for (int i = 0; i < half; i++) {
            if (items.get(i) != null) {
                final TreeSDGeneralNode<E> current = queue.poll();
                final int left = 2 * i + 1;
                final int right = 2 * i + 2;

                if (items.get(left) != null) {
                    current.left = new TreeSDGeneralNode<E>(null, items.get(left), null);
                    queue.add(current.left);
                }
                if (right < items.size() && items.get(right) != null) {
                    current.right = new TreeSDGeneralNode<E>(null, items.get(right), null);
                    queue.add(current.right);
                }
            }
        }
    }

    public TreeSDGeneralNode<E> getRoot() {
        return root;
    }

    private int hashCompute (TreeSDGeneralNode<E> node, int item) {
        if (node == null) return item;
        item = 31 * hashCompute (node.left, item) + node.element.hashCode(); // OR: node.item.hashCode() if generics are used.
        return hashCompute(node.right, item);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TGree<E> other = (TGree<E>) obj;
        return equal(root, other.getRoot());
    }

    private  boolean equal(TreeSDGeneralNode<E> node1,  TreeSDGeneralNode<E> node2) {
        if (node1 == null && node2 == null) return true;
        if (node1 == null || node2 == null) return false;
        if (!node1.element.equals(node2.element)) {
            return false;
        }
        return equal(node1.left, node2.left) && equal(node1.right, node2.right);
    }
}


/**
 * FB:
 * https://www.careercup.com/question?id=5729456584916992
 */

/**
 *
 * http://www.geeksforgeeks.org/serialize-deserialize-binary-tree/
 * http://stackoverflow.com/questions/30601015/cheapest-marker-in-java-serialization
 *
 * Note:
 * Number of markers is n + 1. Thus (nodes + markers) => 2n + 1
 * Had we chosen pre and post order, then it would have costed us 2n.
 * But still markers is better as markers will use less space if tree contains large objects like students.
 *
 * BB:
 * 112
 *
 * Complexity:
 * O(n) both space and time.
 * http://stackoverflow.com/questions/30623706/would-serializing-to-disk-count-as-aux-space-complexity
 *
 */
public class SAndDGeneralTree {

    public static <T> void serialize(TGree<T> tree, String fileName) throws FileNotFoundException, IOException {
        /*
         * using only 1 file will create a lot of confusion in coding.
         */
        // BufferedReader br = new BufferedReader(new FileReader(filePath))
        try (ObjectOutputStream oosNodeData = new ObjectOutputStream(new FileOutputStream(fileName))) {
            preOrderSerialization(tree.getRoot(), oosNodeData);
        }
    }


    private static <T> void preOrderSerialization(TreeSDGeneralNode<T> node, ObjectOutputStream oosNodeData) throws IOException {
        if (node == null) {
            /*
             * alternate to storing null would be int max ?
             *  oosNodeData.writeObject(Integer.MAX_VALUE); / double max ?
             */
            oosNodeData.writeObject(null);

            return;
        }

        oosNodeData.writeObject(node.element);

        preOrderSerialization(node.left, oosNodeData);
        preOrderSerialization(node.right, oosNodeData);
    }

    /**
     * https://leetcode.com/problems/longest-absolute-file-path/
     */
    public static <T> TGree<T> deserialize(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        TreeSDGeneralNode<T> root = null;

        try (ObjectInputStream oisNodeData = new ObjectInputStream(new FileInputStream(fileName))) {
            root = preOrderDeserialization(oisNodeData);
        }
        return new TGree<T>(root);
    }

    private static <T> TreeSDGeneralNode<T> preOrderDeserialization(ObjectInputStream oisNodeData) throws ClassNotFoundException, IOException {
        T x  = (T)oisNodeData.readObject();

        // reading whats registered and determining if created node is the leaf or non-leaf.
        // if (preLN[ctr.ctr] == 'L') {  * from PreorderNodeAndLeaf.
        if (x == null) {
            return null;
        }

        TreeSDGeneralNode<T> node = new TreeSDGeneralNode<T>(null, x,null);

        node.left = preOrderDeserialization(oisNodeData);
        node.right = preOrderDeserialization(oisNodeData);
        return node;
    }

}
