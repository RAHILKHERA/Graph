/**
 * @author Rahil Khera
 * @email rahilakhera@gmail.com
 * @create date 16-Dec-2023 02:14:24
 * @desc [Dijkstras Test, to test the Dijkstra's algo.]
 */

package com.rahilakhera.graph.singlesourceshortestpath;

import com.rahilakhera.graph.graph.Graph;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

public class DijkstrasTest {

    @Test
    void testDijkstraAlgorithm() {
        // Create a directed graph
        Graph directedGraph = new Graph(true);
        directedGraph.addEdge(0, 1, 4);
        directedGraph.addEdge(0, 2, 2);
        directedGraph.addEdge(1, 2, 5);
        directedGraph.addEdge(1, 3, 10);
        directedGraph.addEdge(2, 3, 3);

        // Create an instance of Dijkstra's algorithm
        Dijkstras dijkstras = new Dijkstras(directedGraph);

        // Find the shortest paths from source vertex 0
        Map<Integer, Integer> shortestPaths = dijkstras.findShortestPath(0);

        // Check the correctness of the shortest paths and distances
        assertEquals(0, shortestPaths.get(0));
        assertEquals(4, shortestPaths.get(1));
        assertEquals(2, shortestPaths.get(2));
        assertEquals(5, shortestPaths.get(3));

        // Check the correctness of the paths
        assertEquals(Arrays.asList(0), dijkstras.getPaths().get(0));
        assertEquals(Arrays.asList(0, 2), dijkstras.getPaths().get(1));
        assertEquals(Arrays.asList(0), dijkstras.getPaths().get(2));
        assertEquals(Arrays.asList(0, 2, 3), dijkstras.getPaths().get(3));

        // Create an undirected graph
        Graph undirectedGraph = new Graph(false);
        undirectedGraph.addEdge(0, 1, 2);
        undirectedGraph.addEdge(0, 2, 4);
        undirectedGraph.addEdge(1, 2, 1);
        undirectedGraph.addEdge(1, 3, 7);
        undirectedGraph.addEdge(2, 3, 3);

        // Create a new instance of Dijkstra's algorithm for the undirected graph
        Dijkstras undirectedDijkstras = new Dijkstras(undirectedGraph);

        // Find the shortest paths from source vertex 0
        Map<Integer, Integer> undirectedShortestPaths = undirectedDijkstras.findShortestPath(0);

        // Check the correctness of the shortest paths and distances for the undirected graph
        assertEquals(0, undirectedShortestPaths.get(0));
        assertEquals(2, undirectedShortestPaths.get(1));
        assertEquals(4, undirectedShortestPaths.get(2));
        assertEquals(7, undirectedShortestPaths.get(3));

        // Check the correctness of the paths for the undirected graph
        assertEquals(Arrays.asList(0), undirectedDijkstras.getPaths().get(0));
        assertEquals(Arrays.asList(0, 1), undirectedDijkstras.getPaths().get(1));
        assertEquals(Arrays.asList(0, 2), undirectedDijkstras.getPaths().get(2));
        assertEquals(Arrays.asList(0, 2, 3), undirectedDijkstras.getPaths().get(3));
    }
}

