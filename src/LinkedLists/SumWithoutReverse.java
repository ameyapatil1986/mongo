package LinkedLists;

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

    public Node doSomething() {
        LinkedList l1 = new LinkedList();
        LinkedList l2 = new LinkedList();

        if (l1.size() >  l2.size()) {
            int diff = l1.size()  - l2.size();
            Node n1 = null; // l1.getFirst();
            Node n2 = null; // l1.getFirst();
            for (int i = 0; i < diff; i++) {
                n1 = n1.next;
            }
            NodeData d = recursively(n1, n2);
            recursiveLongerLinkedList(n1, diff, d);

            return d.node;
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
