/**
 * @author Rahil Khera
 * @email rahilakhera@gmail.com
 * @create date 17-Dec-2023 00:37:03
 * @modify date 17-Dec-2023 00:37:03
 * @desc [BellmanFord Algo Tests]
 */

package com.rahilakhera.graph.singlesourceshortestpath;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.rahilakhera.graph.graph.Graph;

public class BellmanFordTest {

    @Test
    void testShortestPathNoEdges() {
        Graph graph = new Graph();
        BellmanFord bellmanFord = new BellmanFord(graph);
        assertEquals(0, bellmanFord.findShortestPath(0).size());
    }

    @Test
    void testShortestPathPositiveWeights() {
        Graph graph = new Graph();
        graph.addEdge(0, 1, 5);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 3, 7);
        graph.addEdge(2, 3, 2);

        BellmanFord bellmanFord = new BellmanFord(graph);
        assertEquals(3, bellmanFord.findShortestPath(2).get(0));
        assertEquals(8, bellmanFord.findShortestPath(2).get(1));
        assertEquals(0, bellmanFord.findShortestPath(2).get(2));
        assertEquals(2, bellmanFord.findShortestPath(2).get(3));

        // Correctness of Paths
        assertEquals(Arrays.asList(2, 0), bellmanFord.getPaths().get(0));
        assertEquals(Arrays.asList(2, 0, 1), bellmanFord.getPaths().get(1));
        assertEquals(Arrays.asList(2), bellmanFord.getPaths().get(2));
        assertEquals(Arrays.asList(2, 3), bellmanFord.getPaths().get(3));
    }

    @Test
    void testShortestPathNegativeWeights() {
        Graph graph = new Graph();
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, -1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 3, 2);

        BellmanFord bellmanFord = new BellmanFord(graph);
        assertNull(bellmanFord.findShortestPath(0));
    }

    @Test
    void testShortestPathNegativeCycle() {
        Graph graph = new Graph();
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, -3);
        graph.addEdge(2, 0, 2);

        BellmanFord bellmanFord = new BellmanFord(graph);
        assertNull(bellmanFord.findShortestPath(0));
    }

    @Test
    void testShortestPathWithCycle() {
        Graph graph = new Graph();
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 0, -6);

        BellmanFord bellmanFord = new BellmanFord(graph);
        assertNull(bellmanFord.findShortestPath(0));
    }

    @Test
    void testShortestPathDisconnectedGraph() {
        Graph graph = new Graph();
        graph.addEdge(0, 1, 1);
        graph.addEdge(2, 3, 2);

        BellmanFord bellmanFord = new BellmanFord(graph);
        assertNull(bellmanFord.findShortestPath(0).get(3));
    }

    @Test
    public void testEmptyGraph() {
        BellmanFord bellmanFord = new BellmanFord(new Graph());
        assertTrue(bellmanFord.findShortestPath(0).isEmpty());
    }

    @Test
    @DisplayName("Test Directed Graph with Negative Edge")
    public void testDirectedGraphWithNegativeEdge() {

        /**
         * To create directed graph, pass true to the constructor.
         */
        Graph graph = new Graph(true);
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, -1);
        graph.addEdge(1, 3, 3);
        graph.addEdge(2, 3, 2);
        graph.addEdge(2, 4, 5);
        graph.addEdge(3, 4, 1);

        BellmanFord bellmanFord = new BellmanFord(graph);

        Map<Integer, Integer> shortestPath = bellmanFord.findShortestPath(0);

        // Check correctness of shortest path.
        assertEquals(0, shortestPath.get(0));
        assertEquals(2, shortestPath.get(1));
        assertEquals(1, shortestPath.get(2));
        assertEquals(3, shortestPath.get(3));
        assertEquals(4, shortestPath.get(4));

        // Check correctness of paths.
        assertEquals(Arrays.asList(0), bellmanFord.getPaths().get(0));
        assertEquals(Arrays.asList(0, 1), bellmanFord.getPaths().get(1));
        assertEquals(Arrays.asList(0, 1, 2), bellmanFord.getPaths().get(2));
        assertEquals(Arrays.asList(0, 1, 2, 3), bellmanFord.getPaths().get(3));
        assertEquals(Arrays.asList(0, 1, 2, 3, 4), bellmanFord.getPaths().get(4));

        shortestPath = bellmanFord.findShortestPath(1);
        assertEquals(Integer.MAX_VALUE, shortestPath.getOrDefault(0, Integer.MAX_VALUE));

    }

}
