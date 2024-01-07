/**
 * @author Rahil Khera
 * @email rahilakhera@gmail.com
 * @create date 07-Jan-2024 15:40:23
 * @desc [PrimsTest, TestCases to test Prim's Implementation.]
 */

package com.rahilakhera.graph.minimumspanningtree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.rahilakhera.graph.graph.Edge;
import com.rahilakhera.graph.graph.Graph;

public class PrimsTest {

    @Test
    void testGenerateMinimumSpanningTree() {
        // Create a sample graph for testing
        Graph graph = new Graph();
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 7);
        graph.addEdge(2, 3, 3);

        Prims prims = new Prims(graph);
        prims.generateMinimumSpanningTree();

        // Expected minimum spanning tree edges and weight for the provided sample graph
        List<Edge> expectedEdges = Arrays.asList(new Edge(0, 1, 2), new Edge(1, 2, 1), new Edge(2, 3, 3));
        int expectedWeight = 6;

        // Actual results from the algorithm
        List<Edge> actualEdges = prims.getMinimumSpanningTree();
        int actualWeight = prims.getMinimumSpanningTreeWeight();

        // Assert the results
        assertTrue(actualEdges.containsAll(expectedEdges) && expectedEdges.containsAll(actualEdges),
                "Edges in the minimum spanning tree are incorrect.");

        assertEquals(expectedWeight, actualWeight, "Minimum spanning tree weight is incorrect.");
    }

    @Test
    void testSimpleConnectedGraph() {
        Graph graph = new Graph();
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 7, 8);
        graph.addEdge(1, 2, 8);
        graph.addEdge(1, 7, 11);
        graph.addEdge(2, 3, 7);
        graph.addEdge(2, 8, 2);
        graph.addEdge(2, 5, 4);
        graph.addEdge(3, 4, 9);
        graph.addEdge(3, 5, 14);
        graph.addEdge(4, 5, 10);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 7, 1);
        graph.addEdge(6, 8, 6);
        graph.addEdge(7, 8, 7);

        Prims prims = new Prims(graph);
        prims.generateMinimumSpanningTree();

        List<Edge> mst = prims.getMinimumSpanningTree();
        int mstWeight = prims.getMinimumSpanningTreeWeight();

        assertEquals(8, mst.size()); // Expected number of edges in MST
        assertEquals(37, mstWeight); // Expected total weight of MST
    }

}
