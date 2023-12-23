/**
 * @author Rahil Khera
 * @email rahilakhera@gmail.com
 * @create date 23-Dec-2023 21:18:39
 * @modify date 23-Dec-2023 21:18:39
 * @desc [description]
 */

package com.rahilakhera.graph.allpairsshortestpath;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import com.rahilakhera.graph.graph.Graph;

public class JohnsonTest {

    private Graph graph;
    private Johnson johnson;

    @Test
    public void testShortestPathsNoNegativeEdges() {

        graph = new Graph();
        // Assume a graph without negative edges
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 3, 5);
        graph.addEdge(1, 3, 9);

        johnson = new Johnson(graph);
        Map<Integer, Map<Integer, Integer>> result = johnson.getAllShortestPath();

        // Perform assertions based on expected results
        assertEquals(0, result.get(1).get(1).intValue()); // Shortest path from 1 to 1 is 0
        assertEquals(3, result.get(1).get(2).intValue()); // Shortest path from 1 to 2 is 3
        assertEquals(8, result.get(1).get(3).intValue()); // Shortest path from 1 to 3 is 8
        // Add more assertions based on your specific graph

        // For paths
        Map<Integer, Map<Integer, List<Integer>>> paths = johnson.getPaths();
        List<Integer> pathFrom1To3 = paths.get(1).get(3);
        assertEquals(Arrays.asList(1, 2, 3), pathFrom1To3); // Verify the path
    }

    @Test
    public void testShortestPathsWithUndirectedGraphNegativeEdges() {

        graph = new Graph();
        // Assume a graph with negative edges
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 3, -5);
        graph.addEdge(1, 3, 8);

        johnson = new Johnson(graph);
        assertThrows(RuntimeException.class, () -> johnson.getAllShortestPath(),
                "Johnson, Undirected graph with negative edge implies negative cycle.");
    }

    @Test
    public void testWithoutSetupShortestPathsWithDirectedGraphNegativeEdges() {
        // Assume a graph with negative edges

        Graph directedGraph = new Graph(true);

        directedGraph.addEdge(1, 2, 3);
        directedGraph.addEdge(2, 3, -5);
        directedGraph.addEdge(1, 3, 8);

        Johnson johnson = new Johnson(directedGraph);
        Map<Integer, Map<Integer, Integer>> result = johnson.getAllShortestPath();

        // Perform assertions based on expected results
        assertEquals(0, result.get(1).get(1)); // Shortest path from 1 to
        assertEquals(3, result.get(1).get(2)); // Shortest path from 1 to
        assertEquals(-2, result.get(1).get(3)); // Shortest path from 1 to

        // For paths
        Map<Integer, Map<Integer, List<Integer>>> paths = johnson.getPaths();
        assertEquals(Arrays.asList(1, 2, 3), paths.get(1).get(3)); // Verify the path
        assertEquals(Arrays.asList(2, 3), paths.get(2).get(3));
    }

    @Test
    public void testWithoutSetupShortestPathsWithDirectedComplexGraphNegativeEdges() {
        // Assume a graph with negative edges
        /**
         * Example from
         * https://www.javatpoint.com/johnsons-algorithm
         */
        Graph directedGraph = new Graph(true);

        directedGraph.addEdge(1, 2, -3);
        directedGraph.addEdge(2, 1, 5);
        directedGraph.addEdge(2, 3, 3);
        directedGraph.addEdge(3, 1, 1);
        directedGraph.addEdge(1, 4, 2);
        directedGraph.addEdge(4, 1, -1);
        directedGraph.addEdge(4, 3, 4);

        Johnson johnson = new Johnson(directedGraph);
        Map<Integer, Map<Integer, Integer>> result = johnson.getAllShortestPath();

        // Perform assertions based on expected results
        assertEquals(0, result.get(1).get(1));
        assertEquals(-3, result.get(1).get(2));
        assertEquals(0, result.get(1).get(3));
        assertEquals(2, result.get(1).get(4));

        assertEquals(4, result.get(2).get(1));
        assertEquals(0, result.get(2).get(2));
        assertEquals(3, result.get(2).get(3));
        assertEquals(6, result.get(2).get(4));

        assertEquals(1, result.get(3).get(1));
        assertEquals(-2, result.get(3).get(2));
        assertEquals(0, result.get(3).get(3));
        assertEquals(3, result.get(3).get(4));

        assertEquals(-1, result.get(4).get(1));
        assertEquals(-4, result.get(4).get(2));
        assertEquals(-1, result.get(4).get(3));
        assertEquals(0, result.get(4).get(4));

        // For paths
        Map<Integer, Map<Integer, List<Integer>>> paths = johnson.getPaths();
        assertEquals(Arrays.asList(1), paths.get(1).get(1)); // Verify the path
        assertEquals(Arrays.asList(1, 2), paths.get(1).get(2));
        assertEquals(Arrays.asList(1, 2, 3), paths.get(1).get(3));
        assertEquals(Arrays.asList(1, 4), paths.get(1).get(4));

        assertEquals(Arrays.asList(2, 3, 1), paths.get(2).get(1)); // Verify the path
        assertEquals(Arrays.asList(2), paths.get(2).get(2));
        assertEquals(Arrays.asList(2, 3), paths.get(2).get(3));
        assertEquals(Arrays.asList(2, 3, 1, 4), paths.get(2).get(4));

        assertEquals(Arrays.asList(3, 1), paths.get(3).get(1)); // Verify the path
        assertEquals(Arrays.asList(3, 1, 2), paths.get(3).get(2));
        assertEquals(Arrays.asList(3), paths.get(3).get(3));
        assertEquals(Arrays.asList(3, 1, 4), paths.get(3).get(4));

        assertEquals(Arrays.asList(4, 1), paths.get(4).get(1)); // Verify the path
        assertEquals(Arrays.asList(4, 1, 2), paths.get(4).get(2));
        assertEquals(Arrays.asList(4, 1, 2, 3), paths.get(4).get(3));
        assertEquals(Arrays.asList(4), paths.get(4).get(4));

    }
}
