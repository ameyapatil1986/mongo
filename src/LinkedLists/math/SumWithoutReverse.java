package LinkedLists.math;

import LinkedLists.Intersection;
import java.util.LinkedList;


/**
 * https://github.com/cherryljr/LeetCode/blob/master/Add%20Two%20Numbers%20II.java
 *
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 */
public class SumWithoutReverse {

    private Node first;

    private static class Node {
        int element;
        Node next;

        Node(int element, Node next) {
            this.element = element;
            this.next = next;
        }
    }

    private static class NodeData {
        Node node;
        int carry;

        NodeData(Node node, int carry) {
            this.node = node;
            this.carry = carry;
        }
    }

    private Node fetchNodeAtEqualLevel(Node node, int levelsToJumpUp) {
        for (int count = 0; count < levelsToJumpUp; count++) {
            node = node.next;
        }
        return node;
    }


    public Node doSomething(SumWithoutReverse ll) {
        int len1 = 0;
        int len2 = 0;

        for (Node p = first; p != null; p = p.next, len1++);
        for (Node p = ll.first; p != null; p = p.next, len2++);

        Node p1 = fetchNodeAtEqualLevel(first,    len1 - len2);
        Node p2 = fetchNodeAtEqualLevel(ll.first, len2 - len1);

        NodeData d = recursively(p1, p2);
        if (len1 > len2) {
            recursiveLongerLinkedList(p1, len1 - len2, d);
        } else {
            recursiveLongerLinkedList(p2, len2 - len1, d);
        }

        return null;
    }

    // return an object with node and carry
    public NodeData recursively(Node n1, Node n2) {
        if (n1 == null) {
            return new NodeData(null, 0);
        }

        NodeData n = recursively(n1.next, n2.next);

        int sum = n1.element + n2.element + n.carry;
        Node node = new Node(sum % 10, n.node);
        int carry = sum / 10;

        n.node = node;
        n.carry = carry;

        return n;
    }

    public NodeData recursiveLongerLinkedList(Node n1, int diff, NodeData pNodeData) {
        if (diff == 0) {
            return pNodeData;
        }

        NodeData n = recursiveLongerLinkedList(n1.next, diff - 1, pNodeData);

        int sum = n1.element + n.carry;
        Node node = new Node(sum % 10, n.node);
        int carry = sum / 10;

        n.node = node;
        n.carry = carry;

        return n;
    }
}
