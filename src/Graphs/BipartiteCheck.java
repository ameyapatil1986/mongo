package Graphs;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;


class BipartiteGraph<T> implements Iterable<T> {

    /* A map from nodes in the graph to sets of outgoing edges.  Each
     * set of edges is represented by a map from edges to doubles.
     */
    private final Map<T, Map<T, Double>> graph = new HashMap<T, Map<T, Double>>();

    /**
     *  Adds a new node to the graph. If the node already exists then its a
     *  no-op.
     *
     * @param node  Adds to a graph. If node is null then this is a no-op.
     * @return      true if node is added, false otherwise.
     */
    public boolean addNode(T node) {
        if (node == null) {
            throw new NullPointerException("The input node cannot be null.");
        }
        if (graph.containsKey(node)) return false;

        graph.put(node, new HashMap<T, Double>());
        return true;
    }


    /**
     * Given the source and destination node it would add an arc from source
     * to destination node. If an arc already exists then the value would be
     * updated the new value.
     *
     * @param nodeA                    the source node.
     * @param nodeB                    the destination node.
     * @param length                    if length if
     * @throws NullPointerException     if source or destination is null.
     * @throws NoSuchElementException   if either source of destination does not exists.
     */
    public void addEdge (T nodeA, T nodeB, double length) {
        if (nodeA == null || nodeB == null) {
            throw new NullPointerException("Source and Destination, both should be non-null.");
        }
        if (!graph.containsKey(nodeA) || !graph.containsKey(nodeB)) {
            throw new NoSuchElementException("Source and Destination, both should be part of graph");
        }
        /* A node would always be added so no point returning true or false */
        graph.get(nodeA).put(nodeB, length);
        graph.get(nodeB).put(nodeA, length);
    }

    /**
     * Removes an edge from the graph.
     *
     * @param nodeA        If the source node.
     * @param nodeB   If the destination node.
     * @throws NullPointerException     if either source or destination specified is null
     * @throws NoSuchElementException   if graph does not contain either source or destination
     */
    public void removeEdge (T nodeA, T nodeB) {
        if (nodeA == null || nodeB == null) {
            throw new NullPointerException("Source and Destination, both should be non-null.");
        }
        if (!graph.containsKey(nodeA) || !graph.containsKey(nodeB)) {
            throw new NoSuchElementException("Source and Destination, both should be part of graph");
        }
        graph.get(nodeA).remove(nodeB);
        graph.get(nodeB).remove(nodeA);
    }

    /**
     * Given a node, returns the edges going outward that node,
     * as an immutable map.
     *
     * @param node The node whose edges should be queried.
     * @return An immutable view of the edges leaving that node.
     * @throws NullPointerException   If input node is null.
     * @throws NoSuchElementException If node is not in graph.
     */
    public Map<T, Double> edgesFrom(T node) {
        if (node == null) {
            throw new NullPointerException("The node should not be null.");
        }
        Map<T, Double> edges = graph.get(node);
        if (edges == null) {
            throw new NoSuchElementException("Source node does not exist.");
        }
        return Collections.unmodifiableMap(edges);
    }

    /**
     * Given a node returns neighbors.
     *
     * @param node  The input node.
     * @return      Returns all neighbors of the node.
     */
    public Set<T> getNeighbors(T node) {
        if (node == null) {
            throw new NullPointerException("The node is empty.");
        }
        if (!graph.containsKey(node)) {
            throw new NoSuchElementException("Source node does not exist.");
        }

        return Collections.unmodifiableSet(graph.get(node).keySet());
    }

    /**
     * Returns the iterator that travels the nodes of a graph.
     *
     * @return an iterator that travels the nodes of a graph.
     */
    public Iterator<T> iterator() {
        return graph.keySet().iterator();
    }
}

