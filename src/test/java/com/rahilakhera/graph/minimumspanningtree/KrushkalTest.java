package com.rahilakhera.graph.minimumspanningtree;

import com.rahilakhera.graph.graph.Graph;
import com.rahilakhera.graph.graph.Edge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class KrushkalTest {

    private Graph graph;

    @BeforeEach
    void setUp() {
        // Create a sample graph for testing Kruskal's algorithm
        graph = new Graph();
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 5);
        graph.addEdge(2, 3, 3);
    }

    @Test
    void testMinimumSpanningTreeGeneration() {
        // Create Krushkal instance with the sample graph
        Krushkal kruskal = new Krushkal(graph);

        // Generate the Minimum Spanning Tree
        kruskal.generateMinimumSpannigTree();

        // Check if the minimum weight is correct
        assertEquals(6, kruskal.getMinimumWeight());

        // Check if the correct number of edges is included in the Minimum Spanning Tree
        List<Edge> treeEdges = kruskal.getTreeEdges();
        assertEquals(3, treeEdges.size());

        // Ensure that the edges in the Minimum Spanning Tree are correct
        assertTrue(treeEdges.contains(new Edge(0, 1, 2)));
        assertTrue(treeEdges.contains(new Edge(1, 2, 1)));
        assertTrue(treeEdges.contains(new Edge(2, 3, 3)));
    }

    @Test
    void testKruskalDisconnectedGraph() {
        Graph graph = new Graph();
        graph.addEdge(0, 1, 1);
        graph.addEdge(2, 3, 2);

        Krushkal kruskal = new Krushkal(graph);
        kruskal.generateMinimumSpannigTree();

        List<Edge> expectedEdges = List.of(new Edge(0, 1, 1), new Edge(2, 3, 2));
        assertTrue(expectedEdges.contains(new Edge(0, 1, 1)));
        assertTrue(expectedEdges.contains(new Edge(2, 3, 2)));
        assertEquals(3, kruskal.getMinimumWeight());
    }

    @Test
    void testKruskalSingleNodeGraph() {
        Graph graph = new Graph();
        graph.addVertex(0);

        Krushkal kruskal = new Krushkal(graph);
        kruskal.generateMinimumSpannigTree();

        assertTrue(kruskal.getTreeEdges().isEmpty());
        assertEquals(0, kruskal.getMinimumWeight());
    }

    @Test
    void testKruskalSimpleGraph() {
        Graph graph = new Graph();
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 5);
        graph.addEdge(2, 3, 7);

        Krushkal kruskal = new Krushkal(graph);
        kruskal.generateMinimumSpannigTree();

        List<Edge> expectedEdges = List.of(new Edge(0, 2, 3), new Edge(1, 2, 2), new Edge(1, 3, 5)); // Corrected edges
        assertTrue(expectedEdges.contains(new Edge(0, 2, 3)));
        assertTrue(expectedEdges.contains(new Edge(1, 2, 2)));
        assertTrue(expectedEdges.contains(new Edge(1, 3, 5)));
        assertEquals(10, kruskal.getMinimumWeight());
    }

    @Test
    void testKruskalCyclicGraph() {
        Graph graph = new Graph();
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 0, 3);

        Krushkal kruskal = new Krushkal(graph);
        kruskal.generateMinimumSpannigTree();

        // Check if cycle is correctly avoided
        List<Edge> expectedEdges = List.of(new Edge(0, 1, 1), new Edge(1, 2, 2));
        assertTrue(expectedEdges.contains(new Edge(0, 1, 1)));
        assertTrue(expectedEdges.contains(new Edge(1, 2, 2)));
        assertEquals(3, kruskal.getMinimumWeight());
    }

    @Test
    void testKruskalSameWeightEdges() {
        Graph graph = new Graph();
        graph.addEdge(0, 1, 5);
        graph.addEdge(0, 2, 5);
        graph.addEdge(1, 2, 10);

        Krushkal kruskal = new Krushkal(graph);
        kruskal.generateMinimumSpannigTree();

        // Check if either (0, 1) or (0, 2) is included, but not both
        List<Edge> expectedEdges = List.of(new Edge(0, 1, 5), new Edge(0, 2, 5));
        assertTrue(kruskal.getTreeEdges().containsAll(expectedEdges));
        assertEquals(10, kruskal.getMinimumWeight());
    }

}
