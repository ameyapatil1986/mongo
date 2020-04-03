package BinaryTrees;

import java.util.*;
import javax.xml.soap.Node;


/**
 * https://leetcode.com/problems/maximum-average-subtree/
 */
public class SubtreeMaxAvg {

    TreeNode root;

    class TreeNode {
        int x;
        List<TreeNode> nodes;
    }

    class NodeData {
        int sum;
        int count;
        double maxAvgSoFar;

        NodeData(int sum, int count, double pMaxAvgSoFar) {
            this.sum = sum;
            this.count = count;
            this.maxAvgSoFar = pMaxAvgSoFar;
        }
    }

    public double maxAvg() {
        return getAvg(root).maxAvgSoFar;
    }

    private NodeData getAvg (TreeNode node) {
        if (node == null) {
            return new NodeData(0, 0, 0);
        }

        int sum = 0;
        int count = 0;
        double maxAvg = 0.0;
        for (TreeNode currChild : node.nodes) {
            NodeData nodeData = getAvg(node);
            sum += nodeData.sum;
            count += nodeData.count;
            maxAvg = Math.max(maxAvg, nodeData.maxAvgSoFar);
        }

        int sumOfSubtree = sum + node.x;
        int sizeOfSubtree = (count + 1);
        double currentSubtreeAvg = sumOfSubtree / sizeOfSubtree;

        return new NodeData(sumOfSubtree, sizeOfSubtree,  Math.max(currentSubtreeAvg, maxAvg));
    }
}
