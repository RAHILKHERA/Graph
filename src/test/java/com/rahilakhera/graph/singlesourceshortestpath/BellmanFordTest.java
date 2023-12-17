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
        assertEquals(2, bellmanFord.findShortestPath(2).get(3));
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
}
