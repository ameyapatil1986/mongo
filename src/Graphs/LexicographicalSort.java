package Graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;


/**
 * https://leetcode.com/problems/course-schedule-ii/
 */
class GraphLexico<T> implements Iterable<T> {

    /* A map from nodes in the graph to sets of outgoing edges.  Each
     * set of edges is represented by a map from edges to doubles.
     */
    private final Map<T, List<T>> graph = new HashMap<T, List<T>>();

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

        graph.put(node, new ArrayList<T>());
        return true;
    }

    /**
     * Given the source and destination node it would add an arc from source
     * to destination node. If an arc already exists then the value would be
     * updated the new value.
     *
     * @param source                    the source node.
     * @param destination               the destination node.
     * @param length                    if length if
     * @throws NullPointerException     if source or destination is null.
     * @throws NoSuchElementException   if either source of destination does not exists.
     */
    public void addEdge (T source, T destination) {
        if (source == null || destination == null) {
            throw new NullPointerException("Source and Destination, both should be non-null.");
        }
        if (!graph.containsKey(source) || !graph.containsKey(destination)) {
            throw new NoSuchElementException("Source and Destination, both should be part of graph");
        }
        /* A node would always be added so no point returning true or false */
        graph.get(source).add(destination);
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
    public List<T> nodesFrom(T node) {
        if (node == null) {
            throw new NullPointerException("The node should not be null.");
        }
        List<T> edges = graph.get(node);
        if (edges == null) {
            throw new NoSuchElementException("Source node does not exist.");
        }
        return Collections.unmodifiableList(graph.get(node));
    }

    /**
     * Returns the iterator that travels the nodes of a graph.
     *
     * @return an iterator that travels the nodes of a graph.
     */
    @Override public Iterator<T> iterator() {
        return graph.keySet().iterator();
    }
}

/**
 * Sample example:
 * ---------------
 * AB
 * AC
 * CD
 *
 *
 *  A    B
 *   \ /
 *    C
 *    |
 *    D
 *
 * A -> C
 * B -> C
 * D -> D
 *
 * Topological sort should be: ABCD or BACD.
 *
 */


/**
 *
 * References:
 * http://www.keithschwarz.com/interesting/code/?dir=topological-sort
 * https://www.youtube.com/watch?v=ddTC4Zovtbc
 *
 *
 * Refer to lexigo graphical sort.
 *
 * Sample example:
 *
 *  A    B
 *   \ /
 *    C
 *    |
 *    D
 *
 * A -> C
 * B -> C
 * D -> D
 *
 * Topological sort should be: ABCD or BACD.
 *
 *
 *
 * Complexity:
 * ----------
 * O(V+E)
 *
 *
 */

/**
 * References:
 * -----------
 * http://www.geeksforgeeks.org/flipkart-interview-set-5-off-campus/
 * http://www.careercup.com/question?id=5685334973087744
 * http://www.careercup.com/question?id=13394663
 * http://stackoverflow.com/questions/3123554/question-from-interview-retrieve-alphabetic-order-from-dictionary
 * http://www.geeksforgeeks.org/topological-sorting/
 * http://www.keithschwarz.com/interesting/code/topological-sort/TopologicalSort.java.html
 * http://stackoverflow.com/questions/23318537/complexity-of-calculating-matrix-with-uneven-row-length
 * http://codereview.stackexchange.com/questions/48292/return-the-lexicographic-order
 *
 *
 *
 *
 * Topological sort complexity:
 * -----------------------------
 * Time:
 * =====
 * O(V + E)
 * explanation:
 * Assume: a graph with 10 nodes, each connected to each other in a straight line, just like a skewed tree
 * 1->2->3->...->10.
 * Now, the dfs will finish all edges in the first node '1' only. Thus constituting for 'E'
 *
 * But, the for loop of nodes ( grep:   for (Character node : reverseGraph) { )
 * will still reel through all the nodes, constituting the 'V'
 *
 * Complexity of constructing graph:
 * O(n * m) where n is number of words and m is average length ( i guess its average length )
 *
 * Space:
 * ======
 * O(V + E)  - entire graph is stored.
 *
 *
 * BB:
 * ---
 * 19
 *
 *
 */