/**
 * http://www.keithschwarz.com/interesting/code/?dir=bipartite-verify
 * http://www.geeksforgeeks.org/bipartite-graph/
 * http://www.personal.kent.edu/~rmuhamma/Algorithms/MyAlgorithms/GraphAlgor/breadthSearch.htm
 *
 *
 * BB:
 * ---
 * 7
 *
 * Complexity:
 * -----------
 * http://stackoverflow.com/questions/11468621/why-is-the-time-complexity-of-both-dfs-and-bfs-o-v-e
 * http://stackoverflow.com/questions/29214328/how-does-e-differ-from-e-and-v-differ-from-v
 *
 * O(|V| + |E|)) time.
 *
 *
 */
public class BipartiteCheck {

    /*
     * Example:
     * ---------
     *
     * 1. failed case.
     * ----------------
     * A
     * | \
     * |  C
     * | /
     * B
     *
     * 2. Success case:
     * ---------------
     * A
     *   \
     *    B
     *   /
     * C
     *   \
     *     D
     */
    /**
     * Returns whether the input graph is bipartite.
     *
     * @param g The graph to examine.
     * @return Whether the graph is bipartite.
     */
    public static <T> boolean isBipartite(BipartiteGraph<T> g) {
        /* We'll associate each node in the graph with its parity in the DFS
         * search.  This map will hold this association.
         */
        Map<T, Boolean> parityTable = new HashMap<T, Boolean>();

        /* Start off a DFS from each node in the graph, unless we've already
         * visited it.  We hoist the check for whether the node has been
         * explored before out of the recursion, since if the node has been
         * explored previously we don't care what parity it has.
         */

        // this is used for disjoint graphs.
        //      TODO: post checkpoint.
        //        for (T node: g) {
        //            if (!parityTable.containsKey(node) && !dfsExplore(node, g, new HashMap<T, Boolean>(), true)) {
        //                return false;
        //            }
        //        }

        return dfsExplore(g.iterator().next(), g, new HashMap<T, Boolean>(), true);
    }

    /**
     * Recursively explores outward from the given node in the DFS, labeling
     * it with the appropriate parity.  If a contradiction is detected in the
     * parity assignment, this returns false.
     *
     * @param node The node to explore.
     * @param g The graph in which to perform the DFS.
     * @param parityTable A map from nodes to their parities.
     * @param parity The expected parity of this node.
     */
    private static <T> boolean dfsExplore(T node, BipartiteGraph<T> g, Map<T, Boolean> parityTable, boolean parity) {
        /* If we've visited this node, return whether the parity matches the
         * expected parity.
         */
        if (parityTable.containsKey(node))
            return parityTable.get(node).equals(parity); // this is the normal case.

        /* Otherwise, mark that this node has the indicated parity. */
        parityTable.put(node, parity);

        /* Continue exploring outward from this node.  If we find a
         * contradiction, immediately abort and report failure.
         */
        for (T endpoint: g.getNeighbors(node)) {
            /* Note that we use the opposite parity for all child nodes, since
             * we alternate between odd and even in the DFS tree.
             */
            if (!dfsExplore(endpoint, g, parityTable, !parity)) {
                return false;
            }
        }

        /* If we made it here, everything in this subtree worked out. */
        return true;
    }



    public static void main(String[] args) {

        BipartiteGraph<Integer> bg1 = new BipartiteGraph<Integer>();
        bg1.addNode(1);  bg1.addNode(2); bg1.addNode(3);
        bg1.addEdge(1, 2, 5);
        bg1.addEdge(2, 3, 5);
        bg1.addEdge(1, 3, 5);

        System.out.println(isBipartite(bg1));

        BipartiteGraph<Integer> bg2 = new BipartiteGraph<Integer>();
        bg2.addNode(1);  bg2.addNode(2); bg2.addNode(3);  bg2.addNode(4);
        bg2.addEdge(1, 2, 5);
        bg2.addEdge(2, 3, 5);
        bg2.addEdge(3, 4, 5);

        System.out.println(isBipartite(bg2));
    }
}