public final class LexicographicalSort {

    private LexicographicalSort() {}

    /**
     * Returns the list of characters in lexicographically sorted order.
     *
     * Note that if entire information needed to determine lexicographical
     * order is not present then results are unreliable.
     *
     * @param dictionary the list of words ordered in lexicographical order
     */
    public static List<Character> lexigoGraphicOrder(List<String> dictionary) {
        final GraphLexico<Character> graph = new GraphLexico<Character>();
        for (int i = 0; i < dictionary.size() - 1; i++) {
            createGraph(dictionary.get(i),  dictionary.get(i + 1), graph);
        }
        //alternatively: GraphUtil.topologicalSort(graph);
        return topologicalSort(graph);
    }

    /**
     * Creates a DAG based on the lexicographical order.
     *
     *
     * @param string1   the first string with higher placement/priority in dictionary
     * @param string2   the second string with lesser placement/priority in dictionary
     * @param graph     the DAG to be constructed.
     */
    private static void createGraph(String string1, String string2, GraphLexico<Character> graph) {
        char[] ch1 = string1.toCharArray();
        char[] ch2 = string2.toCharArray();

        // pick the smaller length
        int minLength = ch1.length > ch2.length ? ch2.length : ch1.length;

        for (int i = 0; i < minLength; i++) {
            if (ch1[i] != ch2[i]) {
                graph.addNode(ch1[i]);
                graph.addNode(ch2[i]);
                graph.addEdge(ch1[i], ch2[i]);
                //return;
            }
        }
    }


    /**
     *
     * References:
     * http://www.keithschwarz.com/interesting/code/?dir=topological-sort
     * https://www.youtube.com/watch?v=ddTC4Zovtbc
     *
     *
     * Refer to lexigo graphical sort.
     *
     *
     * This is the result of an input like:
     * ------------------------------------
     * AB
     * AC
     * AD
     * CD
     *
     *
     * Sample example:
     * ---------------
     *
     *  A    B
     *   \ /
     *    C
     *    |
     *    D
     *
     * A -> C
     * B -> C
     * D -> D
     *
     * Topological sort should be: ABCD or BACD.
     *
     *
     *
     * Complexity:
     * ----------
     * O(V+E)
     *
     *
     */
    /**
     * Running the topological sort, on the constructed graph
     *
     *
     * @param graph     the DAG determining priority of characters
     * @return          the characters in lexicographic order
     */
    private static  List<Character> topologicalSort(GraphLexico<Character> graph) {
        final GraphLexico<Character> reverseGraph = reverseGraph(graph);

        final List<Character> result = new ArrayList<Character>();
        final Set<Character> visited = new HashSet<Character>();

        for (Character node : reverseGraph) {
            explore(node, result, visited, reverseGraph);
        }

        return result;
    }


    private static void explore (Character node, List<Character> result, Set<Character> visited, GraphLexico<Character> reverseGraph) {

        if (visited.contains(node)) {
            return;
        }

        // System.out.println(node);
        visited.add(node);

        for(Character currNode : reverseGraph.nodesFrom(node)) {
            explore(currNode, result, visited, reverseGraph);
        }

        result.add(node);
    }

    private static GraphLexico<Character> reverseGraph(GraphLexico<Character> graph) {
        final GraphLexico<Character> graphRev = new GraphLexico<Character>();

        for (Character node : graph) {
            graphRev.addNode(node);
        }

        for (Character node : graph) {
            for (Character neighbors : graph.nodesFrom(node)) {
                graphRev.addEdge(neighbors, node);
            }
        }

        return graphRev;
    }


    public static void main(String[] args) {
//        List<String> list = new ArrayList<String>();
//        //        list.add("abc");
//        //        list.add("acd");
//        //        list.add("bcc");
//        //        list.add("bed");
//        //        list.add("bdc");
//        //        list.add("dab");
//
//        list.add("ab");
//        list.add("ac");
//        list.add("ad");
//        list.add("cd");
//
//        List<Character> expectedList = new ArrayList<Character>();
//        expectedList.add('a');
//        expectedList.add('b');
//        expectedList.add('c');
//        expectedList.add('d');
//
//        assertEquals(expectedList, lexigoGraphicOrder(list));
    }

}

